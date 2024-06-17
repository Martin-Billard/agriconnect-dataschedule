package ag.agriconnectdataschedule.metier;

import ag.agriconnectdataschedule.entities.Capteur;
import ag.agriconnectdataschedule.entities.Limite;
import ag.agriconnectdataschedule.entities.Releve;
import jakarta.annotation.PostConstruct;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledFuture;

@Service
public class SchedulerService {

    private final CapteurServiceClient capteurServiceClient;
    private final ReleveServiceClient releveServiceClient;
    private final LimiteurServiceClient limiteurServiceClient;
    private final ActionneurServiceClient actionneurServiceClient;

    private final ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
    private final Map<Long, ScheduledFuture<?>> scheduledTasks = new ConcurrentHashMap<>();

    public SchedulerService(CapteurServiceClient capteurServiceClient, ReleveServiceClient releveServiceClient, LimiteurServiceClient limiteurServiceClient, ActionneurServiceClient actionneurServiceClient) {
        this.capteurServiceClient = capteurServiceClient;
        this.releveServiceClient = releveServiceClient;
        this.limiteurServiceClient = limiteurServiceClient;
        this.actionneurServiceClient = actionneurServiceClient;
    }

    @PostConstruct
    public void init() {
        taskScheduler.setPoolSize(100);
        taskScheduler.initialize();
        scheduleAllSensors(); // Planifier la collecte pour tous les capteurs au démarrage
    }

    public void scheduleAllSensors() {
        List<Capteur> capteurs = capteurServiceClient.getAllCapteurs();
        for (Capteur capteur : capteurs) {
            scheduleSensorDataCollection(capteur.getId());
        }
    }

    public void scheduleSensorDataCollection(Long capteurId) {
        Capteur capteur = capteurServiceClient.findById(capteurId);

        // Annuler la tâche précédente si elle existe
        if (scheduledTasks.containsKey(capteur.getId())) {
            scheduledTasks.get(capteur.getId()).cancel(false);
        }

        // Planifier la nouvelle tâche avec le nouvel intervalle
        ScheduledFuture<?> scheduledTask = taskScheduler.scheduleAtFixedRate(
                () -> collectSensorData(capteur),
                capteur.getIntervalle() * 1000L
        );

        // Stocker la nouvelle tâche planifiée
        scheduledTasks.put(capteur.getId(), scheduledTask);
    }

    private void collectSensorData(Capteur capteur) {
        Random random = new Random();
        int humidityVariation = random.nextInt(-2, 2);
        int humidity = capteur.getHumidite() + humidityVariation;
        double tempVariation = -3 + 6 * random.nextDouble();
        double temp = capteur.getTemperature() - tempVariation;

        createReleve(humidity, temp, capteur.getId());
        gestionCapteurLimite(humidity, temp, capteur.getId());

    }

    private void createReleve(int humidity, double temperature, Long idCapteur) {
        Random random = new Random();
        Releve releve = new Releve();
        releve.setDateReleve(LocalDate.now());
        releve.setHumitide(humidity);
        releve.setTemperature(temperature);
        releve.setIdCapteur(idCapteur);
        releve.setId(random.nextInt(Integer.MAX_VALUE));
        releveServiceClient.addReleve(releve);
    }

    private void gestionCapteurLimite(int humidity, double temperature, Long idCapteur){
        Limite limite = limiteurServiceClient.getLimiteByIdCapteur(idCapteur);
        if (limite != null){
            if (limite.getLimitHum() != null){
                if (limite.getLimitHum()< humidity){
                    actionneurServiceClient.triggerActionneur(limite.getIdActionneurHum(), limite.getDureeActionneurHum());
                }
            }
            if (limite.getLimitTemp() != null) {
                if (limite.getLimitTemp() < temperature) {
                    actionneurServiceClient.triggerActionneur(limite.getIdActionneurTemp(), limite.getDureeActionneurTemp());
                }
            }
        }
    }
}

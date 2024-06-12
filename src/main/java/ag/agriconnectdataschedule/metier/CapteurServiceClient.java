package ag.agriconnectdataschedule.metier;

import ag.agriconnectdataschedule.entities.Capteur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "accapteurs")
public interface CapteurServiceClient {

    @GetMapping("/api/capteurs")
    List<Capteur> getAllCapteurs();

    @GetMapping("/api/capteurs/{id}")
    Capteur findById(@PathVariable("id") Long id);

    @GetMapping("/api/capteurs/mesure/{id}")
    Capteur getMesure(@PathVariable("id") Long id);

}

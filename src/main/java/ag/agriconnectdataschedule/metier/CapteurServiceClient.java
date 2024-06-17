package ag.agriconnectdataschedule.metier;

import ag.agriconnectdataschedule.entities.Capteur;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "accapteurs")
public interface CapteurServiceClient {

    @RequestMapping(value = "/api/capteurs", method = RequestMethod.GET)
    List<Capteur> getAllCapteurs();

    @RequestMapping(value = "/api/capteurs/{id}", method = RequestMethod.GET)
    Capteur findById(@PathVariable("id") Long id);

    @RequestMapping(value = "/api/capteurs/mesure/{id}", method = RequestMethod.GET)
    Capteur getMesure(@PathVariable("id") Long id);

}

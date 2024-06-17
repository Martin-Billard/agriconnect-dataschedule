package ag.agriconnectdataschedule.metier;

import ag.agriconnectdataschedule.entities.Limite;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@FeignClient(name = "aclimiteur")
public interface LimiteurServiceClient {

    @RequestMapping(value = "/api/limites/capteur/{idCapteur}", method = RequestMethod.GET)
    Limite getLimiteByIdCapteur(@PathVariable Long idCapteur);
}

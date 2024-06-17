package ag.agriconnectdataschedule.metier;

import ag.agriconnectdataschedule.entities.Capteur;
import ag.agriconnectdataschedule.entities.Limite;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "aclimiteur")
public interface LimiteurServiceClient {

    @GetMapping("/api/limites/capteur/{idCapteur}")
    Limite getLimiteByIdCapteur(@PathVariable Long idCapteur);
}

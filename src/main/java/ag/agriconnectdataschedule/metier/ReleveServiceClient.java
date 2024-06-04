package ag.agriconnectdataschedule.metier;

import ag.agriconnectdataschedule.entities.Releve;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "ac-releve")
public interface ReleveServiceClient {

    @PostMapping("/api/releves")
    Releve addReleve(@RequestBody Releve releve);
}

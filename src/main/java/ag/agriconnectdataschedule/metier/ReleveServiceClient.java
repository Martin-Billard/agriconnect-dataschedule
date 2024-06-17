package ag.agriconnectdataschedule.metier;

import ag.agriconnectdataschedule.entities.Releve;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(name = "acreleves")
public interface ReleveServiceClient {

    @RequestMapping(value = "/api/releves", method = RequestMethod.POST)
    Releve addReleve(@RequestBody Releve releve);
}

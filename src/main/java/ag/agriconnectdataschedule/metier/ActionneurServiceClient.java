package ag.agriconnectdataschedule.metier;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@FeignClient(name = "acactionneurs")
public interface ActionneurServiceClient {

    @RequestMapping(value = "/api/actionneurs/trigger/{id}", method = RequestMethod.POST)
    ResponseEntity<String> triggerActionneur(@PathVariable Long id, @RequestParam Long duration);
}

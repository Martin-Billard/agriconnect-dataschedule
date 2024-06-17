package ag.agriconnectdataschedule.metier;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@FeignClient(name = "acactionneurs")
public interface ActionneurServiceClient {

    @PostMapping("/api/actionneurs/trigger/{id}")
    ResponseEntity<String> triggerActionneur(@PathVariable Long id, @RequestParam Long duration);
}

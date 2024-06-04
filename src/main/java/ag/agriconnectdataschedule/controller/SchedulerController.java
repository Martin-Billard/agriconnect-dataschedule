package ag.agriconnectdataschedule.controller;

import ag.agriconnectdataschedule.metier.SchedulerService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scheduler")
public class SchedulerController {

    private final SchedulerService schedulerService;

    public SchedulerController(SchedulerService schedulerService) {
        this.schedulerService = schedulerService;
    }

    @PostMapping("/schedule/{capteurId}")
    public void scheduleSensorDataCollection(@PathVariable Long capteurId) {
        schedulerService.scheduleSensorDataCollection(capteurId);
    }
}
package be.technobel.materialloc.tasks;

import be.technobel.materialloc.service.RequestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Configuration
@EnableAsync
@EnableScheduling
public class RequestManagementTasks {

    private final RequestService requestService;

    public RequestManagementTasks(RequestService requestService) {
        this.requestService = requestService;
    }

    @Async
    @Scheduled(cron = "0 0 4 * * *")
    public void cleanNonAcceptedTasks(){
        log.info("scheduled task -- cleaning requests");
        requestService.cleanRequests();
    }

}

package be.technobel.materialloc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
public class MateriAllocationAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(MateriAllocationAppApplication.class, args);
    }

}
package ru.practicum.ewm.main_svc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"ru.practicum.ewm.main_svc", "ru.practicum.ewm.stat_svc.client", "ru.practicum.ewm.stat_svc.other"})
public class MainSvc {
    public static void main(String[] args) {
        SpringApplication.run(MainSvc.class, args);
    }
}
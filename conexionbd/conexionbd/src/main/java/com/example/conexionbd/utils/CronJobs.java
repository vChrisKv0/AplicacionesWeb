package com.example.conexionbd.utils;

import com.example.conexionbd.personal.control.PersonalService;
import com.example.conexionbd.personal.model.Personal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CronJobs {

    private static final Logger logger = LoggerFactory.getLogger(CronJobs.class);
    private final PersonalService personalService;

    @Autowired
    public CronJobs(PersonalService personalService) {
        this.personalService = personalService;
    }

    // Este método se ejecutará cada 2 minutos
    @Scheduled(fixedRate = 120000)
    public void printLastRegisteredPersonal() {
        List<Personal> personalList = (List<Personal>) personalService.findAll().getBody().getData();
        if (personalList != null && !personalList.isEmpty()) {
            Personal lastRegistered = personalList.get(personalList.size() - 1);
            logger.info("Último personal registrado: {} {}", lastRegistered.getName(), lastRegistered.getPuesto());
        } else {
            logger.info("No hay personal registrado.");
        }
    }
}

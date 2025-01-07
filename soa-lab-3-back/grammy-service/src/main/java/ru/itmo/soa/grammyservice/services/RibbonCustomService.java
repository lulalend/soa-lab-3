package ru.itmo.soa.grammyservice.services;

import org.springframework.stereotype.Service;

@Service
public class RibbonCustomService {
    private static boolean FIRST_SERVICE_FLAG = false;
    public String getURI() {
        if (!FIRST_SERVICE_FLAG) {
            FIRST_SERVICE_FLAG = true;
            return "https://main-service-1:1111";
        }

        FIRST_SERVICE_FLAG = false;
        return "https://main-service-2:1111";
    }
}

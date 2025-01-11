package ru.itmo.soa.grammyservice.services;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

@Service
public class RibbonCustomService {
    private int currentServiceIndex = 0;
    List<String> allServices = List.of(
            "https://main-service-1:1111",
            "https://main-service-2:1111",
            "https://main-service-3:1111"
    );

    public String getURI() {
        int counter = 0;

        while (counter < 3 && !isAvailable(allServices.get(currentServiceIndex))) {
            currentServiceIndex = (currentServiceIndex + 1) % allServices.size();
            counter++;
        }

        if (counter == 3) {
            throw new IllegalStateException("No services are available!");
        }

        return allServices.get(currentServiceIndex++);
    }

    private boolean isAvailable(String service) {
        String host = service.split(":")[1].replace("//", "");
        int port = Integer.parseInt(service.split(":")[2]);

        return ServiceChecker.isServiceAvailable(host, port);
    }
}

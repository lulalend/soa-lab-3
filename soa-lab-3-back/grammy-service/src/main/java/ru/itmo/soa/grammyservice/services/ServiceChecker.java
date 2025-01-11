package ru.itmo.soa.grammyservice.services;

import java.net.Socket;

public class ServiceChecker {

    public static boolean isServiceAvailable(String host, int port) {
        try (Socket socket = new Socket(host, port)) {
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

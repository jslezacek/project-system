package org.project;

public class MulticastSession {
    private final String ipAddress;
    private final int port;
    private final int buffer = 2048;
    private final String nic = "lo";

    public MulticastSession(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }


}

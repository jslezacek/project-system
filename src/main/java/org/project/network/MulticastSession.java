package org.project.network;

public class MulticastSession {
    private final String ipAddress;
    private final int port;
    private final int buffer = 2048;
    private final String nic;

    public MulticastSession(String ipAddress, int port, String nic) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.nic = nic;
    }
}

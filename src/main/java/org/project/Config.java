package org.project;

public class Config {
    static final String HOST = "127.0.0.1";
    static final int PORT = 10000;

    static int requestLength = 500;
    static int responseLength = 2000;
    static int numOfCalls = 50000;

    static byte[] request = new byte[requestLength];
    static byte[] response = new byte[responseLength];

}

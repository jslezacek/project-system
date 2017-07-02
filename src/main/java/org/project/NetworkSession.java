package org.project;

import java.io.IOException;
import java.net.Socket;

/**
 * Created by jojo on 5/14/17.
 */
public class NetworkSession {

    public NetworkSession(String hostname, int port) throws IOException {
        Socket socket = new Socket(hostname, port);
    }
}

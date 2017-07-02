package org.project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Created by jojo on 5/10/17.
 */
public class BlockingClient {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket(Config.HOST, Config.PORT);

            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();
            int callCount = 0;

            long startTime = System.currentTimeMillis();

            while (true) {
                os.write(Config.request);
                read(is, Config.response);
                callCount++;
                if (callCount == Config.numOfCalls) {
                    System.out.println("numOfcalls=" + Config.numOfCalls + " time: " + (System.currentTimeMillis() - startTime));
                    callCount = 0;
                    startTime = System.currentTimeMillis();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void read(InputStream is, byte[] bytes) throws IOException {
        int num = 0;
        while(num < bytes.length) {
            num += is.read(bytes, num, bytes.length - num);
        }
    }
}

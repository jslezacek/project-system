package org.project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * Created by jojo on 5/10/17.
 */
public class BlockingServer {
    public static void main(String[] args) {
        try {
            ServerSocket srvSocket = new ServerSocket(Config.PORT);

            while (true) {
                final Socket socket = srvSocket.accept();

                new Thread() {
                    @Override
                    public void run() {
                        try {
                            InputStream is = socket.getInputStream();
                            OutputStream os = socket.getOutputStream();
                            while (true) {
                                is.read(Config.response);
                                System.out.println(new String(Config.response));
//                                BlockingClient.read(is, Config.request);
//                                os.write(Config.response);
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
                }.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

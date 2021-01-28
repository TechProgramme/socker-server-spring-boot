package com.techprogramme.socketserver;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

@Component
public class SocketServer implements ApplicationListener<ApplicationReadyEvent> {

    @Override
    public void onApplicationEvent(final ApplicationReadyEvent event) {
        startListener();
    }

    private void startListener() {
        new Thread(() -> {
            try {
                int port = 8000;
                ServerSocket sock = new ServerSocket(port, 1);
                Socket m_sock;

                while (true) {
                    try {
                        System.out.println("Socket Server listening on port: " + port);
                        m_sock = sock.accept();
                        System.out.println("New connection accepted " + m_sock.getInetAddress() + ":" + m_sock.getPort());
                        System.out.println("Accepted Time :" + new Date().toString());

                        InputStream is = m_sock.getInputStream();
                        InputStreamReader isReader = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isReader);

                        String data;
                        StringBuilder payload = new StringBuilder();
                        while (((data = br.readLine()) == null ? "" : data).length() != 0) {
                            payload.append(data);
                        }

                        System.out.println("Payload : " + payload.toString());

                    } catch (IOException e) {
                        System.out.println("failed to accept a connection, exception: " + e.getMessage());
                        continue;
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex.getMessage());
                        continue;
                    }

                    m_sock.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

}
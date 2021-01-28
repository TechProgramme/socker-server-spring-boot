package com.techprogramme.socketserver;

import java.io.DataOutputStream;
import java.net.Socket;

public class SocketClient {

    public static void main(String[] args) {

        try {
            String data = "Hello Socket Server";
            Socket s = new Socket("localhost", 8000);
            DataOutputStream dataOutputStream = new DataOutputStream(s.getOutputStream());
            dataOutputStream.writeUTF(data);
            dataOutputStream.flush();
            dataOutputStream.close();
            s.close();
            System.out.println("\"" + data + "\"" + " Data sent to server");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}



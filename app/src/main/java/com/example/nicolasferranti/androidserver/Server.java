package com.example.nicolasferranti.androidserver;

/**
 * Created by nicolasferranti on 03/08/16.
 */

import android.app.Activity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server extends Activity {
    // define a constant used as size of buffer
    static final int BUFSIZE=1024;
    // main starts things rolling
    static public void main(String args[]) {
        /*
        if (args.length != 1) {
            throw new IllegalArgumentException("Must specify a port!");
        }
        int port = Integer.parseInt(args[0]);
        */
        int port = 4444;
        try {
        // Create Server Socket (passive socket)
            ServerSocket ss = new ServerSocket(port);
            while (true) {
                Socket s = ss.accept();
                handleClient(s);
            }
        } catch (IOException e) {
            System.out.println("Fatal I/O Error !");
            System.exit(0);
        }
    }
    static void handleClient(Socket s) throws IOException {
        byte[] buff = new byte[BUFSIZE];
        int bytesread = 0;
        // print out client's address
        System.out.println("Connection from " +
                s.getInetAddress().getHostAddress());
        // Set up streams
        InputStream in = s.getInputStream();
        OutputStream out = s.getOutputStream();
        // read/write loop
        while ((bytesread = in.read(buff)) != -1) {
            out.write(buff,0,bytesread);
        }
        System.out.println("Client has left\n");
        s.close();
    }

}

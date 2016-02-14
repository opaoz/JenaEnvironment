package ru.vladimirlevin.jenaenv.server;

import ru.opa.pack.net.HttpResponse;
import ru.vladimirlevin.jenaenv.controllers.RequestManager;
import ru.vladimirlevin.jenaenv.views.ServerUI;

import java.io.*;
import java.net.Socket;

/**
 * Created by Vladimir_Levin on 14.02.2016.
 */
public class ClientThread implements Runnable {
    private Socket socket;
    private InputStream inputStream;
    private OutputStream outputStream;
    private String request;
    private ServerUI ui;

    protected ClientThread(Socket socket, ServerUI ui) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        this.ui = ui;
        new Thread(this).start();
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine();
            if (line.startsWith("POST")) {
                request = HttpResponse.readBodyPOST(in, line);
                if (!request.equals("none")) {
                    HttpResponse.writeResponse(new RequestManager(request).toString(), outputStream);
                }
            } else if (line.startsWith("GET")) {
                HttpResponse.sendFileGET(outputStream, line);
            }
        } catch (IOException t) {
            ui.println("Socket error");
            ui.println(t.toString());
        } finally {
            try {
                socket.close();
            } catch (Throwable t) {
                ui.println("Socket closing error");
            }
        }
        ui.println("Client processing finished");
    }
}

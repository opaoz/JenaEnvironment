package ru.vladimirlevin.jenaenv;

import ru.vladimirlevin.jenaenv.references.References;
import ru.vladimirlevin.jenaenv.server.Server;
import ru.vladimirlevin.jenaenv.views.ServerUI;

import java.io.IOException;

/**
 * Created by Vladimir_Levin on 06.12.2015.
 */
public class Main {
    public static ServerUI ui = new ServerUI();

    public static void main(String[] args) {
        try {
            ui.setVisible(true);
            new Server(References.SERVER_PORT, ui).start();
        } catch (IOException e) {
            ui.println("Server crashed" + e);
        }
    }
}

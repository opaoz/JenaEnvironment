package ru.vladimirlevin.jenaenv.server;

import ru.vladimirlevin.jenaenv.references.References;
import ru.vladimirlevin.jenaenv.views.ServerUI;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Vladimir_Levin on 14.02.2016.
 */
public class Server {
    private ServerSocket serverSocket; // Объект сервера, слушающий подключения
    private ServerUI ui; // Визуальное представление консоли

    public Server(int port, ServerUI ui) throws IOException {
        if (port < 0 || port > 65535) { // Если передан неверный порт
            port = References.DEFAULT_SERVER_PORT; // устанавливаем дефотный
            ui.println("Server port changed to default value - " + port);
        }

        this.ui = ui;
        serverSocket = new ServerSocket(port); // Инициализируем объект сервера
        ui.println("Server started...");
    }

    public void start() throws IOException {
        while (true) { // Бесконечный цикл ожидания подключений
            Socket socket = serverSocket.accept(); // Как только клиен присоединился
            ui.println("Client connected with " + socket.getLocalSocketAddress());
            new ClientThread(socket, ui); // Выделяем ему поток
        }
    }
}

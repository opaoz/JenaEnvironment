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
    private Socket socket; // Подлючённый юзер
    private InputStream inputStream; // Сообщения от юзера
    private OutputStream outputStream; // Ответ юзеру
    private String request; // Запрос от юзера
    private ServerUI ui; // Окно консоли

    // Инициализируем новый поток
    protected ClientThread(Socket socket, ServerUI ui) throws IOException {
        this.socket = socket;
        this.inputStream = socket.getInputStream();
        this.outputStream = socket.getOutputStream();
        this.ui = ui;
        new Thread(this).start(); // Запускаем обработку в другом потоке
    }

    // Эта функция вызовется автоматически при запуске потока
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(inputStream));
            String line = in.readLine(); // Считываем первую линию запроса
            if (line.startsWith("POST")) {
                request = HttpResponse.readBodyPOST(in, line); // Если это ПОСТ-запрос, считываем тело запроса
                if (!request.equals("none")) { // Если тело запроса не пустое, передаём его нашему RequestManager'у
                    // и отправляем ответ назад
                    HttpResponse.writeResponse(new RequestManager(request).toString(), outputStream);
                }
            } else if (line.startsWith("GET")) { // Ести это гет запрос
                HttpResponse.sendFileGET(outputStream, line); // отправляем файл в ответ на гет запрос
            }
        } catch (IOException t) { // Обратока ошибок
            ui.println("Socket error");
            ui.println(t.toString());
        } finally {
            try {
                socket.close(); // Закрываем полток после ошибки
            } catch (Throwable t) {
                ui.println("Socket closing error");
            }
        }
        ui.println("Client processing finished");
    }
}

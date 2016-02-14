package ru.vladimirlevin.jenaenv.views;

import ru.vladimirlevin.jenaenv.views.components.Log;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Vladimir_Levin on 14.02.2016.
 */

public class ServerUI extends JFrame {
    private static final long serialVersionUID = 6182239340196405650L;
    private Log log; // Окно лога, созданное в предыдущем шаге

    public ServerUI() {
        log = new Log(); //Инициализируем окно

        this.setLayout(new FlowLayout()); // Т.к. у нас всего один компонент в окне, установим данный тип заполнения
        this.add(log); // Добавляем окно лога на фрейм
        this.setTitle("Server"); // Устанавливаем название
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // При закрытии окна, выключаем сервер
        this.pack(); // Устанавливаем размер по контенту
        this.setVisible(true); // Отрисовываем
    }

    // Метод добавления сообщения в лог
    public void println(String message) {
        log.info(message);
    }
}
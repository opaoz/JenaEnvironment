package ru.vladimirlevin.jenaenv.views.components;

import ru.vladimirlevin.jenaenv.references.References;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Vladimir_Levin on 14.02.2016.
 */
public class Log extends JPanel {
    private static final long serialVersionUID = -7968405633747657642L; // Автоматически генерируемый UID
    private JTextArea text; // Тестовое представление логов
    private DateFormat dateFormat = References.DATE_FORMAT; // Паттерн форматирования даты из файла References

    // Конструктор класса
    public Log() {
        text = new JTextArea(); // Добавляем текст на панель
        text.setAlignmentX(LEFT_ALIGNMENT); // Позиционируем её налево
        text.setAlignmentY(TOP_ALIGNMENT); // и прижимаем к верху
        text.setBackground(Color.BLACK); // Задний фон - чёрный
        text.setForeground(Color.WHITE); // Цвет текста - белый
        text.setFont(References.LOG_FONT); // Стиль шрифта из файла References
        text.setLineWrap(true); // Устанавливаем перенос строк
        text.setWrapStyleWord(true); // И слов

        JScrollPane scrollPane = new JScrollPane(text); // Создаём ScrollBar
        scrollPane.setPreferredSize(new Dimension(500, 200)); // Ограничиваем размеры области вывода
        this.add(scrollPane); // Добавляем к нашей импровизированной консоли
        this.setVisible(true); // Отрисовываем
    }

    // Метод добавления сообщений в консоль
    public void info(String message) {
        text.append("[" + dateFormat.format(new Date()) + "] " + message + " \n");
    }

    // Размеры окна консоли
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(500, 200);
    }
}

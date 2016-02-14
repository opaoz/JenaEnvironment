package ru.vladimirlevin.jenaenv.references;

import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public final class References {
    /*Model*/
    public static final String FAMILY_PATH = "D:/Family/family.rdf"; // Путь сохранения rdf-модели

    /*Server*/
    public static final int DEFAULT_SERVER_PORT = 8080; // Порт по умолчанию для сервера
    public static final int SERVER_PORT = 8080; // Порт для сервера (если не указал используются дефолтный)

    /*Log*/
    public static final Font LOG_FONT = new Font("Verdana", Font.BOLD, 12); // Стиль текста в консоли сервера
    public static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); // Формат даты в консоли
}


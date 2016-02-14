package ru.vladimirlevin.jenaenv.models;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import ru.vladimirlevin.jenaenv.references.References;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Vladimir_Levin on 14.02.2016.
 */
public class FamilyTree {
    public static final String FAMILY_URI = "http://family/"; // Префикс для модели
    public static final String RELATIONSHIP_URI = "http://purl.org/vocab/relationship/"; // Префикс для связей в модели
    private Map<String, Resource> family = new HashMap<String, Resource>(); // Двумерный массив объектов
    private Model model; // Модель

    public FamilyTree() {
        try {
            // Если файл модели уже существует, загружаем его
            model = FileManager.get().loadModel(References.FAMILY_PATH);
        } catch (Exception e) {
            // Если файл не найден, создаём модель и экспортируем её
            model = reCreateModel();
            exportModel(model);
        }
    }

    public Model reCreateModel() {
        model = ModelFactory.createDefaultModel(); // Создаём дефолтную модель
        Resource NAMESPACE = model.createResource(RELATIONSHIP_URI); // Создаём NAMESPACE модели
        model.setNsPrefix("rela", RELATIONSHIP_URI); // Устанавливаем префик для отношений

        Property childOf = model.createProperty(RELATIONSHIP_URI, "childOf"); // Отношение "ребёнок"
        Property siblingOf = model.createProperty(RELATIONSHIP_URI, "siblingOf"); // Отношение "брат"
        Property spouseOf = model.createProperty(RELATIONSHIP_URI, "spouseOf"); // Отношение "супруг"
        Property parentOf = model.createProperty(RELATIONSHIP_URI, "parentOf"); // Отношение "родитель"

        // Объекты людей
        family.put("Adam", model.createResource(FAMILY_URI + "adam"));
        family.put("Dotty", model.createResource(FAMILY_URI + "dotty"));
        family.put("Beth", model.createResource(FAMILY_URI + "beth"));
        family.put("Chuck", model.createResource(FAMILY_URI + "chuck"));
        family.put("Edward", model.createResource(FAMILY_URI + "edward"));
        family.put("Fan", model.createResource(FAMILY_URI + "fan"));
        family.put("Greg", model.createResource(FAMILY_URI + "greg"));
        family.put("Harriet", model.createResource(FAMILY_URI + "harriet"));

        // Отношения между ними
        push("Adam", spouseOf, "Dotty");
        push("Beth", spouseOf, "Chuck");
        push("Fan", spouseOf, "Greg");

        push("Adam", siblingOf, "Beth");
        push("Edward", siblingOf, "Fan");

        push("Edward", childOf, "Adam");
        push("Edward", childOf, "Dotty");
        push("Fan", childOf, "Adam");
        push("Fan", childOf, "Dotty");
        push("Harriet", childOf, "Fan");
        push("Harriet", childOf, "Greg");

        push("Adam", parentOf, "Edward");
        push("Dotty", parentOf, "Edward");
        push("Adam", parentOf, "Fan");
        push("Dotty", parentOf, "Fan");
        push("Fan", parentOf, "Harriet");
        push("Greg", parentOf, "Harriet");

        // После создания возвращаем модель
        return model;
    }

    // Метод установки связи между объектами
    public void push(String who, Property what, String whom) {
        model.add(model.createStatement(family.get(who), what, family.get(whom)));
    }

    // Вспомошательный метод получения модели
    public Model getModel() {
        return model;
    }

    // Экспорт модели
    public static Boolean exportModel(Model model) {
        File file = new File(References.FAMILY_PATH); // Создание файла

        try {
            file.createNewFile();
        } catch (IOException e) {
            return false;
        }
        // Вывод в файл
        try (FileOutputStream fout = new FileOutputStream(file)) {
            RDFWriter writer = model.getWriter("RDF/XML-ABBREV");
            writer.write(model, fout, null);
        } catch (IOException e) {
            return false;
        }

        return true;
    }
}

package ru.vladimirlevin.jenaenv.controllers;

import org.apache.jena.rdf.model.Model;
import ru.opa.pack.util.JSONHelper;
import ru.opa.pack.util.QueryExec;
import ru.vladimirlevin.jenaenv.models.FamilyTree;

/**
 * Created by Vladimir_Levin on 14.02.2016.
 */
public class RequestManager {
    private String jsonString;

    private static FamilyTree familyTree = new FamilyTree(); // Загружаем
    private static Model model = familyTree.getModel(); // И получаем модель

    public RequestManager(String request) {
        // Выполняем SparQL запрос и возвращаем ответ
        jsonString = JSONHelper.generateJSONResponse(QueryExec.exec(request, model));
    }

    @Override
    public String toString() {
        return jsonString;
    }
}

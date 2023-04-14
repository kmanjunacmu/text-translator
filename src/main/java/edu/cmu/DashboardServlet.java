/**
 * Author: Karthik Manjunath
 * Andrew ID: kmanjuna
 */
package edu.cmu;

import edu.cmu.service.MongoService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.bson.Document;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "DashboardServlet", urlPatterns = {""})
public class DashboardServlet extends HttpServlet {

    /**
     * API Get request for the dashboard.
     *
     * @param request  Servlet Request
     * @param response Servlet Response
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        List<Document> documents = MongoService.getDocuments();

        Map<String, Integer> sourceMap = new HashMap<>();
        Map<String, Integer> targetMap = new HashMap<>();
        double avgTranslationSpeed = 0;

        for (Document document : documents) {
            updateMap(sourceMap, (String) document.get("source"));
            updateMap(targetMap, (String) document.get("target"));
            avgTranslationSpeed += (double) document.get("translationSpeed");
        }

        String mostPopularSource = maxFrequency(sourceMap);
        String mostPopularTarget = maxFrequency(targetMap);
        avgTranslationSpeed /= documents.size();

        request.setAttribute("mostPopularSource", mostPopularSource);
        request.setAttribute("mostPopularTarget", mostPopularTarget);
        request.setAttribute("documents", documents);
        request.setAttribute("avgTranslationSpeed", (int) avgTranslationSpeed);

        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Updates the given map with new frequency after the addition of the word.
     *
     * @param map  Hashmap of source/target
     * @param word word entered
     */
    private void updateMap(Map<String, Integer> map, String word) {
        int frequency = map.getOrDefault(word, 0);
        map.put(word, frequency + 1);
    }

    /**
     * Calculates the word with the maximum frequency in the map.
     *
     * @param map input map
     * @return word with the maximum frequency
     */
    private String maxFrequency(Map<String, Integer> map) {
        int max = Integer.MIN_VALUE;
        StringBuilder popString = new StringBuilder();

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() >= max) {
                max = entry.getValue();
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() == max) {
                popString.append(entry.getKey()).append(", ");
            }
        }

        if (popString.length() > 0) {
            popString.deleteCharAt(popString.length() - 1);
            popString.deleteCharAt(popString.length() - 1);
        }

        return popString.toString();
    }
}

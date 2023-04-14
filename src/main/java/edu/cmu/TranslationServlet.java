/**
 * Author: Karthik Manjunath
 * Andrew ID: kmanjuna
 */
package edu.cmu;

import com.google.gson.Gson;
import edu.cmu.model.MongoDocument;
import edu.cmu.model.TranslationResponse;
import edu.cmu.service.MongoService;
import edu.cmu.service.TranslateService;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(name = "TranslationServlet", urlPatterns = {"/translate"})
public class TranslationServlet extends HttpServlet {
    double translationSpeed;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        //Get parameters from the client
        String query = request.getParameter("query");
        String source = request.getParameter("source");
        String target = request.getParameter("target");

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String timeOfTranslation = formatter.format(date);

        double startTime = System.currentTimeMillis();

        // Get the translated String
        String translatedString = TranslateService.translate(query, source, target);

        double endTime = System.currentTimeMillis();
        translationSpeed = endTime - startTime;

        MongoDocument document = new MongoDocument
                (query, source, target, translatedString, (int) translationSpeed, timeOfTranslation);
        MongoService.insertDocument(document);

        TranslationResponse translationResponse = new TranslationResponse();
        translationResponse.setTranslatedText(translatedString);

        // Set the content type of the response to application/json
        response.setContentType("application/json");

        response.setCharacterEncoding("UTF-8");

        // Get the PrintWriter object from the response
        PrintWriter out = response.getWriter();

        // Convert the translationResponse object to a JSON string
        Gson gson = new Gson();
        String json = gson.toJson(translationResponse);

        // Write the JSON string to the PrintWriter
        out.print(json);
        out.flush();
    }
}

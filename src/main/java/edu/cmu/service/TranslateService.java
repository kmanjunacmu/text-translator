/**
 * Author: Karthik Manjunath
 * Andrew ID: kmanjuna
 */
package edu.cmu.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import edu.cmu.model.ApiRequest;
import edu.cmu.model.Json;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class TranslateService {

    /**
     * API Key to access Google Translation API.
     */
    private static final String API_KEY = "";
    /**
     * Endpoint of the Google Translation API.
     */
    private static final String END_POINT = "https://translation.googleapis.com/language/translate/v2";

    /**
     * Map to store the mapping from language name to code.
     */
    private static final Map<String, String> languageMap = new HashMap<>();

    /**
     * Translate the query string from source language to target language.
     *
     * @param query  String to be translated
     * @param source source language
     * @param target target language
     * @return translated string
     */
    public static String translate(String query, String source, String target) {
        String translatedText = "";

        try {
            String endpoint = END_POINT + "?" + "key=" + API_KEY;
            URL url = new URL(endpoint);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            createCodeMap();

            String src = languageMap.get(source);
            String tar = languageMap.get(target);

            ApiRequest apiRequest = new ApiRequest(query, src, tar);
            String jsonRequestBody = new Gson().toJson(apiRequest);

            OutputStream os = conn.getOutputStream();
            os.write(jsonRequestBody.getBytes());
            os.flush();
            os.close();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(reader, JsonObject.class);
            translatedText = jsonObject.get("data").getAsJsonObject()
                    .get("translations").getAsJsonArray()
                    .get(0).getAsJsonObject()
                    .get("translatedText").getAsString();

            conn.disconnect();
        } catch (MalformedURLException exception) {
            System.out.println("Malformed URL : " + END_POINT);
            exception.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return translatedText;
    }

    /**
     * Fills the map with language name to code mappings.
     */
    private static void createCodeMap() {
        Gson gson = new Gson();

        StringBuilder sb = new StringBuilder();
        try {
            ClassLoader classLoader = TranslateService.class.getClassLoader();
            URL resourceUrl = classLoader.getResource("codes.json");

            BufferedReader br = new BufferedReader(new FileReader(resourceUrl.getFile()));
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();
            String jsonString = sb.toString();

            Json[] jsonArray = gson.fromJson(jsonString, Json[].class);
            for (Json jsonObject : jsonArray) {
                String name = jsonObject.getName();
                String code = jsonObject.getCode();
                languageMap.put(name, code);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

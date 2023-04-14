/**
 * Author: Karthik Manjunath
 * Andrew ID: kmanjuna
 */
package edu.cmu.model;

public class ApiRequest {
    /**
     * Query text to be translated
     */
    private String q;

    /**
     * Source language
     */
    private String source;

    /**
     * Target language
     */
    private String target;

    /**
     * Constructor to set the parameters of the API request
     *
     * @param q      query String
     * @param source source language
     * @param target target language
     */
    public ApiRequest(String q, String source, String target) {
        this.q = q;
        this.source = source;
        this.target = target;
    }

    /**
     * Retrieves the string to be translated.
     *
     * @return query string
     */
    public String getQ() {
        return q;
    }

    /**
     * Sets the query string.
     *
     * @param q string to be translated
     */
    public void setQ(String q) {
        this.q = q;
    }

    /**
     * Retrieves the source language.
     *
     * @return source language
     */
    public String getSource() {
        return source;
    }

    /**
     * Sets the source language.
     *
     * @param source source language
     */
    public void setSource(String source) {
        this.source = source;
    }

    /**
     * Retrieves the target language.
     *
     * @return target language
     */
    public String getTarget() {
        return target;
    }

    /**
     * Sets the target language
     *
     * @param target target language
     */
    public void setTarget(String target) {
        this.target = target;
    }
}

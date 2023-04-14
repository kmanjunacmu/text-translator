/**
 * Author: Karthik Manjunath
 * Andrew ID: kmanjuna
 */
package edu.cmu.model;

public class MongoDocument {
    /**
     * Input text entered by the user.
     */
    private String inputText;

    /**
     * Source language chosen by the user.
     */
    private String source;

    /**
     * Target language chosen by the user.
     */
    private String target;

    /**
     * Text after translation.
     */
    private String translatedText;

    /**
     * Time taken to translate the text.
     */
    private double translationSpeed;

    /**
     * Time of translation.
     */
    private String timeOfTranslation;

    /**
     * Constructor to initialize a Mongo document.
     *
     * @param inputText        input text
     * @param source           source language
     * @param target           target language
     * @param translatedText   translated text
     * @param translationSpeed translation speed
     */
    public MongoDocument(String inputText, String source, String target, String translatedText, double translationSpeed, String timeOfTranslation) {
        this.inputText = inputText;
        this.source = source;
        this.target = target;
        this.translatedText = translatedText;
        this.translationSpeed = translationSpeed;
        this.timeOfTranslation = timeOfTranslation;
    }

    /**
     * Retrieves input text.
     *
     * @return input text
     */
    public String getInputText() {
        return inputText;
    }

    /**
     * Sets the input text.
     *
     * @param inputText input text
     */
    public void setInputText(String inputText) {
        this.inputText = inputText;
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
     * Sets the target language.
     *
     * @param target target language
     */
    public void setTarget(String target) {
        this.target = target;
    }

    /**
     * Retrieves the translated text
     *
     * @return translated text
     */
    public String getTranslatedText() {
        return translatedText;
    }

    /**
     * Sets the translated text.
     *
     * @param translatedText translated text
     */
    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }

    /**
     * Retrieves the translation speed.
     *
     * @return translation speed
     */
    public double getTranslationSpeed() {
        return translationSpeed;
    }

    /**
     * Sets the translation speed.
     *
     * @param translationSpeed translation speed
     */
    public void setTranslationSpeed(double translationSpeed) {
        this.translationSpeed = translationSpeed;
    }

    /**
     * Retrieves the time of translation
     *
     * @return time of translation
     */
    public String getTimeOfTranslation() {
        return timeOfTranslation;
    }

    /**
     * Sets the time of translation
     *
     * @param timeOfTranslation time of translation
     */
    public void setTimeOfTranslation(String timeOfTranslation) {
        this.timeOfTranslation = timeOfTranslation;
    }
}

/**
 * Author: Karthik Manjunath
 * Andrew ID: kmanjuna
 */
package edu.cmu.model;

/**
 * Model to capture the translation response.
 */
public class TranslationResponse {
    /**
     * A string containing the text translated from the Google API.
     */
    private String translatedText;

    /**
     * Retrieves the translated text.
     *
     * @return translated text
     */
    public String getTranslatedText() {
        return translatedText;
    }

    /**
     * A method to set the translated text.
     *
     * @param translatedText translated text
     */
    public void setTranslatedText(String translatedText) {
        this.translatedText = translatedText;
    }
}

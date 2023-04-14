/**
 * Author: Karthik Manjunath
 * Andrew ID: kmanjuna
 */
package edu.cmu.model;

public class Json {
    /**
     * ISO-639 1 codes for languages.
     */
    private String code;

    /**
     * Name of the language.
     */
    private String name;

    /**
     * Retrieve the language code
     *
     * @return ISO code
     */
    public String getCode() {
        return code;
    }

    /**
     * Sets ISO language code.
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Retrieves language name.
     *
     * @return language name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the language name.
     *
     * @param name name of the language
     */
    public void setName(String name) {
        this.name = name;
    }
}

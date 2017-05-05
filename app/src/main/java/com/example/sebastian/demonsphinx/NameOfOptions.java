package com.example.sebastian.demonsphinx;

/**
 * Klasa przechowywująca informacje o nazwie opcji w ustawieniach oraz jej opisu.
 */

public class NameOfOptions {
    /**
     * Prywatne zmienne przechowywujące odpowiednio nazwę opcji oraz jej opis.
     */
    private String title;
    private String description;

    /**
     * Dwuargumentowy konstruktor inicjalizujący zmienne klasy.
     */
    public NameOfOptions(String title, String description) {
        this.title = title;
        this.description = description;
    }

    /**
     *Metoda zwracająca nazwę opcji.
     */
    public String getTitle() {
        return title;
    }

    /**
     *Metoda zwracająa opis opcji.
     */
    public String getDescription() {
        return description;
    }
}

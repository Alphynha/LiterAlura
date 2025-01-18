package br.com.alura.LiterAlura.model;

public enum Idioma {
    pt("Português"),
    en("Inglês"),
    es("Espanhol"),
    fr("Francês"),
    nd("Indisponível"),
    ;

    private String idiomas;

    Idioma(String idiomas) {
        this.idiomas = idiomas;
    }

    public static Idioma stringToEnum(String idioma) {
        for (Idioma item: Idioma.values()) {
            if (item.name().equalsIgnoreCase(idioma)) {
                return item;
            }
        }
        return nd;
    }

    public static void listarIdiomas() {
        for (Idioma idioma: Idioma.values()) {
            System.out.println(idioma.name()+ " - " + idioma.getIdiomas());
        }
    }

    public String getIdiomas() {
        return idiomas;
    }
}

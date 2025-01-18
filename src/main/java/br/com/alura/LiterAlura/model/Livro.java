package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Entity
@Table(name = "livros")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String titulo;

    @ManyToOne
    @ToString.Exclude
    private Autor autor;

    @Enumerated(EnumType.STRING)
    private Idioma idioma;

    private Integer downloads;

    public Livro(String titulo, Idioma idioma, Integer downloads) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.downloads = downloads;
    }

    public Livro(LivroRecord livro) {
        this.titulo = livro.titulo();

        Optional<AutorRecord> autorRecord = livro.autores().stream().findFirst();
        autorRecord.ifPresent(record -> this.autor = new Autor(record));

        Optional<String> idiomaString = livro.idiomas().stream().findFirst();
        idiomaString.ifPresent(s -> this.idioma = Idioma.stringToEnum(s));

        this.downloads = livro.downloads();
    }

    public void printarInformacoes() {
        System.out.println("Livro");
        System.out.println("Título: " + titulo);
        System.out.println("Autor: " + (autor != null ? autor.getNome() : "Desconhecido"));
        System.out.println("Idioma: " + (idioma != null ? idioma.getIdiomas() : "Desconhecido"));
        System.out.println("Número de downloads: " + downloads);
        System.out.println("");
    }
}

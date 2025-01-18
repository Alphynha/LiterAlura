package br.com.alura.LiterAlura.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Entity
@Table( name = "autores")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String nome;

    private Integer anoNascimento;

    private Integer anoFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @ToString.Exclude
    private List<Livro> livros;

    public Autor(AutorRecord autor) {
        this.nome = autor.nome();
        this.anoNascimento = autor.anoNascimento();
        this.anoFalecimento = autor.anoFalecimento();
    }

    public void printInformacoes() {
        System.out.println("Autor");
        System.out.println("Nome:" + nome);
        System.out.println("Ano de Nascimento: "+anoNascimento);
        System.out.println("Ano de falecimento: "+anoFalecimento);
        System.out.println("Livros: "+livros);
        System.out.println("");
    }
}
package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Idioma;
import br.com.alura.LiterAlura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    @Query("SELECT livro FROM Livro livro WHERE LOWER(livro.titulo) LIKE LOWER(:nome)")
    Optional<Livro> obterLivroPeloNome(String nome);

    @Query("SELECT livro FROM Livro livro WHERE livro.idioma=:idioma")
    List<Livro> obterLivroPeloIdioma(Idioma idioma);
}

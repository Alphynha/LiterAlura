package br.com.alura.LiterAlura.repository;

import br.com.alura.LiterAlura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor, Long> {

    @Query("SELECT autor FROM Autor autor WHERE LOWER(autor.nome) LIKE LOWER(:nome)")
    Optional<Autor> obterAutorPeloNome(String nome);

    @Query("SELECT autor FROM Autor autor WHERE :ano>=autor.anoNascimento AND :ano<autor.anoFalecimento")
    List<Autor> obterAutoresVivosPeloAno(int ano);
}

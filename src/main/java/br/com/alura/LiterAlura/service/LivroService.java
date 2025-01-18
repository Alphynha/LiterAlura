package br.com.alura.LiterAlura.service;

import br.com.alura.LiterAlura.model.Idioma;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {

    private LivroRepository livroRepository;

    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public LivroService() {};

    public List<Livro> obterTodosOsLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> obterLivroPeloNome(String nome) {
        return livroRepository.obterLivroPeloNome(nome);
    }

    public List<Livro> obterLivrosPeloIdioma(Idioma idioma) {
        return livroRepository.obterLivroPeloIdioma(idioma);
    }

    public void salvarLivro(Livro livro) {
        livroRepository.save(livro);
    }
}

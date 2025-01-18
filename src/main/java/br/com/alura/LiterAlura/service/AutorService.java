package br.com.alura.LiterAlura.service;

import br.com.alura.LiterAlura.model.Autor;
import br.com.alura.LiterAlura.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    public AutorRepository autorRepository;

    @Autowired
    public AutorService(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public AutorService() {}

    public Optional<Autor> obterAutorPeloNome(String nome) {
        return autorRepository.obterAutorPeloNome(nome);
    }

    public void salvarAutor(Autor autor) {
        autorRepository.save(autor);
    }

    public List<Autor> obterTodosAutores() {
        return autorRepository.findAll();
    }

    public List<Autor> obterAutoresVivosNoAno(int ano) {
        return autorRepository.obterAutoresVivosPeloAno(ano);
    }
}

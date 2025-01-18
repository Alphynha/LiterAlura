package br.com.alura.LiterAlura.service;

import br.com.alura.LiterAlura.Api.ConsultaApi;
import br.com.alura.LiterAlura.model.Autor;
import br.com.alura.LiterAlura.model.Idioma;
import br.com.alura.LiterAlura.model.Livro;
import br.com.alura.LiterAlura.model.LivroRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MenuService {

    private ConsultaApi consultaApi;

    private Scanner tcl;

    private AutorService autorService;

    private JsonParser jsonParser;

    private LivroService livroService;

    @Autowired
    public MenuService(LivroService livroService, AutorService autorService, JsonParser jsonParser) {
        this.consultaApi = new ConsultaApi();
        this.tcl = new Scanner(System.in);
        this.livroService = livroService;
        this.autorService = autorService;
        this.jsonParser = jsonParser;
    }

    public void salvarLivro() {
        List<LivroRecord> livrosObtidos = obterLivrosApi();

        if (livrosObtidos.isEmpty()) {
            System.out.println("Não encontramos livros correspondentes");
            return;
        }

        System.out.println("Escolha um livro para salvar [0 - Cancelar Operação]");
        for (int i = 0; i < livrosObtidos.size(); i++) {
            LivroRecord livro = livrosObtidos.get(i);

            String nomeAutor = livro.autores().isEmpty() ? "Autor desconhecido" : livro.autores().get(0).nome();
            System.out.println((i + 1) + " - " + livro.titulo() + " - " + nomeAutor);
        }

        int opcao = tcl.nextInt();
        tcl.nextLine();
        if (opcao == 0) {
            return;
        }
        if (opcao < 1 || opcao > livrosObtidos.size()) {
            System.out.println("Erro: Você escolheu uma opção inválida");
            return;
        }

        LivroRecord livroRecord = livrosObtidos.get(opcao - 1);
        Optional<Livro> livroApi = livroService.obterLivroPeloNome(livroRecord.titulo());
        Optional<Autor> autorApi = Optional.empty();
        if (!livroRecord.autores().isEmpty()) {
            autorApi = autorService.obterAutorPeloNome(livroRecord.autores().get(0).nome());
        }

        if (livroApi.isPresent()) {
            System.out.println("Erro: Livro já registrado");
            return;
        }

        Livro livro = new Livro(livroRecord);

        if (!autorApi.isPresent() && livro.getAutor() != null) {
            Autor autorNovo = livro.getAutor();
            autorService.salvarAutor(autorNovo);
        }

        livroService.salvarLivro(livro);
    }

    public List<LivroRecord> obterLivrosApi() {
        System.out.println("Digite o título do livro [0 - Para cancelar operação]: ");
        String titulo = tcl.nextLine();
        if (titulo.equals("0")) {
            return Collections.emptyList();
        }

        List<LivroRecord> livrosObtidos;
        livrosObtidos = jsonParser.converterLivros(consultaApi.obterDados(titulo));
        return livrosObtidos;
    }

    public void listarLivrosRegistrados() {
        List<Livro> livros = livroService.obterTodosOsLivros();
        livros.forEach(livro -> livro.printarInformacoes());
    }

    public void listarAutoresRegistrados() {
        List<Livro> livros = livroService.obterTodosOsLivros();
        livros.forEach(livro -> livro.printarInformacoes());
    }

    public void listarAutoresVivosNoAno() {
        try {
            System.out.println("Digite o ano: ");
            int ano = tcl.nextInt();
            tcl.nextLine();
            List<Autor> autores = autorService.obterAutoresVivosNoAno(ano);
            autores.forEach(autor -> autor.printInformacoes());
        } catch (InputMismatchException e) {
            System.out.println("Erro: Digite um número válido!");
        }
    }

    public void listarLivrosPeloIdioma() {
        Idioma.listarIdiomas();
        System.out.println("Digite o código do idioma desejado [0 - Para cancelar operaçõa]: ");
        String idiomaUsuario = tcl.nextLine();
        if (idiomaUsuario.equals(0)) {
            return;
        }

        List<Livro> livros = livroService.obterLivrosPeloIdioma(Idioma.stringToEnum(idiomaUsuario));
        livros.forEach(livro -> livro.printarInformacoes());
    }
}

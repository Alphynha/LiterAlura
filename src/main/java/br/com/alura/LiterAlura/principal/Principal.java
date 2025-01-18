package br.com.alura.LiterAlura.principal;

import br.com.alura.LiterAlura.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.InputMismatchException;
import java.util.Scanner;

@Component
public class Principal {

    private final MenuService menuService;

    @Autowired
    public Principal(MenuService menuService) {
        this.menuService = menuService;
    }

    public void iniciar() {
        String menu = """
                Bem vindo a LiterAlura!
                
                1-Buscar livro por título
                2-Listar livros registrados
                3-Listar autores registrados
                4-Listar autores vivos a partir de um ano
                5-Listar livros por idioma
                0-Sair

                Digite uma opção: """;

        Scanner teclado = new Scanner(System.in);
        int opcao;

        do {
            try {
                System.out.print(menu);
                opcao = teclado.nextInt();
                teclado.nextLine();
                switch (opcao) {
                    case 1 -> menuService.salvarLivro();
                    case 2 -> menuService.listarLivrosRegistrados();
                    case 3 -> menuService.listarAutoresRegistrados();
                    case 4 -> menuService.listarAutoresVivosNoAno();
                    case 5 -> menuService.listarLivrosPeloIdioma();
                    case 0 -> System.out.println("Encerrando aplicação...");
                    default -> System.out.println("Opção inválida");
                }
            } catch (InputMismatchException e) {
                System.out.println("Erro: Digite um número");
                opcao = -1;
                teclado.nextLine();
            }
        } while (opcao != 0);

        teclado.close();
    }
}

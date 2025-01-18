package br.com.alura.LiterAlura;

import br.com.alura.LiterAlura.principal.Principal;
import br.com.alura.LiterAlura.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiterAluraApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(LiterAluraApplication.class, args);
	}

	@Autowired
	private MenuService menuService;

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(menuService);
		principal.iniciar();
	}
}

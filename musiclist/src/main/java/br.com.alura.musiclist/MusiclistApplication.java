package br.com.alura.musiclist;

import br.com.alura.musiclist.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MusiclistApplication implements CommandLineRunner {

    public static void main(String[] args) {
		SpringApplication.run(MusiclistApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Principal principal = new Principal();
        principal.exibeMenu();
    }
}

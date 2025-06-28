package br.com.alura.musiclist.principal;

import br.com.alura.musiclist.model.Artista;
import br.com.alura.musiclist.model.Musica;
import br.com.alura.musiclist.repository.MusicaRepository;

import java.util.Scanner;

public class Principal {

    Scanner leitura = new Scanner(System.in);
    private MusicaRepository repositorioMusic;

    public void exibeMenu(){

        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    *** Menu MusicList ***
                   
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                   
                    0 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }

    private void cadastrarArtistas() {

    }

    private void cadastrarMusicas() {

    }

    private void listarMusicas() {
    }

    private void buscarMusicasPorArtista() {
    }

    private void pesquisarDadosDoArtista() {
    }
}

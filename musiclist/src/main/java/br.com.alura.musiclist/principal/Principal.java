package br.com.alura.musiclist.principal;

import br.com.alura.musiclist.model.Artista;
import br.com.alura.musiclist.model.Musica;
import br.com.alura.musiclist.model.TipoArtista;
import br.com.alura.musiclist.repository.ArtistaRepository;
import br.com.alura.musiclist.repository.MusicaRepository;
import br.com.alura.musiclist.service.MusicBrainzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

@Component
public class Principal {

    Scanner leitura = new Scanner(System.in);
    private MusicaRepository repositorioMusic;
    private final ArtistaRepository repositorioArtist;

    @Autowired
    public Principal(ArtistaRepository repositorioArtist, MusicaRepository repositorioMusic) {
        this.repositorioArtist = repositorioArtist;
        this.repositorioMusic = repositorioMusic;
    }

    public void exibeMenu(){

        var opcao = -1;

        while (opcao != 0) {
            var menu = """
                    *** Menu MusicList ***
                   
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Listar artistas
                    5- Buscar músicas por artistas
                    6- Pesquisar dados sobre um artista
                   
                    0 - Sair
                    """;

            System.out.println("\n" + menu);
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
                    listarArtistas();
                    break;
                case 5:
                    buscarMusicasPorArtista();
                    break;
                case 6:
                    deletarArtista();
                    break;
                case 7:
                    pesquisarDadosDoArtista();
                    break;
                case 0:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }

    }

    private void cadastrarArtistas() {
        var cadastrarNovo = "S";
        while (cadastrarNovo.equalsIgnoreCase("s")){
            System.out.println("Digite o nome do artista:");
            var nomeCantor = leitura.nextLine();
            System.out.println("Escolha o tipo do artista (solo,dupla,banda): ");
            var tipoStr = leitura.nextLine();

            try {
                TipoArtista tipo = TipoArtista.valueOf(tipoStr.toUpperCase());
                Artista artista = new Artista(nomeCantor, tipo);
                repositorioArtist.save(artista);
                System.out.println(artista.toString());
            } catch (IllegalArgumentException e) {
                System.out.println("Tipo inválido. Use: SOLO, DUPLA ou BANDA.");
            }

            System.out.println("Cadastrar novo artista? (S/N)");
            cadastrarNovo = leitura.nextLine();
        }

    }

    private void cadastrarMusicas() {
        System.out.println("Cadastrar a música de qual artista cadastrado:");
        var nomeCantor = leitura.nextLine();
        Optional<Artista> artista = repositorioArtist.findByNomeContainingIgnoreCase(nomeCantor);
        if(artista.isPresent()){
            System.out.println("Informe o titulo da música: ");
            var titulo = leitura.nextLine();
            Musica musica = new Musica(titulo);
            musica.setArtista(artista.get());
            artista.get().getMusicas().add(musica);
            repositorioMusic.save(musica);
            System.out.println(musica.toString());
        }
        else{
            System.out.println("Não foi possivel salvar musica. Artista não encontrado.");
        }
    }

    private void listarMusicas() {
        List<Musica> musicas = repositorioMusic.findAll();

        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música salva.");
            return;
        }
        else {
            System.out.println("\n*** Lista de músicas salvas ***");
            musicas.forEach(m -> System.out.println("- " + m.getTitulo() + " | Artista: " + m.getArtista().getNome()));
        }
    }

    private void listarArtistas() {
        List<Artista> artistas = repositorioArtist.findAll();

        if (artistas.isEmpty()) {
            System.out.println("Nenhum artista salvo.");
            return;
        }
        else {
            System.out.println("\n*** Lista de artistas salvos ***");
            artistas.forEach(c -> System.out.println("- " + c.toString()));
        }
    }

    private void buscarMusicasPorArtista() {
        System.out.print("Digite o nome do artista: ");
        String nomeArtista = leitura.nextLine();

        List<Musica> musicas = repositorioMusic.findByArtistaNomeIgnoreCase(nomeArtista);

        if (musicas.isEmpty()) {
            System.out.println("Nenhuma música encontrada para esse artista.");
            return;
        }

        System.out.println("\n Músicas de " + nomeArtista + ":");
        musicas.forEach(m -> System.out.println("- " + m.getTitulo()));

    }
    private void deletarArtista(){
        listarArtistas();
        System.out.print("Digite o nome do artista que deseja deletar: ");
        String nome = leitura.nextLine();

        Optional<Artista> artistaOpt = repositorioArtist.findByNomeContainingIgnoreCase(nome);

        if (artistaOpt.isPresent()) {
            Artista artista = artistaOpt.get();

            System.out.println("Tem certeza que deseja excluir o artista \"" + artista.getNome() + "\"? (s/n)");
            String confirmacao = leitura.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                repositorioArtist.delete(artista);
                System.out.println("Artista excluído com sucesso!");
            } else {
                System.out.println("Exclusão cancelada.");
            }
        } else {
            System.out.println("Artista não encontrado.");
        }
    }

    private void pesquisarDadosDoArtista() {
        System.out.print("Digite o nome do artista para buscar no MusicBrainz: ");
        String nome = leitura.nextLine();

        MusicBrainzService service = new MusicBrainzService();
        service.buscarArtista(nome);
    }
}

package br.com.alura.musiclist.repository;

import br.com.alura.musiclist.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

    //listar musicas de um artista especifico
    List<Musica> findByArtistaNomeIgnoreCase(String nome);

}

package br.com.alura.musiclist.repository;

import br.com.alura.musiclist.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MusicaRepository extends JpaRepository<Musica, Long> {

}

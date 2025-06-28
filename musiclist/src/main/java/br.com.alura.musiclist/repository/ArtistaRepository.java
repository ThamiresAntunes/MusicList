package br.com.alura.musiclist.repository;

import br.com.alura.musiclist.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
}

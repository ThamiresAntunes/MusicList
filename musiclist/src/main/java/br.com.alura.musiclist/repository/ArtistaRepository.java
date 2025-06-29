package br.com.alura.musiclist.repository;

import br.com.alura.musiclist.model.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long> {
    //buscar artista pelo nome
    Optional<Artista> findByNomeContainingIgnoreCase(String nomeCantor);
}

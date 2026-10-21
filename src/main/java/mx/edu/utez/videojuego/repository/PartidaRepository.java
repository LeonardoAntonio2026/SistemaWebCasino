package mx.edu.utez.videojuego.repository;
import mx.edu.utez.videojuego.dto.ObtenerHistorialDTO;
import mx.edu.utez.videojuego.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    List<Partida> findByJugadorId(Long jugadorId);

}

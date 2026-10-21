package mx.edu.utez.videojuego.repository;
import mx.edu.utez.videojuego.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long> {}

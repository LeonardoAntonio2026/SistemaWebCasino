package mx.edu.utez.videojuego.repository;
import mx.edu.utez.videojuego.model.Tiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TiroRepository extends JpaRepository<Tiro, Long> {}

package mx.edu.utez.videojuego.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IniciarPartidaRequestDTO {
    @NotNull(message = "El ID del jugador es obligatorio para iniciar la partida.")
    private Long jugadorId;

    private int apuesta;
}

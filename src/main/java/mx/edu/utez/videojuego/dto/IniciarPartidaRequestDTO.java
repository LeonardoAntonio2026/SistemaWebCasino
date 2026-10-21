package mx.edu.utez.videojuego.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class IniciarPartidaRequestDTO {
    @NotNull(message = "El ID del jugador es obligatorio para iniciar la partida.")
    private Long jugadorId;

    @Min(value = 1, message = "La apuesta debe ser mayor a 0.")
    private int apuesta;
}

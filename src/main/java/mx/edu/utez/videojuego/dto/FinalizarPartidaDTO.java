package mx.edu.utez.videojuego.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FinalizarPartidaDTO {
    @NotNull(message = "El ID de la partida es obligatorio.")
    private Long partidaId;
}

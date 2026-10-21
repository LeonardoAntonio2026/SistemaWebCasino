package mx.edu.utez.videojuego.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RealizarTiroDTO {
    @NotNull(message = "El ID de la partida es obligatorio para realizar el tiro.")
    private Long partidaId;
}

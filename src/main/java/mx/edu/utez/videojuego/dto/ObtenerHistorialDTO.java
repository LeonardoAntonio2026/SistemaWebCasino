package mx.edu.utez.videojuego.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ObtenerHistorialDTO {
    @NotNull(message = "El ID del jugador es obligatorio.")
    private Long jugadorId;
}

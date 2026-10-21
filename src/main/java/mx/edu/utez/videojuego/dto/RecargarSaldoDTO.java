package mx.edu.utez.videojuego.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RecargarSaldoDTO {
    @NotNull(message = "El ID del jugador es obligatorio.")
    private Long jugadorId;

    @Min(value = 1, message = "El saldo a recargar debe ser mayor a 0.")
    private int saldo;
}

package mx.edu.utez.videojuego.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "jugador")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Jugador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private int saldo;
    private boolean activo;
}

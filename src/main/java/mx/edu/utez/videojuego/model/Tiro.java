package mx.edu.utez.videojuego.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tiro")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Partida partida;

    private int valorDado1;
    private int valorDado2;

    private int suma;

    private boolean esGanador;
}

package mx.edu.utez.videojuego.service;

import mx.edu.utez.videojuego.dto.RecargarSaldoDTO;
import mx.edu.utez.videojuego.exception.ResourceNotFoundException;
import mx.edu.utez.videojuego.model.Jugador;
import mx.edu.utez.videojuego.repository.JugadorRepository;
import org.springframework.stereotype.Service;

@Service
public class RecargarSaldoService {
    private final JugadorRepository jugadorRepository;

    public RecargarSaldoService(JugadorRepository jugadorRepository) {
        this.jugadorRepository = jugadorRepository;
    }

    public Jugador RecargarSaldo(RecargarSaldoDTO dto){
        Jugador jugadorActual = jugadorRepository.findById(dto.getJugadorId()).orElseThrow(() -> new ResourceNotFoundException("El jugador con ID " + dto.getJugadorId() + " no existe."));
        jugadorActual.setSaldo(jugadorActual.getSaldo() + dto.getSaldo());
        return jugadorRepository.save(jugadorActual);
    }
}

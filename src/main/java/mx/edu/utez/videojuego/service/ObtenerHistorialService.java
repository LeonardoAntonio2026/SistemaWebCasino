package mx.edu.utez.videojuego.service;

import mx.edu.utez.videojuego.dto.ObtenerHistorialDTO;
import mx.edu.utez.videojuego.exception.ResourceNotFoundException;
import mx.edu.utez.videojuego.model.Partida;
import mx.edu.utez.videojuego.repository.JugadorRepository;
import mx.edu.utez.videojuego.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObtenerHistorialService {
    private final PartidaRepository partidaRepository;
    private final JugadorRepository jugadorRepository;

    public ObtenerHistorialService(PartidaRepository partidaRepository, JugadorRepository jugadorRepository) {
        this.partidaRepository = partidaRepository;
        this.jugadorRepository = jugadorRepository;
    }

    public Partida[] ObtenerHistorial(ObtenerHistorialDTO dto) {
        jugadorRepository.findById(dto.getJugadorId())
                .orElseThrow(() -> new ResourceNotFoundException("El jugador con ID " + dto.getJugadorId() + " no existe."));

        List<Partida> partidas = partidaRepository.findByJugadorId(dto.getJugadorId());

        return partidas.toArray(new Partida[0]);
    }

}

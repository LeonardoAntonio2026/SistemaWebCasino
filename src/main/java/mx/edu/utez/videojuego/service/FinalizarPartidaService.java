package mx.edu.utez.videojuego.service;

import mx.edu.utez.videojuego.dto.FinalizarPartidaDTO;
import mx.edu.utez.videojuego.exception.ResourceNotFoundException;
import mx.edu.utez.videojuego.model.Jugador;
import mx.edu.utez.videojuego.model.Partida;
import mx.edu.utez.videojuego.repository.PartidaRepository;
import org.springframework.stereotype.Service;

@Service
public class FinalizarPartidaService {
    private final PartidaRepository partidaRepository;

    public FinalizarPartidaService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    public Partida FinalizarPartida(FinalizarPartidaDTO dto){


        Partida partidaActual = partidaRepository.findById(dto.getPartidaId())
                .orElseThrow(() -> new ResourceNotFoundException("El jugador con ID " + dto.getPartidaId() + " no existe."));

        partidaActual.setEstado("FINALIZADA");

        return partidaRepository.save(partidaActual);
    }
}

package mx.edu.utez.videojuego.service;

import mx.edu.utez.videojuego.dto.FinalizarPartidaDTO;
import mx.edu.utez.videojuego.exception.BadRequestException;
import mx.edu.utez.videojuego.exception.ResourceNotFoundException;
import mx.edu.utez.videojuego.model.EstadoPartida;
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
                .orElseThrow(() -> new ResourceNotFoundException("La partida con ID " + dto.getPartidaId() + " no existe."));

        if (partidaActual.getEstado() != EstadoPartida.EN_JUEGO){
            throw new BadRequestException("La partida con ID " + dto.getPartidaId() + " no esta en curso.");
        }

        partidaActual.setEstado(EstadoPartida.FINALIZADA);

        return partidaRepository.save(partidaActual);
    }
}

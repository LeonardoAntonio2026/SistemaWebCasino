package mx.edu.utez.videojuego.service;

import mx.edu.utez.videojuego.dto.IniciarPartidaRequestDTO;
import mx.edu.utez.videojuego.exception.BadRequestException;
import mx.edu.utez.videojuego.exception.ResourceNotFoundException;
import mx.edu.utez.videojuego.model.EstadoPartida;
import mx.edu.utez.videojuego.model.Jugador;
import mx.edu.utez.videojuego.model.Partida;
import mx.edu.utez.videojuego.repository.JugadorRepository;
import mx.edu.utez.videojuego.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class IniciarPartidaService {

    private final PartidaRepository partidaRepository;
    private final JugadorRepository jugadorRepository;

    public IniciarPartidaService(PartidaRepository partidaRepository, JugadorRepository jugadorRepository) {
        this.partidaRepository = partidaRepository;
        this.jugadorRepository = jugadorRepository;
    }

    public Partida procesarInicioPartida(IniciarPartidaRequestDTO dto) {
        Jugador jugador = jugadorRepository.findById(dto.getJugadorId())
                .orElseThrow(() -> new ResourceNotFoundException("El jugador con ID " + dto.getJugadorId() + " no existe."));

        if (!jugador.isActivo()){
            throw new BadRequestException("El jugador con ID " + dto.getJugadorId() + " no esta activo.");
        }

        if (jugador.getSaldo() < dto.getApuesta()){
            throw new BadRequestException("No hay saldo suficiente");
        }

        jugador.setSaldo(jugador.getSaldo() - dto.getApuesta());

        Partida partida = new Partida();

        partida.setJugador(jugador);
        partida.setFecha(LocalDate.now().toString());
        partida.setApuesta(dto.getApuesta());
        partida.setEstado(EstadoPartida.EN_JUEGO);

        jugadorRepository.save(jugador);
        return partidaRepository.save(partida);
    }
}

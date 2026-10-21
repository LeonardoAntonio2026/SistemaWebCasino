package mx.edu.utez.videojuego.service;

import mx.edu.utez.videojuego.dto.IniciarPartidaRequestDTO;
import mx.edu.utez.videojuego.exception.ResourceNotFoundException;
import mx.edu.utez.videojuego.model.Jugador;
import mx.edu.utez.videojuego.model.Partida;
import mx.edu.utez.videojuego.model.Tiro;
import mx.edu.utez.videojuego.repository.JugadorRepository;
import mx.edu.utez.videojuego.repository.PartidaRepository;
import mx.edu.utez.videojuego.repository.TiroRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class IniciarPartidaService {

    private final PartidaRepository partidaRepository;
    private final JugadorRepository jugadorRepository;
    private final TiroRepository tiroRepository;

    public IniciarPartidaService(PartidaRepository partidaRepository, JugadorRepository jugadorRepository, TiroRepository tiroRepository) {
        this.partidaRepository = partidaRepository;
        this.jugadorRepository = jugadorRepository;
        this.tiroRepository = tiroRepository;
    }

    public Partida procesarInicioPartida(IniciarPartidaRequestDTO dto) {
        Jugador jugador = jugadorRepository.findById(dto.getJugadorId())
                .orElseThrow(() -> new ResourceNotFoundException("El jugador con ID " + dto.getJugadorId() + " no existe."));

        if (jugador.getSaldo() < dto.getApuesta()){
            throw new ResourceNotFoundException("No hay saldo suficiente");
        }

        jugador.setSaldo(jugador.getSaldo() - dto.getApuesta());

        Partida partida = new Partida();


        partida.setJugador(jugador);
        partida.setFecha("");
        partida.setApuesta(dto.getApuesta());
        partida.setEstado("EN_JUEGO");

        Tiro nuevoTiro = new Tiro();
        nuevoTiro.setPartida(partida);



        jugadorRepository.save(jugador);

        tiroRepository.save(nuevoTiro);
        return partidaRepository.save(partida);
    }
}

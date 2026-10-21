package mx.edu.utez.videojuego.service;

import mx.edu.utez.videojuego.dto.RealizarTiroDTO;
import mx.edu.utez.videojuego.exception.BadRequestException;
import mx.edu.utez.videojuego.exception.ResourceNotFoundException;
import mx.edu.utez.videojuego.model.EstadoPartida;
import mx.edu.utez.videojuego.model.Jugador;
import mx.edu.utez.videojuego.model.Partida;
import mx.edu.utez.videojuego.model.Tiro;
import mx.edu.utez.videojuego.repository.JugadorRepository;
import mx.edu.utez.videojuego.repository.PartidaRepository;
import mx.edu.utez.videojuego.repository.TiroRepository;
import org.springframework.stereotype.Service;


import java.util.Random;

@Service
public class RealizarTiroService {
    Random random = new Random();

    private final PartidaRepository partidaRepository;
    private final JugadorRepository jugadorRepository;
    private final TiroRepository tiroRepository;

    public RealizarTiroService(PartidaRepository partidaRepository, JugadorRepository jugadorRepository, TiroRepository tiroRepository) {
        this.partidaRepository = partidaRepository;
        this.jugadorRepository = jugadorRepository;
        this.tiroRepository = tiroRepository;
    }

    public Tiro RealizarTiro(RealizarTiroDTO dto){
        Partida partidaActual = partidaRepository.findById(dto.getPartidaId())
                .orElseThrow(() -> new ResourceNotFoundException("La partida con ID " + dto.getPartidaId() + " no existe."));

        if (partidaActual.getEstado() != EstadoPartida.EN_JUEGO){
            throw new BadRequestException("La partida con ID " + dto.getPartidaId() + " no esta en juego.");
        }

        Jugador jugadorAcutal = partidaActual.getJugador();
        int valorRandom1 =  random.nextInt(6) + 1;
        int valorRandom2 = random.nextInt(6) + 1;
        int suma = valorRandom1 + valorRandom2;

        Tiro tiroActual = new Tiro();
        tiroActual.setPartida(partidaActual);
        tiroActual.setValorDado1(valorRandom1);
        tiroActual.setValorDado2(valorRandom2);
        tiroActual.setSuma(suma);

        if(suma == 7 || suma == 11){
            jugadorAcutal.setSaldo(jugadorAcutal.getSaldo() + partidaActual.getApuesta() * 2);
            partidaActual.setEstado(EstadoPartida.FINALIZADA);
            tiroActual.setEsGanador(true);
        } else if (suma == 2 || suma == 3 || suma == 12) {
            partidaActual.setEstado(EstadoPartida.FINALIZADA);

        }
        jugadorRepository.save(jugadorAcutal);
        partidaRepository.save(partidaActual);
        return tiroRepository.save(tiroActual);
    }
}

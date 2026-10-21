package mx.edu.utez.videojuego.service;

import mx.edu.utez.videojuego.dto.RealizarTiroDTO;
import mx.edu.utez.videojuego.exception.ResourceNotFoundException;
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
        Tiro tiroActual = tiroRepository.findById(dto.getTiroId())
                .orElseThrow(() -> new ResourceNotFoundException("El tiro con ID " + dto.getTiroId() + " no existe."));

        Partida partidaActual = tiroActual.getPartida();
        Jugador jugadorAcutal = partidaActual.getJugador();
        int valorRandom1 =  random.nextInt(6) + 1;
        int valorRandom2 = random.nextInt(6) + 1;
        int suma = valorRandom1 + valorRandom2;
        if(suma == 7 || suma == 11){
            jugadorAcutal.setSaldo(jugadorAcutal.getSaldo() + partidaActual.getApuesta() * 2);
            partidaActual.setEstado("FINALIZADA");
        } else if (suma == 2 || suma == 3 || suma == 12) {
            partidaActual.setEstado("FINALIZADA");

        }
        jugadorRepository.save(jugadorAcutal);
        partidaRepository.save(partidaActual);
        return tiroRepository.save(tiroActual);
    }
}

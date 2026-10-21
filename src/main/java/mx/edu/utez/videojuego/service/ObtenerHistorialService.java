package mx.edu.utez.videojuego.service;

import mx.edu.utez.videojuego.dto.ObtenerHistorialDTO;
import mx.edu.utez.videojuego.model.Partida;
import mx.edu.utez.videojuego.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ObtenerHistorialService {
    private final PartidaRepository partidaRepository;

    public ObtenerHistorialService(PartidaRepository partidaRepository) {
        this.partidaRepository = partidaRepository;
    }

    public Partida[] ObtenerHistorial(ObtenerHistorialDTO dto) {
        List<Partida> partidas = partidaRepository.findByJugadorId(dto.getJugadorId());

        return partidas.toArray(new Partida[0]);
    }

}

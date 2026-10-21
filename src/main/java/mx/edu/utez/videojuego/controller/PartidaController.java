package mx.edu.utez.videojuego.controller;

import mx.edu.utez.videojuego.dto.FinalizarPartidaDTO;
import mx.edu.utez.videojuego.dto.IniciarPartidaRequestDTO;
import mx.edu.utez.videojuego.dto.ObtenerHistorialDTO;
import mx.edu.utez.videojuego.model.Partida;
import mx.edu.utez.videojuego.model.Tiro;
import mx.edu.utez.videojuego.service.FinalizarPartidaService;
import mx.edu.utez.videojuego.service.IniciarPartidaService;
import mx.edu.utez.videojuego.service.ObtenerHistorialService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partida")
public class PartidaController {

    private final IniciarPartidaService iniciarPartidaService;
    private final FinalizarPartidaService finalizarPartidaService;
    private final ObtenerHistorialService obtenerHistorialService;
    public PartidaController(IniciarPartidaService iniciarPartidaService, FinalizarPartidaService finalizarPartidaService, ObtenerHistorialService obtenerHistorialService) {
        this.iniciarPartidaService = iniciarPartidaService;
        this.finalizarPartidaService = finalizarPartidaService;
        this.obtenerHistorialService = obtenerHistorialService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Partida> iniciarPartida(@Valid @RequestBody IniciarPartidaRequestDTO dto) {
        Partida partidaIniciada = iniciarPartidaService.procesarInicioPartida(dto);
        return ResponseEntity.ok(partidaIniciada);
    }

    @PostMapping("/finalizar")
    public ResponseEntity<Partida> finalizarPartida(@Valid @RequestBody FinalizarPartidaDTO dto){
        Partida partidaFinalizada = finalizarPartidaService.FinalizarPartida(dto);
        return  ResponseEntity.ok(partidaFinalizada);
    }

    @PostMapping("/historial")
    public ResponseEntity<Partida[]> obtenerHistorial(@Valid @RequestBody ObtenerHistorialDTO dto){
        Partida[] historial = obtenerHistorialService.ObtenerHistorial(dto);
        return ResponseEntity.ok(historial);
    }
}

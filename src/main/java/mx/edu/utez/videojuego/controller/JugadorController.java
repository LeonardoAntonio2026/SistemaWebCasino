package mx.edu.utez.videojuego.controller;

import jakarta.validation.Valid;
import mx.edu.utez.videojuego.dto.RecargarSaldoDTO;
import mx.edu.utez.videojuego.model.Jugador;
import mx.edu.utez.videojuego.service.RecargarSaldoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jugador")
public class JugadorController {
    private final RecargarSaldoService recargarSaldoService;

    public JugadorController(RecargarSaldoService recargarSaldoService) {
        this.recargarSaldoService = recargarSaldoService;
    }

    @PostMapping("/recargar")
    public ResponseEntity<Jugador> recargarSaldo(@Valid @RequestBody RecargarSaldoDTO dto){
        Jugador jugadorRecargado = recargarSaldoService.RecargarSaldo(dto);
        return ResponseEntity.ok(jugadorRecargado);
    }
}

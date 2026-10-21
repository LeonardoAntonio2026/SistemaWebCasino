package mx.edu.utez.videojuego.controller;

import jakarta.validation.Valid;
import mx.edu.utez.videojuego.dto.RealizarTiroDTO;
import mx.edu.utez.videojuego.model.Tiro;
import mx.edu.utez.videojuego.service.RealizarTiroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tiro")
public class TiroController {
    private final RealizarTiroService realizarTiroService;

    public TiroController(RealizarTiroService realizarTiroService) {
        this.realizarTiroService = realizarTiroService;
    }

    @PostMapping("/realizar")
    public ResponseEntity<Tiro> realizarTiro(@Valid @RequestBody RealizarTiroDTO dto){
        Tiro tiroRealizado = realizarTiroService.RealizarTiro(dto);
        return ResponseEntity.ok(tiroRealizado);
    }



}

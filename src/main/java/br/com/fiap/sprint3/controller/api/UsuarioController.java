package br.com.fiap.sprint3.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.sprint3.dto.auth.AuthResponse;
import br.com.fiap.sprint3.dto.usuario.UsuarioLoginRequest;
import br.com.fiap.sprint3.dto.usuario.UsuarioRequest;
import br.com.fiap.sprint3.dto.usuario.UsuarioResponse;
import br.com.fiap.sprint3.service.UsuarioService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> create(@Valid @RequestBody UsuarioRequest request) {
        AuthResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody UsuarioLoginRequest request) {
        AuthResponse response = service.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioResponse>> readAll() {
        List<UsuarioResponse> usuarios = service.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponse> read(@PathVariable Long id) {
        UsuarioResponse usuario = service.findById(id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponse> update(@Valid @RequestBody UsuarioRequest request,
            @PathVariable Long id) {
        UsuarioResponse usuario = service.update(request, id);
        return new ResponseEntity<>(usuario, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UsuarioResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
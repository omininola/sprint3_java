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

import br.com.fiap.sprint3.dto.moto.MotoRequest;
import br.com.fiap.sprint3.dto.moto.MotoResponse;
import br.com.fiap.sprint3.service.MotoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/motos")
public class MotoController {
    
    @Autowired
    private MotoService service;

    @PostMapping
    public ResponseEntity<MotoResponse> create(@Valid @RequestBody MotoRequest request) {
        MotoResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<MotoResponse>> readAll() {
        List<MotoResponse> response = service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MotoResponse> read(@PathVariable Long id) {
        MotoResponse response = service.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MotoResponse> update(@PathVariable Long id, @Valid @RequestBody MotoRequest request) {
        MotoResponse response = service.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<MotoResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

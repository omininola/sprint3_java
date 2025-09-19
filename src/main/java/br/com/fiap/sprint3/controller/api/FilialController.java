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

import br.com.fiap.sprint3.dto.filial.FilialRequest;
import br.com.fiap.sprint3.dto.filial.FilialResponse;
import br.com.fiap.sprint3.service.FilialService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/filiais")
public class FilialController {

    @Autowired
    private FilialService service;

    @PostMapping
    public ResponseEntity<FilialResponse> create(@Valid @RequestBody FilialRequest request) {
        FilialResponse response = service.save(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<FilialResponse>> readAll() {
        List<FilialResponse> response = service.findAll();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FilialResponse> read(@PathVariable Long id) {
        FilialResponse response = service.findById(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FilialResponse> update(@PathVariable Long id, @Valid @RequestBody FilialRequest request) {
        FilialResponse response = service.update(id, request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FilialResponse> delete(@PathVariable Long id) {
        service.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

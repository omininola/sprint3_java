package br.com.fiap.sprint3.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.fiap.sprint3.dto.filial.FilialRequest;
import br.com.fiap.sprint3.dto.filial.FilialResponse;
import br.com.fiap.sprint3.dto.moto.MotoResponse;
import br.com.fiap.sprint3.entity.Filial;
import br.com.fiap.sprint3.exception.NotFoundException;
import br.com.fiap.sprint3.repository.FilialRepository;

@Service
public class FilialService {

    @Autowired
    private FilialRepository repository;

    @Autowired
    private MotoService motoService;

    private final String NOT_FOUND_MESSAGE = "Filial com esse Id não foi encontrada";

    public FilialResponse save(FilialRequest request) {
        Filial filial = repository.save(toEntity(request));
        return toResponse(filial);
    }

    public List<FilialResponse> findAll() {
        List<Filial> filiais = repository.findAll();
        return filiais.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public FilialResponse findById(Long id) {
        Optional<Filial> filial = repository.findById(id);
        if (filial.isEmpty())
            throw new NotFoundException(NOT_FOUND_MESSAGE);

        return toResponse(filial.get());
    }

    public FilialResponse update(Long id, FilialRequest request) {
        Optional<Filial> filial = repository.findById(id);
        if (filial.isEmpty())
            throw new NotFoundException(NOT_FOUND_MESSAGE);

        Filial existingFilial = filial.get();
        existingFilial.setId(id);
        existingFilial.setNome(request.getNome());
        existingFilial.setEndereco(request.getEndereco());

        repository.save(existingFilial);
        return toResponse(existingFilial);
    }

    public void delete(Long id) {
        Optional<Filial> filial = repository.findById(id);
        if (filial.isEmpty())
            throw new NotFoundException(NOT_FOUND_MESSAGE);

        repository.deleteById(id);
    }

    public Filial toEntity(FilialRequest request) {
        Filial entity = new Filial();
        entity.setNome(request.getNome());
        entity.setEndereco(request.getEndereco());

        return entity;
    }

    public FilialResponse toResponse(Filial filial) {
        List<MotoResponse> motos = filial.getMotos().stream().map(moto -> motoService.toResponse(moto))
                .collect(Collectors.toList());

        FilialResponse response = new FilialResponse();
        response.setId(filial.getId());
        response.setNome(filial.getNome());
        response.setEndereco(filial.getEndereco());
        response.setMotos(motos);

        return response;
    }

}

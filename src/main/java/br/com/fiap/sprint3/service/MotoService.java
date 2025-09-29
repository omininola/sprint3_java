package br.com.fiap.sprint3.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.fiap.sprint3.dto.moto.MotoRequest;
import br.com.fiap.sprint3.dto.moto.MotoResponse;
import br.com.fiap.sprint3.entity.Filial;
import br.com.fiap.sprint3.entity.Moto;
import br.com.fiap.sprint3.entity.Usuario;
import br.com.fiap.sprint3.exception.NotFoundException;
import br.com.fiap.sprint3.repository.FilialRepository;
import br.com.fiap.sprint3.repository.MotoRepository;
import br.com.fiap.sprint3.repository.UsuarioRepository;

@Service
public class MotoService {

    @Autowired
    private MotoRepository repository;

    @Autowired
    private FilialRepository filialRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final String NOT_FOUND_MESSAGE = "Moto com esse Id não foi encontrada";
    private final String FILIAL_NOT_FOUND_MESSAGE = "Filial com esse Id não foi encontrada";
    private final String USUARIO_NOT_FOUND_MESSAGE = "Usuário com esse Id não foi encontrado";

    public MotoResponse save(MotoRequest request) {
        Moto moto = repository.save(toEntity(request));
        return toResponse(moto);
    }

    public List<MotoResponse> findAll() {
        List<Moto> motos = repository.findAll();
        return motos.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public MotoResponse findById(Long id) {
        Optional<Moto> moto = repository.findById(id);
        if (moto.isEmpty())
            throw new NotFoundException(NOT_FOUND_MESSAGE);

        return toResponse(moto.get());
    }

    public Optional<MotoRequest> findRequestById(Long id) {
        Optional<Moto> moto = repository.findById(id);
        if (moto.isEmpty()) return null;

        MotoRequest request = new MotoRequest();
        request.setPlaca(moto.get().getPlaca());
        request.setStatus(moto.get().getStatus());
        request.setFilialId(moto.get().getFilial().getId());
        request.setUsuarioId(moto.get().getUsuario().getId());

        return Optional.ofNullable(request);
    }

    public MotoRequest fillUserIdByContext(Long filialId) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        MotoRequest moto = new MotoRequest();
        moto.setUsuarioId(usuario.getId());
        moto.setFilialId(filialId);

        return moto;
    }

    public MotoRequest fillUserIdByContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        MotoRequest moto = new MotoRequest();
        moto.setUsuarioId(usuario.getId());

        return moto;
    }

    public MotoResponse update(Long id, MotoRequest request) {
        Optional<Moto> moto = repository.findById(id);
        if (moto.isEmpty())
            throw new NotFoundException(NOT_FOUND_MESSAGE);

        Moto existingMoto = toEntity(request);
        existingMoto.setId(id);

        repository.save(existingMoto);
        return toResponse(existingMoto);
    }

    public void delete(Long id) {
        Optional<Moto> moto = repository.findById(id);
        if (moto.isEmpty())
            throw new NotFoundException(NOT_FOUND_MESSAGE);

        repository.deleteById(id);
    }

    public Moto toEntity(MotoRequest request) {
        Optional<Filial> filial = filialRepository.findById(request.getFilialId());
        if (filial.isEmpty())
            throw new NotFoundException(FILIAL_NOT_FOUND_MESSAGE);

        Optional<Usuario> usuario = usuarioRepository.findById(request.getUsuarioId());
        if (usuario.isEmpty())
            throw new NotFoundException(USUARIO_NOT_FOUND_MESSAGE);

        Moto entity = new Moto();
        entity.setPlaca(request.getPlaca());
        entity.setStatus(request.getStatus());
        entity.setFilial(filial.get());
        entity.setUsuario(usuario.get());

        return entity;
    }

    public MotoResponse toResponse(Moto moto) {
        MotoResponse response = new MotoResponse();
        response.setId(moto.getId());
        response.setPlaca(moto.getPlaca());
        response.setStatus(moto.getStatus());
        response.setFilialNome(moto.getFilial().getNome());
        response.setUsuarioEmail(moto.getUsuario().getEmail());

        return response;
    }

}

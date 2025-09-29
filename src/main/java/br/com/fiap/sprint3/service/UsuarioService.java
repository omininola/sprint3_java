package br.com.fiap.sprint3.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.fiap.sprint3.dto.auth.AuthResponse;
import br.com.fiap.sprint3.dto.usuario.UsuarioLoginRequest;
import br.com.fiap.sprint3.dto.usuario.UsuarioRequest;
import br.com.fiap.sprint3.dto.usuario.UsuarioResponse;
import br.com.fiap.sprint3.entity.Usuario;
import br.com.fiap.sprint3.exception.BadRequestException;
import br.com.fiap.sprint3.exception.NotFoundException;
import br.com.fiap.sprint3.repository.UsuarioRepository;
import br.com.fiap.sprint3.security.TokenService;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    private final String NOT_FOUND_MESSAGE = "Usuário com o Id informado não foi encontrado";
    private final String EMAIL_ALREADY_EXISTS = "Esse email já foi cadastrado, verifique novamente o email";
    private final String EMAIL_NOT_FOUND = "Email não encontrado, verifique novamente o email";

    public AuthResponse save(UsuarioRequest request) {
        Usuario usuario = (Usuario) repository.findByEmail(request.getEmail());
        if (usuario != null) {
            throw new BadRequestException(EMAIL_ALREADY_EXISTS);
        }

        String rawPassword = request.getSenha();
        String senhaEncriptada = new BCryptPasswordEncoder().encode(rawPassword);
        request.setSenha(senhaEncriptada);

        Usuario usuarioSaved = repository.save(toUsuario(request));

        return authenticateAndBuildResponse(usuarioSaved.getEmail(), rawPassword);
    }

    public AuthResponse login(UsuarioLoginRequest usuarioLoginRequest) {
        Usuario usuario = (Usuario) repository.findByEmail(usuarioLoginRequest.getEmail());
        if (usuario == null) {
            throw new NotFoundException(EMAIL_NOT_FOUND);
        }

        return authenticateAndBuildResponse(usuarioLoginRequest.getEmail(), usuarioLoginRequest.getSenha());
    }

    public List<UsuarioResponse> findAll() {
        List<Usuario> usuarios = repository.findAll();
        return usuarios.stream().map(this::toResponse).collect(Collectors.toList());
    }

    public UsuarioResponse findById(Long id) {
        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        return toResponse(usuario.get());
    }

    public UsuarioResponse findByContext() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = (Usuario) auth.getPrincipal();

        return toResponse(usuario);
    }

    public UsuarioResponse update(UsuarioRequest request, Long id) {
        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        Usuario usuarioUpdate = usuario.get();
        usuarioUpdate.setEmail(request.getEmail());
        usuarioUpdate.setRole(request.getRole());

        String senhaEncriptada = new BCryptPasswordEncoder().encode(request.getSenha());
        usuarioUpdate.setSenha(senhaEncriptada);

        repository.save(usuarioUpdate);

        return toResponse(usuarioUpdate);
    }

    public void delete(Long id) {
        Optional<Usuario> usuario = repository.findById(id);

        if (usuario.isEmpty()) {
            throw new NotFoundException(NOT_FOUND_MESSAGE);
        }

        repository.delete(usuario.get());
    }

    private Usuario toUsuario(UsuarioRequest request) {
        Usuario usuario = new Usuario();
        usuario.setSenha(request.getSenha());
        usuario.setEmail(request.getEmail());
        usuario.setRole(request.getRole());

        return usuario;
    }

    private UsuarioResponse toResponse(Usuario usuario) {
        UsuarioResponse usuarioResponse = new UsuarioResponse();
        usuarioResponse.setId(usuario.getId());
        usuarioResponse.setEmail(usuario.getEmail());
        usuarioResponse.setRole(usuario.getRole());

        return usuarioResponse;
    }

    private AuthResponse authenticateAndBuildResponse(String email, String rawPassword) {
        UsernamePasswordAuthenticationToken usernamePassword = new UsernamePasswordAuthenticationToken(email,
                rawPassword);

        Authentication auth = authenticationManager.authenticate(usernamePassword);
        Usuario usuario = (Usuario) auth.getPrincipal();

        String token = tokenService.gerarToken(usuario);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setToken(token);
        authResponse.setUsuario(toResponse(usuario));

        return authResponse;
    }
}

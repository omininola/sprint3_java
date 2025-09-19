package br.com.fiap.sprint3.dto.auth;

import br.com.fiap.sprint3.dto.usuario.UsuarioResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

    String token;
    UsuarioResponse usuario;
}

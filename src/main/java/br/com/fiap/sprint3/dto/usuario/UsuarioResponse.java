package br.com.fiap.sprint3.dto.usuario;

import br.com.fiap.sprint3.enums.UsuarioRole;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {

    private Long id;
    private String email;
    private UsuarioRole role;
}

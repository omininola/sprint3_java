package br.com.fiap.sprint3.dto.usuario;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioLoginRequest {

    @NotBlank(message = "Senha do usuário deve ser preenchida")
    @Size(min = 3, message = "Senha do usuário deve ter no mínimo 3 caracteres")
    private String senha;

    @NotBlank(message = "Email do usuário deve ser preenchido")
    @Email(message = "Email deve seguir os padrões de email")
    private String email;
}

package br.com.fiap.sprint3.dto.moto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MotoRequest {

    @NotBlank(message = "A placa deve ser preenchida")
    private String placa;

    @NotBlank(message = "O status deve ser preenchido")
    private String status;

    @NotNull(message = "O Id da filial deve ser preenchido")
    private Long filialId;

    @NotNull(message = "O Id do usuário deve ser preenchido")
    private Long usuarioId;
}

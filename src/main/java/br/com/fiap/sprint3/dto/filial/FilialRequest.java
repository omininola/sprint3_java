package br.com.fiap.sprint3.dto.filial;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilialRequest {
    
    @NotBlank(message = "Nome deve ser preenchido")
    private String nome;

    @NotBlank(message = "Endereço deve ser preenchido")
    private String endereco;
}

package br.com.fiap.sprint3.dto.filial;

import java.util.List;

import br.com.fiap.sprint3.dto.moto.MotoResponse;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FilialResponse {

    private Long id;
    private String nome;
    private String endereco;
    private List<MotoResponse> motos;
}

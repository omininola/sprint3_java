package br.com.fiap.sprint3.dto.moto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MotoResponse {
    
    private Long id;
    private String placa;
    private String status;
    private String filialNome;
    private String usuarioEmail;
}

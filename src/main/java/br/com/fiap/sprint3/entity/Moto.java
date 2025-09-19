package br.com.fiap.sprint3.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SPRINT3_MOTO")

@Getter
@Setter
@NoArgsConstructor
public class Moto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_moto")
    private Long id;

    @Column(name = "ds_placa")
    private String placa;

    @Column(name = "ds_status")
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "id_filial")
    private Filial filial;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
}

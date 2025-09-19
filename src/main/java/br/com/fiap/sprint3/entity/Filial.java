package br.com.fiap.sprint3.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SPRINT3_FILIAL")

@Getter
@Setter
@NoArgsConstructor
public class Filial {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_filial")
    private Long id;

    @Column(name = "nm_filial")
    private String nome;

    @Column(name = "ds_endereco")
    private String endereco;
    
    @OneToMany(mappedBy = "filial", cascade = CascadeType.ALL)
    private List<Moto> motos = new ArrayList<>();
}

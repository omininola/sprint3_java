package br.com.fiap.sprint3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.sprint3.entity.Filial;

@Repository
public interface FilialRepository extends JpaRepository<Filial, Long> {
}

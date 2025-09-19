package br.com.fiap.sprint3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.fiap.sprint3.entity.Moto;

@Repository
public interface MotoRepository extends JpaRepository<Moto, Long> {
}

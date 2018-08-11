package com.nelioalves.cursomc.repository;

import com.nelioalves.cursomc.model.Cliente;
import com.nelioalves.cursomc.model.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {
}

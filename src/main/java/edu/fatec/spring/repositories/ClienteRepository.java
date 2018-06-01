package edu.fatec.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import edu.fatec.spring.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String> {
	Cliente findByNome(String nome);
	List<Cliente> findAll();
}

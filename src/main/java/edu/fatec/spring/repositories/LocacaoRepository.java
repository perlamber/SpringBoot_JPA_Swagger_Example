package edu.fatec.spring.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import edu.fatec.spring.model.Locacao;

@Repository
public interface LocacaoRepository extends JpaRepository<Locacao, Long> {
	Locacao findById(Long id);
	List<Locacao> findAll();
	
	@Query("select loc from Locacao loc where loc.cliente.nome = :cli")
	List<Locacao> findAllByCliente(@Param("cli")String cliNome);
	
	@Query("select loc from Locacao loc where loc.funcionario.nome = :func")
	List<Locacao> findAllByFuncionario(@Param("func")String funcNome);
}

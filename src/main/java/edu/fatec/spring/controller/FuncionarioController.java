package edu.fatec.spring.controller;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.fatec.spring.model.Cliente;
import edu.fatec.spring.model.Funcionario;
import edu.fatec.spring.repositories.FuncionarioRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/funcionario")
@Transactional
public class FuncionarioController {
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@ApiOperation(value = "Traz todos os funcionarios", response = Funcionario.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso") })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Funcionario>> getAll() {
		return new ResponseEntity<Iterable<Funcionario>>(funcionarioRepository.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Insere Funcionário", response = Funcionario.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Funcionario inserido com sucesso")})
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Funcionario> insert(@RequestBody Funcionario funcionario) {
		
		return  ResponseEntity.ok(funcionarioRepository.saveAndFlush(funcionario));
	}
	
	@ApiOperation(value = "Atualiza funcionario", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "funcionario inserido com sucesso"),
			@ApiResponse(code = 404, message = "funcionario não encontrado.")})
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Funcionario> update(@RequestBody Funcionario funcionario) {
		if(funcionario.getNome() == null) {
			return (ResponseEntity<Funcionario>) ResponseEntity.notFound();
		}
		return  ResponseEntity.ok(funcionarioRepository.saveAndFlush(funcionario));
	}
	
	@ApiOperation(value = "Deleta um funcionario")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Funcionário deletado com sucesso"),
			@ApiResponse(code = 406, message = "Falha ao deletar") })
	@DeleteMapping(value = "/remove/{id}")
	public ResponseEntity<Void> deletaFuncionario(@PathVariable String nome) {
		try {
			funcionarioRepository.delete(nome);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

}

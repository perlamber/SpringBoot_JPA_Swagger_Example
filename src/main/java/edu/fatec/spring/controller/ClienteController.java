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
import edu.fatec.spring.repositories.ClienteRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cliente")
@Transactional
public class ClienteController {
	@Autowired
	ClienteRepository clienteRepository;

	@ApiOperation(value = "Traz todos os Clientes", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso") })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Cliente>> getAll() {
		return new ResponseEntity<Iterable<Cliente>>(clienteRepository.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Insere Cliente", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente inserido com sucesso")})
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Cliente> insert(@RequestBody Cliente cliente) {
		
		return  ResponseEntity.ok(clienteRepository.saveAndFlush(cliente));
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Atualiza Cliente", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Cliente inserido com sucesso"),
			@ApiResponse(code = 404, message = "Cliente não encontrado.")})
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Cliente> update(@RequestBody Cliente cliente) {
		if(cliente.getNome() == null) {
			return (ResponseEntity<Cliente>) ResponseEntity.notFound();
		}
		return  ResponseEntity.ok(clienteRepository.saveAndFlush(cliente));
	}
	
	@ApiOperation(value = "Deleta um Cliente")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Cliente deletado com sucesso"),
			@ApiResponse(code = 406, message = "Falha ao deletar") })
	@DeleteMapping(value = "/remove/{id}")
	public ResponseEntity<Void> deletaFuncionario(@PathVariable String nome) {
		try {
			clienteRepository.delete(nome);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

}

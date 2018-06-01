package edu.fatec.spring.controller;

import java.io.UnsupportedEncodingException;

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
import edu.fatec.spring.model.Locacao;
import edu.fatec.spring.repositories.ClienteRepository;
import edu.fatec.spring.repositories.FuncionarioRepository;
import edu.fatec.spring.repositories.LocacaoRepository;
import edu.fatec.spring.repositories.VideoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/locacao")
@Transactional
public class LocacaoController {
	@Autowired
	LocacaoRepository locacaoRepository;
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	VideoRepository videoRepository;
	@Autowired
	FuncionarioRepository funcionarioRepository;

	@ApiOperation(value = "Realiza uma locaão", response = Locacao.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Locação inserida com sucesso") })
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Locacao> insert(@RequestBody Locacao locacao) {
		if(locacao.getCliente() != null && locacao.getFuncionario() != null && locacao.getVideo() != null)
		{
			locacao.setCliente(clienteRepository.findByNome(locacao.getCliente().getNome()));
			locacao.setVideo(videoRepository.findById(locacao.getVideo().getId()));
			locacao.setFuncionario(funcionarioRepository.findByNome(locacao.getFuncionario().getNome()));
		}
		else
		{
			new ResponseEntity<Locacao>(HttpStatus.PARTIAL_CONTENT);
		}
		
		return  ResponseEntity.ok(locacaoRepository.saveAndFlush(locacao));
	}

	@ApiOperation(value = "Traz a locação por ID", response = Locacao.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Locação retornada com sucesso"),
			@ApiResponse(code = 404, message = "A locação não foi encontrada") })
	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public ResponseEntity<Locacao> getReader(@PathVariable Long id) {
		Locacao locacao = locacaoRepository.findById(id);
		return (locacao != null) ? new ResponseEntity<Locacao>(locacao, HttpStatus.CREATED)
				: new ResponseEntity<Locacao>(HttpStatus.NOT_FOUND);
	}
	
	@ApiOperation(value = "Traz todas as locacoes", response = Locacao.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso") })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Locacao>> getAll() {
		return new ResponseEntity<Iterable<Locacao>>(locacaoRepository.findAll(), HttpStatus.OK);
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Atualzia Locação", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Locação atualizada com sucesso"),
			@ApiResponse(code = 404, message = "Locação não encontrada.")})
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Locacao> update(@RequestBody Locacao locacao) {
		if(locacao.getId() == null) {
			return (ResponseEntity<Locacao>) ResponseEntity.notFound();
		}
		return  ResponseEntity.ok(locacaoRepository.saveAndFlush(locacao));
	}
	
	@ApiOperation(value = "Deleta uma locação")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Locação deletada com sucesso"),
			@ApiResponse(code = 406, message = "Falha ao deletar") })
	@DeleteMapping(value = "/remove/{id}")
	public ResponseEntity<Void> deleteReader(@PathVariable Long id) {
		try {
			locacaoRepository.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

	}
	
	@ApiOperation(value = "Traz as locações realizadas por cliente", response = Locacao.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso") })
	@RequestMapping(value = "/find/byCliente/{cliNome}", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Locacao>> getAllByCliente(@PathVariable String cliNome) {
		return new ResponseEntity<Iterable<Locacao>>(locacaoRepository.findAllByCliente(cliNome), HttpStatus.OK);
	}
	
	@ApiOperation(value = "Traz as locações realizadas por funcionario", response = Locacao.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso") })
	@RequestMapping(value = "/find/byFuncionario/{funcNome}", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Locacao>> getAllByFuncionario(@PathVariable String funcNome) {
		return new ResponseEntity<Iterable<Locacao>>(locacaoRepository.findAllByFuncionario(funcNome), HttpStatus.OK);
	}

}

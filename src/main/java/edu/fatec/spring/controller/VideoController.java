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
import edu.fatec.spring.model.Video;
import edu.fatec.spring.repositories.VideoRepository;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/video")
@Transactional
public class VideoController {
	@Autowired
	VideoRepository videoRepository;

	@ApiOperation(value = "Traz todos os Videos", response = Video.class)
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Lista retornada com sucesso") })
	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public ResponseEntity<Iterable<Video>> getAll() {
		return new ResponseEntity<Iterable<Video>>(videoRepository.findAll(), HttpStatus.OK);
	}

	@ApiOperation(value = "Insere Video", response = Funcionario.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Video inserido com sucesso")})
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public ResponseEntity<Video> insert(@RequestBody Video video) {
		
		return  ResponseEntity.ok(videoRepository.saveAndFlush(video));
	}
	
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "Atualiza Video", response = Cliente.class)
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Video inserido com sucesso"),
			@ApiResponse(code = 404, message = "Video não encontrado.")})
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<Video> update(@RequestBody Video video) {
		if(video.getId() == null) {
			return (ResponseEntity<Video>) ResponseEntity.notFound();
		}
		return  ResponseEntity.ok(videoRepository.saveAndFlush(video));
	}
	
	@ApiOperation(value = "Deleta um Video")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Video deletado com sucesso"),
			@ApiResponse(code = 406, message = "Falha ao deletar") })
	@DeleteMapping(value = "/remove/{id}")
	public ResponseEntity<Void> deletaVideo(@PathVariable Long id) {
		try {
			videoRepository.delete(id);
			return new ResponseEntity<Void>(HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
		}

	}

}

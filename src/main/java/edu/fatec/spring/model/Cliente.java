package edu.fatec.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@Entity
@Table(name = "clientes")
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {
	@Id
	@Column(name = "cli_nome")
	private String nome;
	@Column(name = "cli_cpf")
	private String cpf;
	@Column(name = "cli_dtnasc")
	private String dataNasc;
	@Column(name = "cli_end")
	private String endereco;
	@Column(name = "cli_tel")
	private String telefone;
	@Column(name = "cli_email")
	private String email;
	@Column(name = "cli_debito")
	private float debito;
	
}

package edu.fatec.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@Entity
@Table(name = "funcionarios")
@AllArgsConstructor
@NoArgsConstructor
public class Funcionario {
	@Id
	@Column(name = "fun_nome")
	private String nome;
	@Column(name = "fun_cpf")
	private String cpf;
	@Column(name = "fun_end")
	private String endereco;
	@Column(name = "fun_tel")
	private String telefone;
	@Column(name = "fun_email")
	private String email;
	@Column(name = "fun_login")
	private String login;
	@Column(name = "fun_senha")
	private String senha;
}

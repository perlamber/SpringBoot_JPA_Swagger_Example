package edu.fatec.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@Entity
@Table(name = "locacoes")
@AllArgsConstructor
@NoArgsConstructor
public class Locacao {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "loc_id")
	private Long id;
	@Column(name = "loc_dtloc")
	private String dataLocacao;
	@Column(name = "loc_dtdev")
	private String dataDevolucao;
	@Column(name = "loc_valor")
	private float valor;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "cli_nome")
	private Cliente cliente;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "fun_nome")
	private Funcionario funcionario;
	
	@ManyToOne
	@NotNull
	@JoinColumn(name = "vid_id")
	private Video video;
}

package edu.fatec.spring.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@ApiModel
@Entity
@Table(name = "videos")
@AllArgsConstructor
@NoArgsConstructor
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "vid_id")
	private Long id;
	@Column(name = "vid_titulo")
	private String titulo;
	@Column(name = "vid_genero")
	private String genero;
	@Column(name = "vid_estado")
	private int estado;
	@Column(name = "vid_qtdest")
	private int estoque;
	@Column(name = "vid_info")
	private String info;
	
}

--liquibase formatted sql

--changeset CRIS_DESK:01
--comment: Create main tables

CREATE TABLE `clientes` (
  `cli_nome` VARCHAR(255) NOT NULL,
  `cli_cpf` VARCHAR(15),
  `cli_dtnasc` VARCHAR(10),
  `cli_end` VARCHAR(255),
  `cli_tel` VARCHAR(10),
  `cli_email` VARCHAR(255),
  `cli_debito` FLOAT(3,2),
  PRIMARY KEY (`cli_nome`));
   
  CREATE TABLE `funcionarios` (
  `fun_nome` VARCHAR(255) NOT NULL,
  `fun_cpf` VARCHAR(15),
  `fun_end` VARCHAR(255),
  `fun_tel` VARCHAR(10),
  `fun_email` VARCHAR(255),
  `fun_login` VARCHAR(255),
  `fun_senha` VARCHAR(255),
  PRIMARY KEY (`fun_nome`));
  
  CREATE TABLE `videos` (
  `vid_id` INT NOT NULL,
  `vid_titulo` VARCHAR(255),
  `vid_genero` VARCHAR(45),
  `vid_estado` INT,
  `vid_qtdest` INT,
  `vid_info` VARCHAR(255),
  PRIMARY KEY (`vid_id`));
   
CREATE TABLE `locacoes` (
  `loc_id` INT NOT NULL AUTO_INCREMENT,
  `nome_cli` VARCHAR(255) NOT NULL,
  `id_vid` INT NOT NULL,
  `nome_fun` VARCHAR(255) NOT NULL,
  `loc_dtloc` VARCHAR(10) NOT NULL,
  `loc_dtdev` VARCHAR(10) NOT NULL,
  `loc_valor` FLOAT(3,2) NOT NULL,
  PRIMARY KEY (`loc_id`),
  INDEX `fk_cliente_locacao_idx` (`nome_cli` ASC),
  INDEX `fk_video_locacao_idx` (`id_vid` ASC),
  INDEX `fk_funcionario_locacao_idx` (`nome_fun` ASC),
  CONSTRAINT `fk_cliente_locacao`
    FOREIGN KEY (`nome_cli`)
    REFERENCES `clientes` (`cli_nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_video_locacao`
    FOREIGN KEY (`id_vid`)
    REFERENCES `videos` (`vid_id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_funcionario_locacao`
    FOREIGN KEY (`nome_fun`)
    REFERENCES `funcionarios` (`fun_nome`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
   

   
CREATE TABLE `historico` (
  `id_leitor` INT(11) NOT NULL,
  `id_livro` INT(11) NOT NULL,
  `data` DATE NOT NULL,
  PRIMARY KEY (`id_leitor`, `id_livro`),
  INDEX `fk_leitor_historico_idx` (`id_leitor` ASC),
  INDEX `fk_livro_historico_idx` (`id_livro` ASC),
  CONSTRAINT `fk_leitor_historico`
    FOREIGN KEY (`id_leitor`)
    REFERENCES `leitor` (`id_leitor`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_livro_historico`
    FOREIGN KEY (`id_livro`)
    REFERENCES `livro` (`id_livro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

--rollback SET FOREIGN_KEY_CHECKS = 0;
--rollback drop table if exists `historico;
--rollback drop table if exists `livro`;
--rollback drop table if exists `leitor`;
--rollback SET FOREIGN_KEY_CHECKS = 1;

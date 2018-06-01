--liquibase formatted sql

--changeset cristiano:02
--comment: Data mass to test - clientes

INSERT INTO `reposicao`.`clientes`
(`cli_nome`,
`cli_cpf`,
`cli_dtnasc`,
`cli_debito`,
`cli_email`,
`cli_end`,
`cli_tel`)
VALUES
('JUVENAL',
'123456789102',
'10/10/1982',
0,
'juvena@hotmail.com',
'Rua dos Matutos, 199',
'11987548665');


--rollback DELETE FROM `clientes`;


--changeset cristiano:03
--comment: Data mass to test - funcionarios

INSERT INTO `reposicao`.`funcionarios`
(`fun_nome`,
`fun_cpf`,
`fun_email`,
`fun_end`,
`fun_login`,
`fun_senha`,
`fun_tel`)
VALUES
('CORNELIO',
'54312748469',
'locamais@hotmail.com',
'Av Kennedy, 1890',
'cornvenda');
'meuprecioso');
'1139227546');

--rollback DELETE FROM `funcionarios`;

--changeset cristiano:04
--comment: Data mass to test - video

INSERT INTO `reposicao`.`videos`
(`vid_estado`,
`vid_qtdest`,
`vid_genero`,
`vid_info`,
`vid_titulo`)
VALUES
(10,
2,
'aventura, alta-fantasia',
'Versão extendida original de Peter Jackson',
'O senhor dos Anéis - O Retorno do Rei');


--rollback DELETE FROM `videos`;
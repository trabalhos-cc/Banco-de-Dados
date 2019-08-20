-- MySQL Workbench Synchronization
-- Generated: 2019-08-17 18:36
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Gabriel

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `fatura` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `fatura`.`cidade` (
  `idcidade` INT(11) NOT NULL,
  `nomeCidade` VARCHAR(45) NULL DEFAULT NULL,
  `idUF` INT(11) NOT NULL,
  PRIMARY KEY (`idcidade`),
  INDEX `fk_cidade_UF1_idx` (`idUF` ASC),
  CONSTRAINT `fk_cidade_UF1`
    FOREIGN KEY (`idUF`)
    REFERENCES `fatura`.`UF` (`idUF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`UF` (
  `idUF` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `idpaís` INT(11) NOT NULL,
  PRIMARY KEY (`idUF`),
  INDEX `fk_UF_país1_idx` (`idpaís` ASC),
  CONSTRAINT `fk_UF_país1`
    FOREIGN KEY (`idpaís`)
    REFERENCES `fatura`.`país` (`idpaís`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`país` (
  `idpaís` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idpaís`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`bairro` (
  `idbairro` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idbairro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`logradouro` (
  `idlogradouro` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `idtipoLogradouro` INT(11) NOT NULL,
  PRIMARY KEY (`idlogradouro`),
  INDEX `fk_logradouro_tipoLogradouro1_idx` (`idtipoLogradouro` ASC),
  CONSTRAINT `fk_logradouro_tipoLogradouro1`
    FOREIGN KEY (`idtipoLogradouro`)
    REFERENCES `fatura`.`tipoLogradouro` (`idtipoLogradouro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`tipoLogradouro` (
  `idtipoLogradouro` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idtipoLogradouro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`relatorio` (
  `idrelatorio` INT(11) NOT NULL,
  `periodo` VARCHAR(45) NULL DEFAULT NULL,
  `valorGasto` VARCHAR(45) NULL DEFAULT NULL,
  `médiaGasto` VARCHAR(45) NULL DEFAULT NULL,
  `idcliente` INT(11) NOT NULL,
  PRIMARY KEY (`idrelatorio`),
  INDEX `fk_relatorio_cliente1_idx` (`idcliente` ASC),
  CONSTRAINT `fk_relatorio_cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `fatura`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`endereço` (
  `idendereço` INT(11) NOT NULL,
  `CEP` VARCHAR(45) NULL DEFAULT NULL,
  `idcidade` INT(11) NOT NULL,
  `idbairro` INT(11) NOT NULL,
  `idlogradouro` INT(11) NOT NULL,
  PRIMARY KEY (`idendereço`),
  INDEX `fk_endereço_cidade_idx` (`idcidade` ASC),
  INDEX `fk_endereço_bairro1_idx` (`idbairro` ASC),
  INDEX `fk_endereço_logradouro1_idx` (`idlogradouro` ASC),
  CONSTRAINT `fk_endereço_cidade`
    FOREIGN KEY (`idcidade`)
    REFERENCES `fatura`.`cidade` (`idcidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereço_bairro1`
    FOREIGN KEY (`idbairro`)
    REFERENCES `fatura`.`bairro` (`idbairro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereço_logradouro1`
    FOREIGN KEY (`idlogradouro`)
    REFERENCES `fatura`.`logradouro` (`idlogradouro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`empresa` (
  `idempresa` INT(11) NOT NULL,
  `nomeFantasia` VARCHAR(45) NULL DEFAULT NULL,
  `razaoSocial` VARCHAR(45) NULL DEFAULT NULL,
  `CNPJ` VARCHAR(45) NULL DEFAULT NULL,
  `numEnd` VARCHAR(45) NULL DEFAULT NULL,
  `complemento` VARCHAR(45) NULL DEFAULT NULL,
  `idendereço` INT(11) NOT NULL,
  PRIMARY KEY (`idempresa`),
  INDEX `fk_empresa_endereço1_idx` (`idendereço` ASC),
  CONSTRAINT `fk_empresa_endereço1`
    FOREIGN KEY (`idendereço`)
    REFERENCES `fatura`.`endereço` (`idendereço`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`operadora` (
  `idoperadora` INT(11) NOT NULL,
  `nomeFantasia` VARCHAR(45) NULL DEFAULT NULL,
  `razaoSocial` VARCHAR(45) NULL DEFAULT NULL,
  `CNPJ` VARCHAR(45) NULL DEFAULT NULL,
  `numEnd` VARCHAR(45) NULL DEFAULT NULL,
  `complemento` VARCHAR(45) NULL DEFAULT NULL,
  `idendereço` INT(11) NOT NULL,
  PRIMARY KEY (`idoperadora`),
  INDEX `fk_operadora_endereço1_idx` (`idendereço` ASC),
  CONSTRAINT `fk_operadora_endereço1`
    FOREIGN KEY (`idendereço`)
    REFERENCES `fatura`.`endereço` (`idendereço`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`cliente` (
  `idcliente` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `CPF` VARCHAR(45) NULL DEFAULT NULL,
  `numEnd` VARCHAR(45) NULL DEFAULT NULL,
  `complemento` VARCHAR(45) NULL DEFAULT NULL,
  `idendereço` INT(11) NOT NULL,
  `idplanoFidelidade` INT(11) NOT NULL,
  PRIMARY KEY (`idcliente`),
  INDEX `fk_cliente_endereço1_idx` (`idendereço` ASC),
  INDEX `fk_cliente_planoFidelidade1_idx` (`idplanoFidelidade` ASC),
  CONSTRAINT `fk_cliente_endereço1`
    FOREIGN KEY (`idendereço`)
    REFERENCES `fatura`.`endereço` (`idendereço`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cliente_planoFidelidade1`
    FOREIGN KEY (`idplanoFidelidade`)
    REFERENCES `fatura`.`planoFidelidade` (`idplanoFidelidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`emailOperadora` (
  `email` INT(11) NOT NULL,
  `idoperadora` INT(11) NOT NULL,
  PRIMARY KEY (`email`),
  INDEX `fk_emailOperadora_operadora1_idx` (`idoperadora` ASC),
  CONSTRAINT `fk_emailOperadora_operadora1`
    FOREIGN KEY (`idoperadora`)
    REFERENCES `fatura`.`operadora` (`idoperadora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`foneOperadora` (
  `idfoneOperadora` INT(11) NOT NULL,
  `numFone` INT(11) NULL DEFAULT NULL,
  `idoperadora` INT(11) NOT NULL,
  `idtipoFone` INT(11) NOT NULL,
  `numDDD` INT(11) NOT NULL,
  PRIMARY KEY (`idfoneOperadora`),
  INDEX `fk_foneOperadora_operadora1_idx` (`idoperadora` ASC),
  INDEX `fk_foneOperadora_tipoFone1_idx` (`idtipoFone` ASC),
  INDEX `fk_foneOperadora_DDD1_idx` (`numDDD` ASC),
  CONSTRAINT `fk_foneOperadora_operadora1`
    FOREIGN KEY (`idoperadora`)
    REFERENCES `fatura`.`operadora` (`idoperadora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_foneOperadora_tipoFone1`
    FOREIGN KEY (`idtipoFone`)
    REFERENCES `fatura`.`tipoFone` (`idtipoFone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_foneOperadora_DDD1`
    FOREIGN KEY (`numDDD`)
    REFERENCES `fatura`.`DDD` (`numDDD`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`tipoFone` (
  `idtipoFone` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idtipoFone`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`emailCliente` (
  `email` INT(11) NOT NULL,
  `idcliente` INT(11) NOT NULL,
  PRIMARY KEY (`email`),
  INDEX `fk_emailCliente_cliente1_idx` (`idcliente` ASC),
  CONSTRAINT `fk_emailCliente_cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `fatura`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`DDD` (
  `numDDD` INT(11) NOT NULL,
  PRIMARY KEY (`numDDD`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`foneCliente` (
  `idfoneCliente` INT(11) NOT NULL,
  `numFone` INT(11) NULL DEFAULT NULL,
  `idcliente` INT(11) NOT NULL,
  `idtipoFone` INT(11) NOT NULL,
  `numDDD` INT(11) NOT NULL,
  PRIMARY KEY (`idfoneCliente`),
  INDEX `fk_foneCliente_cliente1_idx` (`idcliente` ASC),
  INDEX `fk_foneCliente_tipoFone1_idx` (`idtipoFone` ASC),
  INDEX `fk_foneCliente_DDD1_idx` (`numDDD` ASC),
  CONSTRAINT `fk_foneCliente_cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `fatura`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_foneCliente_tipoFone1`
    FOREIGN KEY (`idtipoFone`)
    REFERENCES `fatura`.`tipoFone` (`idtipoFone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_foneCliente_DDD1`
    FOREIGN KEY (`numDDD`)
    REFERENCES `fatura`.`DDD` (`numDDD`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`organizacao` (
  `idorganizacao` INT(11) NOT NULL,
  `nomeOrg` VARCHAR(45) NULL DEFAULT NULL,
  `razaoSocial` VARCHAR(45) NULL DEFAULT NULL,
  `CNPJ` VARCHAR(45) NULL DEFAULT NULL,
  `numEnd` VARCHAR(45) NULL DEFAULT NULL,
  `complemento` VARCHAR(45) NULL DEFAULT NULL,
  `idendereço` INT(11) NOT NULL,
  PRIMARY KEY (`idorganizacao`),
  INDEX `fk_organizacao_endereço1_idx` (`idendereço` ASC),
  CONSTRAINT `fk_organizacao_endereço1`
    FOREIGN KEY (`idendereço`)
    REFERENCES `fatura`.`endereço` (`idendereço`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`planoFidelidade` (
  `idplanoFidelidade` INT(11) NOT NULL,
  `pontos` VARCHAR(45) NULL DEFAULT NULL,
  `validade` VARCHAR(45) NULL DEFAULT NULL,
  `renovacao` VARCHAR(45) NULL DEFAULT NULL,
  `idoperadora` INT(11) NOT NULL,
  PRIMARY KEY (`idplanoFidelidade`),
  INDEX `fk_planoFidelidade_operadora1_idx` (`idoperadora` ASC),
  CONSTRAINT `fk_planoFidelidade_operadora1`
    FOREIGN KEY (`idoperadora`)
    REFERENCES `fatura`.`operadora` (`idoperadora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`extratoDetalhado` (
  `idextratoDetalhado` INT(11) NOT NULL,
  `valorPago` VARCHAR(45) NULL DEFAULT NULL,
  `valorfatura` VARCHAR(45) NULL DEFAULT NULL,
  `data` VARCHAR(45) NULL DEFAULT NULL,
  `valorFaltante` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idextratoDetalhado`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`faturaMensal` (
  `idfaturaMensal` INT(11) NOT NULL,
  `dtNascimento` VARCHAR(45) NULL DEFAULT NULL,
  `valorFatura` VARCHAR(45) NULL DEFAULT NULL,
  `valorMinimo` VARCHAR(45) NULL DEFAULT NULL,
  `limiteCredito` VARCHAR(45) NULL DEFAULT NULL,
  `idextratoDetalhado` INT(11) NOT NULL,
  `idcliente` INT(11) NOT NULL,
  `idoperadora` INT(11) NOT NULL,
  PRIMARY KEY (`idfaturaMensal`),
  INDEX `fk_faturaMensal_extratoDetalhado1_idx` (`idextratoDetalhado` ASC),
  INDEX `fk_faturaMensal_cliente1_idx` (`idcliente` ASC),
  INDEX `fk_faturaMensal_operadora1_idx` (`idoperadora` ASC),
  CONSTRAINT `fk_faturaMensal_extratoDetalhado1`
    FOREIGN KEY (`idextratoDetalhado`)
    REFERENCES `fatura`.`extratoDetalhado` (`idextratoDetalhado`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faturaMensal_cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `fatura`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faturaMensal_operadora1`
    FOREIGN KEY (`idoperadora`)
    REFERENCES `fatura`.`operadora` (`idoperadora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`extratoGastos` (
  `idextratoGastos` INT(11) NOT NULL,
  `periodo` VARCHAR(45) NULL DEFAULT NULL,
  `detalhesgastos` VARCHAR(45) NULL DEFAULT NULL,
  `valor` VARCHAR(45) NULL DEFAULT NULL,
  `idoperadora` INT(11) NOT NULL,
  `idcliente` INT(11) NOT NULL,
  `idrelatorio` INT(11) NOT NULL,
  PRIMARY KEY (`idextratoGastos`),
  INDEX `fk_extratoGastos_operadora1_idx` (`idoperadora` ASC),
  INDEX `fk_extratoGastos_cliente1_idx` (`idcliente` ASC),
  INDEX `fk_extratoGastos_relatorio1_idx` (`idrelatorio` ASC),
  CONSTRAINT `fk_extratoGastos_operadora1`
    FOREIGN KEY (`idoperadora`)
    REFERENCES `fatura`.`operadora` (`idoperadora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_extratoGastos_cliente1`
    FOREIGN KEY (`idcliente`)
    REFERENCES `fatura`.`cliente` (`idcliente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_extratoGastos_relatorio1`
    FOREIGN KEY (`idrelatorio`)
    REFERENCES `fatura`.`relatorio` (`idrelatorio`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `fatura`.`extratoFaturamento` (
  `idextratoFaturamento` INT(11) NOT NULL,
  `detalhesfaturamento` VARCHAR(45) NULL DEFAULT NULL,
  `valor` VARCHAR(45) NULL DEFAULT NULL,
  `idoperadora` INT(11) NOT NULL,
  `idempresa` INT(11) NOT NULL,
  `idorganizacao` INT(11) NOT NULL,
  PRIMARY KEY (`idextratoFaturamento`),
  INDEX `fk_extratoFaturamento_operadora1_idx` (`idoperadora` ASC),
  INDEX `fk_extratoFaturamento_empresa1_idx` (`idempresa` ASC),
  INDEX `fk_extratoFaturamento_organizacao1_idx` (`idorganizacao` ASC),
  CONSTRAINT `fk_extratoFaturamento_operadora1`
    FOREIGN KEY (`idoperadora`)
    REFERENCES `fatura`.`operadora` (`idoperadora`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_extratoFaturamento_empresa1`
    FOREIGN KEY (`idempresa`)
    REFERENCES `fatura`.`empresa` (`idempresa`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_extratoFaturamento_organizacao1`
    FOREIGN KEY (`idorganizacao`)
    REFERENCES `fatura`.`organizacao` (`idorganizacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- MySQL Workbench Synchronization
-- Generated: 2019-10-20 16:32
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Gabriel

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

CREATE SCHEMA IF NOT EXISTS `IRPF` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS `IRPF`.`contribuinte` (
  `idcontribuinte` INT(11) NOT NULL,
  `mudancaEndereco` TINYINT(4) NULL DEFAULT NULL,
  `conjuge` TINYINT(4) NULL DEFAULT NULL,
  `dtNascimento` DATE NULL DEFAULT NULL,
  `tituloEleitoral` INT(11) NULL DEFAULT NULL,
  `deficiencia` TINYINT(4) NULL DEFAULT NULL,
  `numero` INT(11) NULL DEFAULT NULL,
  `comlemento` VARCHAR(45) NULL DEFAULT NULL,
  `idocupacao` INT(11) NOT NULL,
  `idlocalidade` INT(11) NOT NULL,
  PRIMARY KEY (`idcontribuinte`),
  INDEX `fk_contribuinte_ocupacao_idx` (`idocupacao` ASC),
  INDEX `fk_contribuinte_localidade1_idx` (`idlocalidade` ASC),
  CONSTRAINT `fk_contribuinte_ocupacao`
    FOREIGN KEY (`idocupacao`)
    REFERENCES `IRPF`.`ocupacao` (`idocupacao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_contribuinte_localidade1`
    FOREIGN KEY (`idlocalidade`)
    REFERENCES `IRPF`.`endereco` (`idendereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`bairro` (
  `idbairro` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idbairro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`logradouro` (
  `idlogradouro` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `idtipoLogradouro` INT(11) NOT NULL,
  PRIMARY KEY (`idlogradouro`),
  INDEX `fk_logradouro_tipoLogradouro1_idx` (`idtipoLogradouro` ASC),
  CONSTRAINT `fk_logradouro_tipoLogradouro1`
    FOREIGN KEY (`idtipoLogradouro`)
    REFERENCES `IRPF`.`tipoLogradouro` (`idtipoLogradouro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`tipoLogradouro` (
  `idtipoLogradouro` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idtipoLogradouro`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`cidade` (
  `idcidade` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `iduf` INT(11) NOT NULL,
  PRIMARY KEY (`idcidade`),
  INDEX `fk_cidade_uf1_idx` (`iduf` ASC),
  CONSTRAINT `fk_cidade_uf1`
    FOREIGN KEY (`iduf`)
    REFERENCES `IRPF`.`uf` (`iduf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`uf` (
  `iduf` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `idpais` INT(11) NOT NULL,
  PRIMARY KEY (`iduf`),
  INDEX `fk_uf_pais1_idx` (`idpais` ASC),
  CONSTRAINT `fk_uf_pais1`
    FOREIGN KEY (`idpais`)
    REFERENCES `IRPF`.`pais` (`idpais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`pais` (
  `idpais` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idpais`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`emailContribuinte` (
  `idemailContribuinte` INT(11) NOT NULL,
  `email` VARCHAR(45) NULL DEFAULT NULL,
  `idcontribuinte` INT(11) NOT NULL,
  PRIMARY KEY (`idemailContribuinte`),
  INDEX `fk_emailContribuinte_contribuinte1_idx` (`idcontribuinte` ASC),
  CONSTRAINT `fk_emailContribuinte_contribuinte1`
    FOREIGN KEY (`idcontribuinte`)
    REFERENCES `IRPF`.`contribuinte` (`idcontribuinte`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`foneContribuinte` (
  `idfoneContribuinte` INT(11) NOT NULL,
  `idcontribuinte` INT(11) NOT NULL,
  `idddd` INT(11) NOT NULL,
  `idtipoFone` INT(11) NOT NULL,
  `idddi` INT(11) NOT NULL,
  PRIMARY KEY (`idfoneContribuinte`),
  INDEX `fk_foneContribuinte_contribuinte1_idx` (`idcontribuinte` ASC),
  INDEX `fk_foneContribuinte_ddd1_idx` (`idddd` ASC),
  INDEX `fk_foneContribuinte_tipoFone1_idx` (`idtipoFone` ASC),
  INDEX `fk_foneContribuinte_ddi1_idx` (`idddi` ASC),
  CONSTRAINT `fk_foneContribuinte_contribuinte1`
    FOREIGN KEY (`idcontribuinte`)
    REFERENCES `IRPF`.`contribuinte` (`idcontribuinte`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_foneContribuinte_ddd1`
    FOREIGN KEY (`idddd`)
    REFERENCES `IRPF`.`ddd` (`idddd`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_foneContribuinte_tipoFone1`
    FOREIGN KEY (`idtipoFone`)
    REFERENCES `IRPF`.`tipoFone` (`idtipoFone`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_foneContribuinte_ddi1`
    FOREIGN KEY (`idddi`)
    REFERENCES `IRPF`.`ddi` (`idddi`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`tipoFone` (
  `idtipoFone` INT(11) NOT NULL,
  `tipo` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idtipoFone`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`ddd` (
  `idddd` INT(11) NOT NULL,
  `nro` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idddd`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`endereco` (
  `idendereco` INT(11) NOT NULL,
  `idenderecoExterior` INT(11) NOT NULL,
  `idlogradouro` INT(11) NOT NULL,
  `idbairro` INT(11) NOT NULL,
  `idcidade` INT(11) NOT NULL,
  `idtipoEndereco` INT(11) NOT NULL,
  `CEP` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idendereco`),
  INDEX `fk_localidade_enderecoExterior1_idx` (`idenderecoExterior` ASC),
  INDEX `fk_endereco_logradouro1_idx` (`idlogradouro` ASC),
  INDEX `fk_endereco_bairro1_idx` (`idbairro` ASC),
  INDEX `fk_endereco_cidade1_idx` (`idcidade` ASC),
  INDEX `fk_endereco_tipoEndereco1_idx` (`idtipoEndereco` ASC),
  CONSTRAINT `fk_localidade_enderecoExterior1`
    FOREIGN KEY (`idenderecoExterior`)
    REFERENCES `IRPF`.`enderecoExterior` (`idenderecoExterior`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_logradouro1`
    FOREIGN KEY (`idlogradouro`)
    REFERENCES `IRPF`.`logradouro` (`idlogradouro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_bairro1`
    FOREIGN KEY (`idbairro`)
    REFERENCES `IRPF`.`bairro` (`idbairro`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_cidade1`
    FOREIGN KEY (`idcidade`)
    REFERENCES `IRPF`.`cidade` (`idcidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_endereco_tipoEndereco1`
    FOREIGN KEY (`idtipoEndereco`)
    REFERENCES `IRPF`.`tipoEndereco` (`idtipoEndereco`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`ocupacao` (
  `idocupacao` INT(11) NOT NULL,
  `idnatureza` INT(11) NOT NULL,
  `idocupacaoPrincipal` INT(11) NOT NULL,
  PRIMARY KEY (`idocupacao`),
  INDEX `fk_ocupacao_natureza1_idx` (`idnatureza` ASC),
  INDEX `fk_ocupacao_ocupacaoPrincipal1_idx` (`idocupacaoPrincipal` ASC),
  CONSTRAINT `fk_ocupacao_natureza1`
    FOREIGN KEY (`idnatureza`)
    REFERENCES `IRPF`.`natureza` (`idnatureza`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_ocupacao_ocupacaoPrincipal1`
    FOREIGN KEY (`idocupacaoPrincipal`)
    REFERENCES `IRPF`.`ocupacaoPrincipal` (`idocupacaoPrincipal`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`natureza` (
  `idnatureza` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idnatureza`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`ocupacaoPrincipal` (
  `idocupacaoPrincipal` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idocupacaoPrincipal`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`enderecoExterior` (
  `idenderecoExterior` INT(11) NOT NULL,
  `CPFProcurador` INT(11) NULL DEFAULT NULL,
  `codigoPostal` INT(11) NULL DEFAULT NULL,
  `idembaixada` INT(11) NOT NULL,
  PRIMARY KEY (`idenderecoExterior`),
  INDEX `fk_enderecoExterior_embaixada1_idx` (`idembaixada` ASC),
  CONSTRAINT `fk_enderecoExterior_embaixada1`
    FOREIGN KEY (`idembaixada`)
    REFERENCES `IRPF`.`embaixada` (`idembaixada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`ddi` (
  `idddi` INT(11) NOT NULL,
  `nro` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`idddi`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`embaixada` (
  `idembaixada` INT(11) NOT NULL,
  `idtipoOrgao` INT(11) NOT NULL,
  `idcidade` INT(11) NOT NULL,
  `idpais` INT(11) NOT NULL,
  PRIMARY KEY (`idembaixada`),
  INDEX `fk_embaixada_tipoOrgao1_idx` (`idtipoOrgao` ASC),
  INDEX `fk_embaixada_cidade1_idx` (`idcidade` ASC),
  INDEX `fk_embaixada_pais1_idx` (`idpais` ASC),
  CONSTRAINT `fk_embaixada_tipoOrgao1`
    FOREIGN KEY (`idtipoOrgao`)
    REFERENCES `IRPF`.`tipoOrgao` (`idtipoOrgao`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_embaixada_cidade1`
    FOREIGN KEY (`idcidade`)
    REFERENCES `IRPF`.`cidade` (`idcidade`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_embaixada_pais1`
    FOREIGN KEY (`idpais`)
    REFERENCES `IRPF`.`pais` (`idpais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`tipoOrgao` (
  `idtipoOrgao` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idtipoOrgao`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`tipoEndereco` (
  `idtipoEndereco` INT(11) NOT NULL,
  `tipo` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idtipoEndereco`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`dependentes` (
  `iddependentes` INT(11) NOT NULL,
  `CPF` INT(11) NULL DEFAULT NULL,
  `dtNascimento` DATE NULL DEFAULT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `valor` FLOAT(11) NULL DEFAULT NULL,
  `item` INT(11) NULL DEFAULT NULL,
  `idtipoDependente` INT(11) NOT NULL,
  PRIMARY KEY (`iddependentes`),
  INDEX `fk_dependentes_tipoDependente1_idx` (`idtipoDependente` ASC),
  CONSTRAINT `fk_dependentes_tipoDependente1`
    FOREIGN KEY (`idtipoDependente`)
    REFERENCES `IRPF`.`tipoDependente` (`idtipoDependente`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`tipoDependente` (
  `idtipoDependente` INT(11) NOT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idtipoDependente`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`titularDependente` (
  `iddependentes` INT(11) NOT NULL,
  `idcontribuinte` INT(11) NOT NULL,
  INDEX `fk_titularDependente_dependentes1_idx` (`iddependentes` ASC),
  INDEX `fk_titularDependente_contribuinte1_idx` (`idcontribuinte` ASC),
  CONSTRAINT `fk_titularDependente_dependentes1`
    FOREIGN KEY (`iddependentes`)
    REFERENCES `IRPF`.`dependentes` (`iddependentes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_titularDependente_contribuinte1`
    FOREIGN KEY (`idcontribuinte`)
    REFERENCES `IRPF`.`contribuinte` (`idcontribuinte`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`rendTribPF` (
  `idrendTribPF` INT(11) NOT NULL,
  `contribuinte_idcontribuinte` INT(11) NOT NULL,
  `dependentes_iddependentes` INT(11) NOT NULL,
  PRIMARY KEY (`idrendTribPF`),
  INDEX `fk_rendTribPF_contribuinte1_idx` (`contribuinte_idcontribuinte` ASC),
  INDEX `fk_rendTribPF_dependentes1_idx` (`dependentes_iddependentes` ASC),
  CONSTRAINT `fk_rendTribPF_contribuinte1`
    FOREIGN KEY (`contribuinte_idcontribuinte`)
    REFERENCES `IRPF`.`contribuinte` (`idcontribuinte`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_rendTribPF_dependentes1`
    FOREIGN KEY (`dependentes_iddependentes`)
    REFERENCES `IRPF`.`dependentes` (`iddependentes`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`rendTrabNAss` (
  `idrendTrabNAss` INT(11) NOT NULL,
  `mes` VARCHAR(45) NULL DEFAULT NULL,
  `rendTribPF_idrendTribPF` INT(11) NOT NULL,
  PRIMARY KEY (`idrendTrabNAss`),
  INDEX `fk_rendTrabNAss_rendTribPF1_idx` (`rendTribPF_idrendTribPF` ASC),
  CONSTRAINT `fk_rendTrabNAss_rendTribPF1`
    FOREIGN KEY (`rendTribPF_idrendTribPF`)
    REFERENCES `IRPF`.`rendTribPF` (`idrendTribPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`jan` (
  `idjan` INT(11) NOT NULL,
  `titular` VARCHAR(45) NULL DEFAULT NULL,
  `beneficiario` VARCHAR(45) NULL DEFAULT NULL,
  `rendimentoRecebido` VARCHAR(45) NULL DEFAULT NULL,
  `rendTrabNAss_idrendTrabNAss` INT(11) NOT NULL,
  PRIMARY KEY (`idjan`),
  INDEX `fk_jan_rendTrabNAss1_idx` (`rendTrabNAss_idrendTrabNAss` ASC),
  CONSTRAINT `fk_jan_rendTrabNAss1`
    FOREIGN KEY (`rendTrabNAss_idrendTrabNAss`)
    REFERENCES `IRPF`.`rendTrabNAss` (`idrendTrabNAss`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`outrasInf` (
  `idoutrasInf` INT(11) NOT NULL,
  `nit/pis/pasep` INT(11) NULL DEFAULT NULL,
  `rendTribPF_idrendTribPF` INT(11) NOT NULL,
  `mes` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`idoutrasInf`),
  INDEX `fk_outrasInf_rendTribPF1_idx` (`rendTribPF_idrendTribPF` ASC),
  CONSTRAINT `fk_outrasInf_rendTribPF1`
    FOREIGN KEY (`rendTribPF_idrendTribPF`)
    REFERENCES `IRPF`.`rendTribPF` (`idrendTribPF`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`rendimento` (
  `idrendimento` INT(11) NOT NULL,
  `trabalhoSalariado` FLOAT(11) NULL DEFAULT NULL,
  `alugueis` FLOAT(11) NULL DEFAULT NULL,
  `pensao/outros` FLOAT(11) NULL DEFAULT NULL,
  `exterior` FLOAT(11) NULL DEFAULT NULL,
  `outrasInf_idoutrasInf` INT(11) NOT NULL,
  PRIMARY KEY (`idrendimento`),
  INDEX `fk_rendimento_outrasInf1_idx` (`outrasInf_idoutrasInf` ASC),
  CONSTRAINT `fk_rendimento_outrasInf1`
    FOREIGN KEY (`outrasInf_idoutrasInf`)
    REFERENCES `IRPF`.`outrasInf` (`idoutrasInf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`deducao` (
  `iddeducao` INT(11) NOT NULL,
  `previdenciaOficial` FLOAT(11) NULL DEFAULT NULL,
  `qntDependentes` INT(11) NULL DEFAULT NULL,
  `pensaoAlimenticia` FLOAT(11) NULL DEFAULT NULL,
  `livroCaixa` FLOAT(11) NULL DEFAULT NULL,
  `outrasInf_idoutrasInf` INT(11) NOT NULL,
  PRIMARY KEY (`iddeducao`),
  INDEX `fk_deducao_outrasInf1_idx` (`outrasInf_idoutrasInf` ASC),
  CONSTRAINT `fk_deducao_outrasInf1`
    FOREIGN KEY (`outrasInf_idoutrasInf`)
    REFERENCES `IRPF`.`outrasInf` (`idoutrasInf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`carneLeao` (
  `idcarneLeao` INT(11) NOT NULL,
  `dafPago` FLOAT(11) NULL DEFAULT NULL,
  `outrasInf_idoutrasInf` INT(11) NOT NULL,
  PRIMARY KEY (`idcarneLeao`),
  INDEX `fk_carneLeao_outrasInf1_idx` (`outrasInf_idoutrasInf` ASC),
  CONSTRAINT `fk_carneLeao_outrasInf1`
    FOREIGN KEY (`outrasInf_idoutrasInf`)
    REFERENCES `IRPF`.`outrasInf` (`idoutrasInf`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `IRPF`.`bensDireito` (
  `idbensDireito` INT(11) NOT NULL,
  `discriminacao` VARCHAR(45) NULL DEFAULT NULL,
  `situacaoFirst` FLOAT(11) NULL DEFAULT NULL,
  `situacaoLast` FLOAT(11) NULL DEFAULT NULL,
  `item` INT(11) NULL DEFAULT NULL,
  `nome` VARCHAR(45) NULL DEFAULT NULL,
  `documento` INT(11) NULL DEFAULT NULL,
  `pais_idpais` INT(11) NOT NULL,
  PRIMARY KEY (`idbensDireito`),
  INDEX `fk_bensDireito_pais1_idx` (`pais_idpais` ASC),
  CONSTRAINT `fk_bensDireito_pais1`
    FOREIGN KEY (`pais_idpais`)
    REFERENCES `IRPF`.`pais` (`idpais`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

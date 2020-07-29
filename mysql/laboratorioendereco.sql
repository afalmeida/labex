-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: db
-- Tempo de geração: 26/07/2020 às 23:47
-- Versão do servidor: 8.0.21
-- Versão do PHP: 7.4.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `labdasa`
--

-- --------------------------------------------------------

--
-- Estrutura para tabela `laboratorioendereco`
--

CREATE TABLE `laboratorioendereco` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `id_laboratorio` int NOT NULL,
  `id_endereco` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `laboratorioendereco`
--
ALTER TABLE `laboratorioendereco`
  ADD KEY `fk_id_laboratorio` (`id_laboratorio`),
  ADD KEY `fk_id_endereco` (`id_endereco`);

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `laboratorioendereco`
--
ALTER TABLE `laboratorioendereco`
  ADD CONSTRAINT `fk_id_endereco` FOREIGN KEY (`id_endereco`) REFERENCES `endereco` (`id`),
  ADD CONSTRAINT `fk_id_laboratorioendereco` FOREIGN KEY (`id_laboratorio`) REFERENCES `laboratorio` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

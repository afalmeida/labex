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
-- Estrutura para tabela `laboratorioexame`
--

CREATE TABLE `laboratorioexame` (
  `id` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `id_laboratorio` int NOT NULL,
  `id_exame` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices de tabelas apagadas
--

--
-- Índices de tabela `laboratorioexame`
--
ALTER TABLE `laboratorioexame`
  ADD KEY `fk_id_laboratorio` (`id_laboratorio`),
  ADD KEY `fk_id_exame` (`id_exame`);

--
-- Restrições para dumps de tabelas
--

--
-- Restrições para tabelas `laboratorioexame`
--
ALTER TABLE `laboratorioexame`
  ADD CONSTRAINT `fk_id_exame` FOREIGN KEY (`id_exame`) REFERENCES `exame` (`id`),
  ADD CONSTRAINT `fk_id_laboratorioexame` FOREIGN KEY (`id_laboratorio`) REFERENCES `laboratorio` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

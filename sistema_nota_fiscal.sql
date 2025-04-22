-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Tempo de geração: 22-Abr-2025 às 19:19
-- Versão do servidor: 8.0.32
-- versão do PHP: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `sistema_nota_fiscal`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `a01_clientes`
--

CREATE TABLE `a01_clientes` (
  `A01_id_cliente` int NOT NULL,
  `A01_nome` varchar(255) NOT NULL,
  `A01_cpf_cnpj` varchar(18) NOT NULL,
  `A01_endereco` varchar(255) NOT NULL,
  `A01_telefone` varchar(15) DEFAULT NULL,
  `A01_email` varchar(100) DEFAULT NULL,
  `A01_data_cadastro` datetime DEFAULT CURRENT_TIMESTAMP,
  `A01_credito` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `a02_produto`
--

CREATE TABLE `a02_produto` (
  `A02_id_produto` int NOT NULL,
  `A02_nome` varchar(50) NOT NULL,
  `A02_quantidade` int NOT NULL,
  `A02_preco` decimal(10,2) NOT NULL,
  `A02_codigo_do_produto` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `a03_item`
--

CREATE TABLE `a03_item` (
  `A03_id_item` int NOT NULL,
  `A04_id_pedido` int NOT NULL,
  `A02_id_produto` int NOT NULL,
  `A03_quantidade` int NOT NULL,
  `A03_preco_unitario` decimal(10,2) NOT NULL,
  `A03_subtotal` decimal(10,2) GENERATED ALWAYS AS ((`A03_quantidade` * `A03_preco_unitario`)) VIRTUAL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estrutura da tabela `a04_pedido`
--

CREATE TABLE `a04_pedido` (
  `A04_id_pedido` int NOT NULL,
  `A01_id_cliente` int NOT NULL,
  `A04_valor_total` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Índices para tabelas despejadas
--

--
-- Índices para tabela `a01_clientes`
--
ALTER TABLE `a01_clientes`
  ADD PRIMARY KEY (`A01_id_cliente`),
  ADD UNIQUE KEY `A01_cpf_cnpj` (`A01_cpf_cnpj`);

--
-- Índices para tabela `a02_produto`
--
ALTER TABLE `a02_produto`
  ADD PRIMARY KEY (`A02_id_produto`),
  ADD UNIQUE KEY `A02_codigo_do_produto` (`A02_codigo_do_produto`);

--
-- Índices para tabela `a03_item`
--
ALTER TABLE `a03_item`
  ADD PRIMARY KEY (`A03_id_item`,`A04_id_pedido`,`A02_id_produto`),
  ADD KEY `A04_id_pedido` (`A04_id_pedido`),
  ADD KEY `A02_id_produto` (`A02_id_produto`);

--
-- Índices para tabela `a04_pedido`
--
ALTER TABLE `a04_pedido`
  ADD PRIMARY KEY (`A04_id_pedido`),
  ADD KEY `A01_id_cliente` (`A01_id_cliente`);

--
-- AUTO_INCREMENT de tabelas despejadas
--

--
-- AUTO_INCREMENT de tabela `a01_clientes`
--
ALTER TABLE `a01_clientes`
  MODIFY `A01_id_cliente` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `a02_produto`
--
ALTER TABLE `a02_produto`
  MODIFY `A02_id_produto` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `a03_item`
--
ALTER TABLE `a03_item`
  MODIFY `A03_id_item` int NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de tabela `a04_pedido`
--
ALTER TABLE `a04_pedido`
  MODIFY `A04_id_pedido` int NOT NULL AUTO_INCREMENT;

--
-- Restrições para despejos de tabelas
--

--
-- Limitadores para a tabela `a03_item`
--
ALTER TABLE `a03_item`
  ADD CONSTRAINT `a03_item_ibfk_1` FOREIGN KEY (`A04_id_pedido`) REFERENCES `a04_pedido` (`A04_id_pedido`),
  ADD CONSTRAINT `a03_item_ibfk_2` FOREIGN KEY (`A02_id_produto`) REFERENCES `a02_produto` (`A02_id_produto`);

--
-- Limitadores para a tabela `a04_pedido`
--
ALTER TABLE `a04_pedido`
  ADD CONSTRAINT `a04_pedido_ibfk_1` FOREIGN KEY (`A01_id_cliente`) REFERENCES `a01_clientes` (`A01_id_cliente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

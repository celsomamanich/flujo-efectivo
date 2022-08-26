-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 26-08-2022 a las 08:07:52
-- Versión del servidor: 10.4.24-MariaDB
-- Versión de PHP: 8.1.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdflujoefectivo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cuentas`
--

CREATE TABLE `cuentas` (
  `id` int(11) NOT NULL,
  `numero_cuenta` varchar(50) COLLATE utf8_spanish_ci DEFAULT NULL,
  `saldo` decimal(18,2) DEFAULT NULL,
  `estado` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `moneda` varchar(3) COLLATE utf8_spanish_ci DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `cuentas`
--

INSERT INTO `cuentas` (`id`, `numero_cuenta`, `saldo`, `estado`, `moneda`) VALUES
(1, '100001', '0.50', 'ACTIVE', 'BOB'),
(2, '100002', '250.55', 'ACTIVE', 'BOB'),
(3, '200001', '250.55', 'ACTIVE', 'USB'),
(4, '100004', '300.00', 'ACTIVE', 'BOB');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `transacciones`
--

CREATE TABLE `transacciones` (
  `id` int(11) NOT NULL,
  `operacion` varchar(20) COLLATE utf8_spanish_ci DEFAULT NULL,
  `monto` decimal(18,2) DEFAULT NULL,
  `moneda` varchar(3) COLLATE utf8_spanish_ci DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `idcuenta` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_spanish_ci;

--
-- Volcado de datos para la tabla `transacciones`
--

INSERT INTO `transacciones` (`id`, `operacion`, `monto`, `moneda`, `fecha`, `idcuenta`) VALUES
(1, 'DEPOSITO', '10.00', 'BOB', '2022-08-26 00:11:34', 1),
(2, 'DEPOSITO', '10.00', 'BOB', '2022-08-26 00:13:07', 1),
(3, 'DEPOSITO', '25.30', 'BOB', '2022-08-26 00:16:45', 1),
(4, 'RETIRO', '5.30', 'BOB', '2022-08-26 00:19:12', 1),
(5, 'RETIRO', '2.50', 'BOB', '2022-08-26 00:20:35', 1),
(6, 'RETIRO', '30.00', 'BOB', '2022-08-26 00:21:49', 1),
(7, 'DEPOSITO', '0.50', 'BOB', '2022-08-26 00:24:17', 1),
(8, 'DEPOSITO', '2.50', 'BOB', '2022-08-26 00:24:33', 1);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_transacciones_cuentas` (`idcuenta`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `cuentas`
--
ALTER TABLE `cuentas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `transacciones`
--
ALTER TABLE `transacciones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `transacciones`
--
ALTER TABLE `transacciones`
  ADD CONSTRAINT `fk_transacciones_cuentas` FOREIGN KEY (`idcuenta`) REFERENCES `cuentas` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

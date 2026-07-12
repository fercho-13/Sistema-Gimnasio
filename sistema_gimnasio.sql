-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Jul 07, 2026 at 05:00 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `sistema_gimnasio`
--

-- --------------------------------------------------------

--
-- Table structure for table `empleados`
--

CREATE TABLE `empleados` (
  `EmpleadoId` int(11) NOT NULL,
  `PersonaId` int(11) NOT NULL,
  `RolId` int(11) NOT NULL,
  `FechaIngreso` date NOT NULL DEFAULT curdate(),
  `FechaEgreso` date DEFAULT NULL,
  `Activo` tinyint(1) NOT NULL DEFAULT 1,
  `Matricula` varchar(15) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `empleados`
--

INSERT INTO `empleados` (`EmpleadoId`, `PersonaId`, `RolId`, `FechaIngreso`, `FechaEgreso`, `Activo`, `Matricula`) VALUES
(1, 10, 3, '2026-06-29', '2026-07-03', 0, NULL),
(3, 15, 2, '2026-07-03', NULL, 1, '90889');

-- --------------------------------------------------------

--
-- Table structure for table `ingresos`
--

CREATE TABLE `ingresos` (
  `IngresoId` int(11) NOT NULL,
  `SocioId` int(11) NOT NULL,
  `FechaHora` datetime NOT NULL,
  `Acceso` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ingresos`
--

INSERT INTO `ingresos` (`IngresoId`, `SocioId`, `FechaHora`, `Acceso`) VALUES
(1, 1, '2026-06-28 18:02:34', 'Pago'),
(2, 2, '2026-06-28 18:02:51', 'Pago'),
(3, 1, '2026-06-28 18:03:03', 'Pago'),
(4, 1, '2026-06-28 21:04:41', 'Pago'),
(5, 1, '2026-06-29 15:02:26', 'Pago'),
(6, 1, '2026-06-29 17:00:35', 'Pago'),
(7, 2, '2026-06-29 17:01:47', 'Pago'),
(8, 1, '2026-06-30 17:38:04', 'Pago'),
(9, 1, '2026-07-01 15:57:37', 'Pago'),
(10, 1, '2026-07-01 18:09:56', 'Pago'),
(11, 1, '2026-07-03 14:44:34', 'Pago'),
(12, 1, '2026-07-03 14:44:41', 'Pago'),
(13, 1, '2026-07-03 14:44:48', 'Pago'),
(14, 1, '2026-07-03 14:46:25', 'Pago'),
(15, 1, '2026-07-03 14:49:23', 'Pago'),
(16, 2, '2026-07-03 23:37:34', 'Pago'),
(17, 2, '2026-07-03 23:37:36', 'Pago'),
(18, 2, '2026-07-03 23:38:26', 'Pago'),
(19, 2, '2026-07-03 23:38:56', 'Pago'),
(20, 1, '2026-07-03 23:40:02', 'Pago'),
(21, 1, '2026-07-03 23:41:32', 'Pago'),
(22, 1, '2026-07-03 23:49:52', 'Pago'),
(23, 1, '2026-07-03 23:51:18', 'Pago'),
(24, 12, '2026-07-04 00:17:02', 'Pago'),
(25, 12, '2026-07-04 00:17:53', 'Pago'),
(26, 1, '2026-07-04 00:21:47', 'Pago'),
(27, 12, '2026-07-04 00:22:11', 'Pago');

-- --------------------------------------------------------

--
-- Table structure for table `personas`
--

CREATE TABLE `personas` (
  `PersonaId` int(11) NOT NULL,
  `Nombre` varchar(50) NOT NULL,
  `Apellido` varchar(50) NOT NULL,
  `FechaNacimiento` date NOT NULL,
  `Direccion` varchar(50) NOT NULL,
  `DNI` varchar(15) NOT NULL,
  `Telefono` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `personas`
--

INSERT INTO `personas` (`PersonaId`, `Nombre`, `Apellido`, `FechaNacimiento`, `Direccion`, `DNI`, `Telefono`) VALUES
(1, 'Fermin', 'Resch', '2006-03-02', 'Independencia 1234', '47089390', '223 100010'),
(2, 'Daniel', 'De Mec', '2006-02-23', 'Colon 3434', '50000123', '223 456234'),
(3, 'Mauro', 'Lopez', '2004-07-23', 'Hipolito Yrigoyen 4404', '45234112', '223 234523'),
(10, 'Felipe', 'Gimenez', '1995-08-09', 'Gascon 3847', '23987654', '223 894732'),
(15, 'Sergio', 'Perez', '2000-01-01', 'Colon 1233', '98776542', '223 897623'),
(18, 'Bautista', 'Rodriguez', '1998-09-28', 'Nueva Junta 1998', '12000112', '223 876543'),
(19, 'Marcos', 'Perez', '2001-01-01', 'Colon 1098', '90889990', '223 878797'),
(20, 'Marcos', 'Reta', '2000-09-02', 'Alberti 2345', '12111222', '223 785746');

-- --------------------------------------------------------

--
-- Table structure for table `planes`
--

CREATE TABLE `planes` (
  `PlanId` int(11) NOT NULL,
  `Descripcion` varchar(50) NOT NULL,
  `CantidadCupos` int(11) DEFAULT NULL,
  `Precio` decimal(10,2) NOT NULL,
  `Activo` tinyint(1) NOT NULL DEFAULT 1,
  `CantidadDias` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `planes`
--

INSERT INTO `planes` (`PlanId`, `Descripcion`, `CantidadCupos`, `Precio`, `Activo`, `CantidadDias`) VALUES
(1, 'Prueba gratis', 1, 0.00, 1, 1),
(2, 'Pase mensual', NULL, 40000.00, 1, 30),
(3, 'Plan x 10', 10, 200000.00, 1, 10),
(4, 'Pase medio mes', NULL, 25000.00, 1, 15);

-- --------------------------------------------------------

--
-- Table structure for table `roles`
--

CREATE TABLE `roles` (
  `RolId` int(11) NOT NULL,
  `Descripcion` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `roles`
--

INSERT INTO `roles` (`RolId`, `Descripcion`) VALUES
(1, 'Recepcionista'),
(2, 'Entrenador'),
(3, 'Limpieza');

-- --------------------------------------------------------

--
-- Table structure for table `socios`
--

CREATE TABLE `socios` (
  `SocioId` int(11) NOT NULL,
  `PersonaId` int(11) NOT NULL,
  `FechaAlta` date NOT NULL DEFAULT curdate(),
  `Activo` tinyint(1) NOT NULL DEFAULT 1,
  `IngresosImpagos` int(11) NOT NULL DEFAULT 0,
  `Entrenando` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `socios`
--

INSERT INTO `socios` (`SocioId`, `PersonaId`, `FechaAlta`, `Activo`, `IngresosImpagos`, `Entrenando`) VALUES
(1, 1, '2024-03-20', 1, 0, 1),
(2, 2, '2024-03-11', 1, 0, 1),
(3, 3, '2026-06-09', 0, 0, 0),
(11, 18, '2026-07-03', 1, 0, 1),
(12, 19, '2026-07-04', 1, 0, 0),
(13, 20, '2026-07-04', 1, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `suscripciones`
--

CREATE TABLE `suscripciones` (
  `SuscripcionId` int(11) NOT NULL,
  `SocioId` int(11) NOT NULL,
  `PlanId` int(11) NOT NULL,
  `FechaInicio` date NOT NULL DEFAULT curdate(),
  `FechaFin` date NOT NULL DEFAULT curdate(),
  `CuposRestantes` int(11) DEFAULT NULL,
  `Activo` tinyint(1) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `suscripciones`
--

INSERT INTO `suscripciones` (`SuscripcionId`, `SocioId`, `PlanId`, `FechaInicio`, `FechaFin`, `CuposRestantes`, `Activo`) VALUES
(1, 2, 1, '2026-06-09', '2026-06-16', 1, 0),
(2, 1, 4, '2026-06-09', '2026-06-23', NULL, 0),
(3, 1, 2, '2026-06-09', '2026-07-09', NULL, 1),
(4, 2, 3, '2026-06-09', '2026-07-09', 8, 1),
(5, 3, 1, '2026-06-09', '2026-06-16', 0, 0),
(12, 3, 4, '2026-07-03', '2026-07-18', NULL, 1),
(14, 11, 2, '2026-07-03', '2026-08-02', NULL, 1),
(15, 12, 1, '2026-07-04', '2026-07-05', 0, 0),
(16, 13, 1, '2026-07-04', '2026-07-05', 0, 0);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `empleados`
--
ALTER TABLE `empleados`
  ADD PRIMARY KEY (`EmpleadoId`),
  ADD UNIQUE KEY `Matricula` (`Matricula`),
  ADD UNIQUE KEY `PersonaId` (`PersonaId`),
  ADD KEY `FK_Empleado_Rol` (`RolId`);

--
-- Indexes for table `ingresos`
--
ALTER TABLE `ingresos`
  ADD PRIMARY KEY (`IngresoId`),
  ADD KEY `fk_IngresoSocio` (`SocioId`);

--
-- Indexes for table `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`PersonaId`);

--
-- Indexes for table `planes`
--
ALTER TABLE `planes`
  ADD PRIMARY KEY (`PlanId`);

--
-- Indexes for table `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`RolId`);

--
-- Indexes for table `socios`
--
ALTER TABLE `socios`
  ADD PRIMARY KEY (`SocioId`),
  ADD UNIQUE KEY `PersonaId` (`PersonaId`);

--
-- Indexes for table `suscripciones`
--
ALTER TABLE `suscripciones`
  ADD PRIMARY KEY (`SuscripcionId`),
  ADD KEY `FK_Suscripcion_Socio` (`SocioId`),
  ADD KEY `FK_Suscripcion_Plan` (`PlanId`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `empleados`
--
ALTER TABLE `empleados`
  MODIFY `EmpleadoId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `ingresos`
--
ALTER TABLE `ingresos`
  MODIFY `IngresoId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT for table `personas`
--
ALTER TABLE `personas`
  MODIFY `PersonaId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT for table `planes`
--
ALTER TABLE `planes`
  MODIFY `PlanId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `roles`
--
ALTER TABLE `roles`
  MODIFY `RolId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `socios`
--
ALTER TABLE `socios`
  MODIFY `SocioId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT for table `suscripciones`
--
ALTER TABLE `suscripciones`
  MODIFY `SuscripcionId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `empleados`
--
ALTER TABLE `empleados`
  ADD CONSTRAINT `FK_Empleado_Persona` FOREIGN KEY (`PersonaId`) REFERENCES `personas` (`PersonaId`),
  ADD CONSTRAINT `FK_Empleado_Rol` FOREIGN KEY (`RolId`) REFERENCES `roles` (`RolId`);

--
-- Constraints for table `ingresos`
--
ALTER TABLE `ingresos`
  ADD CONSTRAINT `fk_IngresoSocio` FOREIGN KEY (`SocioId`) REFERENCES `socios` (`SocioId`);

--
-- Constraints for table `socios`
--
ALTER TABLE `socios`
  ADD CONSTRAINT `FK_Socio_Persona` FOREIGN KEY (`PersonaId`) REFERENCES `personas` (`PersonaId`);

--
-- Constraints for table `suscripciones`
--
ALTER TABLE `suscripciones`
  ADD CONSTRAINT `FK_Suscripcion_Plan` FOREIGN KEY (`PlanId`) REFERENCES `planes` (`PlanId`),
  ADD CONSTRAINT `FK_Suscripcion_Socio` FOREIGN KEY (`SocioId`) REFERENCES `socios` (`SocioId`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

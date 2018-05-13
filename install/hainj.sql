-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jan 27, 2017 at 11:31 PM
-- Server version: 10.1.19-MariaDB
-- PHP Version: 5.6.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `hainj`
--
CREATE DATABASE IF NOT EXISTS `hainj` DEFAULT CHARACTER SET cp1250 COLLATE cp1250_czech_cs;
USE `hainj`;

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `id` bigint(20) NOT NULL,
  `accNumber` varchar(10) COLLATE cp1250_czech_cs NOT NULL,
  `balance` double NOT NULL,
  `cardNumber` varchar(16) COLLATE cp1250_czech_cs NOT NULL,
  `userId` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1250 COLLATE=cp1250_czech_cs;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`id`, `accNumber`, `balance`, `cardNumber`, `userId`) VALUES
(1, '1122334455', 1000000, '1234123412341234', 2),
(2, '1122334466', 1000000, '1235123512351235', 3);

-- --------------------------------------------------------

--
-- Table structure for table `rights`
--

CREATE TABLE `rights` (
  `id` int(11) NOT NULL,
  `addUser` int(11) NOT NULL,
  `deleteUser` int(11) NOT NULL,
  `modifSelf` int(11) NOT NULL,
  `modifyUser` int(11) NOT NULL,
  `role` varchar(255) COLLATE cp1250_czech_cs NOT NULL,
  `transaction` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1250 COLLATE=cp1250_czech_cs;

--
-- Dumping data for table `rights`
--

INSERT INTO `rights` (`id`, `addUser`, `deleteUser`, `modifSelf`, `modifyUser`, `role`, `transaction`) VALUES
(1, 1, 1, 1, 1, 'Admin', 0),
(2, 0, 0, 1, 0, 'User', 1);

-- --------------------------------------------------------

--
-- Table structure for table `templates`
--

CREATE TABLE `templates` (
  `id` bigint(20) NOT NULL,
  `accNumber` varchar(10) COLLATE cp1250_czech_cs NOT NULL,
  `amount` double NOT NULL,
  `bankCode` varchar(4) COLLATE cp1250_czech_cs NOT NULL,
  `constSymbol` varchar(16) COLLATE cp1250_czech_cs DEFAULT NULL,
  `message` varchar(255) COLLATE cp1250_czech_cs DEFAULT NULL,
  `name` varchar(255) COLLATE cp1250_czech_cs NOT NULL,
  `specSymbol` varchar(16) COLLATE cp1250_czech_cs DEFAULT NULL,
  `varSymbol` varchar(16) COLLATE cp1250_czech_cs DEFAULT NULL,
  `idAcc` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1250 COLLATE=cp1250_czech_cs;

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` bigint(20) NOT NULL,
  `AccNumber` varchar(10) COLLATE cp1250_czech_cs NOT NULL,
  `Message` varchar(255) COLLATE cp1250_czech_cs DEFAULT NULL,
  `amount` double NOT NULL,
  `bankCode` varchar(4) COLLATE cp1250_czech_cs NOT NULL,
  `constSymbol` varchar(16) COLLATE cp1250_czech_cs DEFAULT NULL,
  `date` datetime NOT NULL,
  `processed` bit(1) NOT NULL,
  `specSymbol` varchar(16) COLLATE cp1250_czech_cs DEFAULT NULL,
  `varSymbol` varchar(16) COLLATE cp1250_czech_cs DEFAULT NULL,
  `idAcc` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1250 COLLATE=cp1250_czech_cs;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `AccNumber`, `Message`, `amount`, `bankCode`, `constSymbol`, `date`, `processed`, `specSymbol`, `varSymbol`, `idAcc`) VALUES
(1, '1234569870', NULL, 1000, '0000', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 1),
(2, '1234569870', NULL, 1000, '0000', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 1),
(3, '1234569870', NULL, 1000, '0000', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 1),
(4, '1234569870', 'Transakce 4', 1000, '0300', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 1),
(5, '1234569870', NULL, 1000, '0100', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 1),
(6, '1234569870', NULL, 1000, '0000', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 2),
(7, '1234569870', NULL, 1000, '0000', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 2),
(8, '1234569870', NULL, 1000, '0000', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 2),
(9, '1234569870', 'Transakce 4', 1000, '0300', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 2),
(10, '1234569870', NULL, 1000, '0100', NULL, '2017-01-27 23:30:55', b'1', NULL, NULL, 2);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `Address` varchar(255) COLLATE cp1250_czech_cs DEFAULT NULL,
  `BirthDate` date NOT NULL,
  `Email` varchar(255) COLLATE cp1250_czech_cs NOT NULL,
  `Gender` varchar(255) COLLATE cp1250_czech_cs NOT NULL,
  `Login` varchar(8) COLLATE cp1250_czech_cs NOT NULL,
  `Message` varchar(255) COLLATE cp1250_czech_cs DEFAULT NULL,
  `Password` varchar(255) COLLATE cp1250_czech_cs NOT NULL,
  `Phone` varchar(15) COLLATE cp1250_czech_cs DEFAULT NULL,
  `Name` varchar(255) COLLATE cp1250_czech_cs NOT NULL,
  `NID` varchar(15) COLLATE cp1250_czech_cs NOT NULL,
  `idRights` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=cp1250 COLLATE=cp1250_czech_cs;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `Address`, `BirthDate`, `Email`, `Gender`, `Login`, `Message`, `Password`, `Phone`, `Name`, `NID`, `idRights`) VALUES
(1, 'gregerg 122', '1993-04-17', 'a@b.c', 'Male', 'Admin001', NULL, 'FF2DE3B14F7D236154642D962C4CA63DD74EBE689AAF25965AAD9CF6F9D4CB2556B798AC86B13D3BD6EDEE4DFA88E342B63D45413863F20E92769422853233FE', '721016873', 'Jakub Hain', '931111/1201', 1),
(2, 'gregerg 122', '1993-04-17', 'ab@b.c', 'Male', 'User0002', NULL, 'A75E6D9EB2F73EB30F210349DEC49F6B8477B6EF9A8BF0937D96065D3DE365E14AFBCBA9C6EB19F92CDB0AA4DA21BFBD82083F0F0695B6C3C2296EB54327477C', '721016873', 'Jakub Hain', '910314/1234', 2),
(3, 'gregerg 122', '1993-04-17', 'abb@b.c', 'Male', 'User0001', NULL, 'A75E6D9EB2F73EB30F210349DEC49F6B8477B6EF9A8BF0937D96065D3DE365E14AFBCBA9C6EB19F92CDB0AA4DA21BFBD82083F0F0695B6C3C2296EB54327477C', '721016873', 'Jakub Hain', '910514/1234', 2);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_lxbwavwbpiasxqlwy8andkktw` (`accNumber`),
  ADD UNIQUE KEY `UK_cdo7mbvgooqutipr7uhr4npin` (`cardNumber`),
  ADD KEY `FK_jfg97pwxxmwydwhcdehd2cs0v` (`userId`);

--
-- Indexes for table `rights`
--
ALTER TABLE `rights`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_53efjlgj97fwx9myi3h5jiv26` (`role`);

--
-- Indexes for table `templates`
--
ALTER TABLE `templates`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_6ffursp11cl0uswsi4dfl9dqn` (`idAcc`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_aw4px2pg2b2crielvb4f0ictd` (`idAcc`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK_ibcfppmacbm81husxf339yq0e` (`Email`),
  ADD UNIQUE KEY `UK_2ja5spbupxg6p2psmkpbmtv9m` (`Login`),
  ADD UNIQUE KEY `UK_ngjc5mk0qeh4bmpbdmqh912th` (`NID`),
  ADD KEY `FK_bamc1udtv3obxlvd4cbm57cmp` (`idRights`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `rights`
--
ALTER TABLE `rights`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `templates`
--
ALTER TABLE `templates`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `account`
--
ALTER TABLE `account`
  ADD CONSTRAINT `FK_jfg97pwxxmwydwhcdehd2cs0v` FOREIGN KEY (`userId`) REFERENCES `user` (`id`);

--
-- Constraints for table `templates`
--
ALTER TABLE `templates`
  ADD CONSTRAINT `FK_6ffursp11cl0uswsi4dfl9dqn` FOREIGN KEY (`idAcc`) REFERENCES `account` (`id`);

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `FK_aw4px2pg2b2crielvb4f0ictd` FOREIGN KEY (`idAcc`) REFERENCES `account` (`id`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `FK_bamc1udtv3obxlvd4cbm57cmp` FOREIGN KEY (`idRights`) REFERENCES `rights` (`id`);

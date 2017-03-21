-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Mar 21, 2017 at 04:23 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `easykyc`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  `role` varchar(20) NOT NULL,
  `email` varchar(64) NOT NULL,
  `token` varchar(128) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `id` int(11) NOT NULL,
  `txnId` varchar(225) NOT NULL,
  `schemaAddress` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`username`, `password`, `role`, `email`, `token`, `created_date`, `id`, `txnId`, `schemaAddress`) VALUES
('root', 'root', 'root_admin', 'rvshekhar10@gmail.com', 'a57d747a8034565e41936f9396279c10', '2017-03-21 02:48:34', 1, '', '');

-- --------------------------------------------------------

--
-- Table structure for table `document_list`
--

CREATE TABLE `document_list` (
  `id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL,
  `v_auth_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `dependency` int(1) NOT NULL,
  `txnId` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `document_list`
--

INSERT INTO `document_list` (`id`, `name`, `v_auth_id`, `timestamp`, `dependency`, `txnId`) VALUES
(17, 'KYC for SERVICE 1', 7, '2017-03-21 02:50:31', 0, ''),
(18, 'KYC for SERVICE 2', 7, '2017-03-21 02:51:28', 0, '');

-- --------------------------------------------------------

--
-- Table structure for table `support_doc`
--

CREATE TABLE `support_doc` (
  `id` int(11) NOT NULL,
  `parent_id` int(11) NOT NULL,
  `timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `doc_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `support_doc`
--

INSERT INTO `support_doc` (`id`, `parent_id`, `timestamp`, `doc_id`) VALUES
(1, 18, '2017-03-21 02:51:29', 17);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `CATEGORY_DESC_ENG` varchar(11) DEFAULT NULL,
  `STATE` varchar(64) NOT NULL,
  `MOTHER_NAME_ENG` varchar(225) DEFAULT NULL,
  `HOUSE_NUMBER_ENG` varchar(20) DEFAULT NULL,
  `RELATION_ENG` varchar(225) DEFAULT NULL,
  `DOB` varchar(64) NOT NULL,
  `MEMBER_AADHAR_ID` varchar(64) DEFAULT NULL,
  `ECONOMIC_GROUP` varchar(64) DEFAULT NULL,
  `BUILDING_NO_ENG` varchar(64) DEFAULT NULL,
  `BHAMASHAH_ID` varchar(64) NOT NULL,
  `STREET_NAME_ENG` varchar(64) DEFAULT NULL,
  `IFSC_CODE` varchar(64) DEFAULT NULL,
  `M_ID` varchar(64) DEFAULT NULL,
  `FAMILYIDNO` varchar(64) DEFAULT NULL,
  `PIN_CODE` varchar(64) DEFAULT NULL,
  `LANDLINE_NO` varchar(64) DEFAULT NULL,
  `VILLAGE_NAME` varchar(64) DEFAULT NULL,
  `GP_WARD` varchar(64) DEFAULT NULL,
  `EMAIL` varchar(225) DEFAULT NULL,
  `SPOUCE_NAME_ENG` varchar(225) DEFAULT NULL,
  `IS_RURAL` varchar(64) DEFAULT NULL,
  `DISTRICT` varchar(64) DEFAULT NULL,
  `EDUCATION_DESC_ENG` varchar(64) DEFAULT NULL,
  `ACC_NO` varchar(64) DEFAULT NULL,
  `ADDRESS` text,
  `INCOME_DESC_ENG` varchar(64) DEFAULT NULL,
  `BANK_NAME` varchar(64) DEFAULT NULL,
  `LAND_MARK_ENG` varchar(64) DEFAULT NULL,
  `RATION_CARD_NO` varchar(64) DEFAULT NULL,
  `NAME_ENG` varchar(64) NOT NULL,
  `MOBILE` varchar(64) NOT NULL,
  `GENDER` varchar(64) NOT NULL,
  `FATHER_NAME_ENG` varchar(64) DEFAULT NULL,
  `FAX_NO` varchar(64) DEFAULT NULL,
  `BLOCK_CITY` varchar(64) DEFAULT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `txnId` varchar(225) NOT NULL,
  `schemaAddress` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `CATEGORY_DESC_ENG`, `STATE`, `MOTHER_NAME_ENG`, `HOUSE_NUMBER_ENG`, `RELATION_ENG`, `DOB`, `MEMBER_AADHAR_ID`, `ECONOMIC_GROUP`, `BUILDING_NO_ENG`, `BHAMASHAH_ID`, `STREET_NAME_ENG`, `IFSC_CODE`, `M_ID`, `FAMILYIDNO`, `PIN_CODE`, `LANDLINE_NO`, `VILLAGE_NAME`, `GP_WARD`, `EMAIL`, `SPOUCE_NAME_ENG`, `IS_RURAL`, `DISTRICT`, `EDUCATION_DESC_ENG`, `ACC_NO`, `ADDRESS`, `INCOME_DESC_ENG`, `BANK_NAME`, `LAND_MARK_ENG`, `RATION_CARD_NO`, `NAME_ENG`, `MOBILE`, `GENDER`, `FATHER_NAME_ENG`, `FAX_NO`, `BLOCK_CITY`, `created_date`, `txnId`, `schemaAddress`) VALUES
(10, NULL, '', NULL, NULL, NULL, '01-JAN-64', '479986973003', NULL, NULL, '9999-ZEH1-00068', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 'Anita', '', 'Female', NULL, NULL, NULL, '2017-03-21 02:52:43', 'e06f652172e810e6df1921e5ca3bb2f5def73cd1d65a4c40bf946aa9b545b383\r\n			', 'USERS');

-- --------------------------------------------------------

--
-- Table structure for table `user_doc`
--

CREATE TABLE `user_doc` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `status` int(1) NOT NULL,
  `verification` int(11) DEFAULT NULL,
  `doc_id` int(11) NOT NULL,
  `txnId` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user_doc`
--

INSERT INTO `user_doc` (`id`, `user_id`, `created_date`, `status`, `verification`, `doc_id`, `txnId`) VALUES
(2, 10, '2017-03-21 02:53:08', 101, 123, 17, 'bc4ba5eadc6819b9519afa2dbb9111b7acc8feb443803b1b5d359fc023ade1fe\r\n			'),
(3, 10, '2017-03-21 02:53:11', 101, 123, 18, '6a5fa411509424d3495df9ec0d2488d90c0ddda7ebfecf8bcca1e11c48093b74\r\n			');

-- --------------------------------------------------------

--
-- Table structure for table `v_auth`
--

CREATE TABLE `v_auth` (
  `id` int(11) NOT NULL,
  `name` varchar(225) NOT NULL,
  `status` int(11) NOT NULL,
  `created_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `type` varchar(20) NOT NULL,
  `userid` varchar(225) NOT NULL,
  `password` varchar(225) NOT NULL,
  `token` varchar(128) NOT NULL,
  `txnId` varchar(225) NOT NULL,
  `schemaAddress` varchar(64) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `v_auth`
--

INSERT INTO `v_auth` (`id`, `name`, `status`, `created_date`, `type`, `userid`, `password`, `token`, `txnId`, `schemaAddress`) VALUES
(7, 'Authenticator 1', 1, '2017-03-21 02:51:08', 'Govt.', 'authenticator1', 'authenticator1', '4252be3cb744554d2e77cf6e805b37e4', 'fbb892eae74354829ea6d258f23aa59c9d1e47781fe1b86d358281c6666d750e\r\n			', '3RDPARTY');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `document_list`
--
ALTER TABLE `document_list`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `support_doc`
--
ALTER TABLE `support_doc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user_doc`
--
ALTER TABLE `user_doc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `v_auth`
--
ALTER TABLE `v_auth`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `document_list`
--
ALTER TABLE `document_list`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `support_doc`
--
ALTER TABLE `support_doc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;
--
-- AUTO_INCREMENT for table `user_doc`
--
ALTER TABLE `user_doc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT for table `v_auth`
--
ALTER TABLE `v_auth`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

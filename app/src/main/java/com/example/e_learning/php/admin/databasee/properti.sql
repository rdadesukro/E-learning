-- phpMyAdmin SQL Dump
-- version 3.2.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: May 12, 2017 at 10:11 
-- Server version: 5.1.41
-- PHP Version: 5.3.1

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `properti`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `id_admin` int(1) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id_admin`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id_admin`, `username`, `password`) VALUES
(1, 'dina', '1');

-- --------------------------------------------------------

--
-- Table structure for table `alarm`
--

CREATE TABLE IF NOT EXISTS `alarm` (
  `id_a` int(2) NOT NULL AUTO_INCREMENT,
  `alarm` varchar(20) NOT NULL,
  `file` varchar(40) NOT NULL,
  PRIMARY KEY (`id_a`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `alarm`
--

INSERT INTO `alarm` (`id_a`, `alarm`, `file`) VALUES
(1, 'Chattingawd', 'Ending 2 â€“ Run! Run! Run! â€“ Maki Ots'),
(2, 'Property Barudawdawd', '11. Shouchi no Suke.mp3');

-- --------------------------------------------------------

--
-- Table structure for table `chatting`
--

CREATE TABLE IF NOT EXISTS `chatting` (
  `id_chatting` int(5) NOT NULL AUTO_INCREMENT,
  `id_admin` int(3) NOT NULL,
  `nama` varchar(30) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `email` varchar(30) NOT NULL,
  `tgl_jam` datetime NOT NULL,
  PRIMARY KEY (`id_chatting`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `chatting`
--

INSERT INTO `chatting` (`id_chatting`, `id_admin`, `nama`, `no_hp`, `email`, `tgl_jam`) VALUES
(1, 1, 'ada', '789', 'awd', '2017-05-10 20:44:54'),
(2, 1, 'daw', '798', 'awda', '2017-05-15 20:45:05');

-- --------------------------------------------------------

--
-- Table structure for table `daftar`
--

CREATE TABLE IF NOT EXISTS `daftar` (
  `id_daftar` int(5) NOT NULL AUTO_INCREMENT,
  `nama` varchar(40) NOT NULL,
  `no_hp` varchar(13) NOT NULL,
  `email` varchar(30) NOT NULL,
  `alamat` varchar(70) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id_daftar`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `daftar`
--

INSERT INTO `daftar` (`id_daftar`, `nama`, `no_hp`, `email`, `alamat`, `password`) VALUES
(1, 'awd', '0980', 'awdd', 'awd', 'a');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE IF NOT EXISTS `kategori` (
  `id_kategori` int(3) NOT NULL AUTO_INCREMENT,
  `kategori` varchar(30) NOT NULL,
  PRIMARY KEY (`id_kategori`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `kategori`) VALUES
(1, 'Tanah'),
(2, 'Rumah'),
(3, 'Mobil'),
(4, 'Motor'),
(9, 'a'),
(10, 'b'),
(11, 'ad'),
(12, 'a'),
(13, 'awd'),
(14, 'awd'),
(15, 'a');

-- --------------------------------------------------------

--
-- Table structure for table `kontak`
--

CREATE TABLE IF NOT EXISTS `kontak` (
  `id_k` int(1) NOT NULL AUTO_INCREMENT,
  `deskripsi` text NOT NULL,
  PRIMARY KEY (`id_k`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `kontak`
--

INSERT INTO `kontak` (`id_k`, `deskripsi`) VALUES
(1, '<p>No Pak De : 08676757 aa33jjjsefsfe</p>\r\n');

-- --------------------------------------------------------

--
-- Table structure for table `properti`
--

CREATE TABLE IF NOT EXISTS `properti` (
  `id_p` int(5) NOT NULL AUTO_INCREMENT,
  `nama_p` varchar(40) NOT NULL,
  `id_kategori` varchar(3) NOT NULL,
  `id_daftar` varchar(5) NOT NULL,
  `deskripsi` text NOT NULL,
  `dibaca` enum('SD','BD') NOT NULL,
  `tgl_jam` datetime NOT NULL,
  PRIMARY KEY (`id_p`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `properti`
--

INSERT INTO `properti` (`id_p`, `nama_p`, `id_kategori`, `id_daftar`, `deskripsi`, `dibaca`, `tgl_jam`) VALUES
(1, 'rdgrd', '1', '1', 'awdwa', 'SD', '2017-05-16 10:11:00'),
(2, 'sefes', '2', '1', 'awdaw', 'BD', '2017-05-15 11:11:00');

-- --------------------------------------------------------

--
-- Table structure for table `properti_info`
--

CREATE TABLE IF NOT EXISTS `properti_info` (
  `id_pi` int(5) NOT NULL AUTO_INCREMENT,
  `nama_pi` varchar(40) NOT NULL,
  `id_kategori` int(3) NOT NULL,
  `kamar_tidur` int(2) NOT NULL,
  `kamar_mandi` int(2) NOT NULL,
  `luas_bangunan` int(4) NOT NULL,
  `luas_tanah` int(4) NOT NULL,
  `rekomendasi` enum('Ya','Tidak') NOT NULL,
  `view` int(5) NOT NULL,
  `latitude` double NOT NULL,
  `longitude` double NOT NULL,
  `kpr` int(9) NOT NULL,
  `harga` int(11) NOT NULL,
  `info_properti` text NOT NULL,
  `tgl_jam` datetime NOT NULL,
  PRIMARY KEY (`id_pi`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data for table `properti_info`
--

INSERT INTO `properti_info` (`id_pi`, `nama_pi`, `id_kategori`, `kamar_tidur`, `kamar_mandi`, `luas_bangunan`, `luas_tanah`, `rekomendasi`, `view`, `latitude`, `longitude`, `kpr`, `harga`, `info_properti`, `tgl_jam`) VALUES
(1, '1', 3, 11, 111, 1, 11, 'Tidak', 2, 111, 111, 1, 1, '<p>11efsesf</p>\r\n', '2017-05-31 10:00:10'),
(2, 'adwa', 2, 23, 21, 2, 23, 'Ya', 12, 234, 432, 1241, 234, 'efsfe', '2017-05-15 11:11:00'),
(3, 'a', 2, 2, 3, 2, 2, 'Tidak', 0, 33, 33, 33, 234, '', '0000-00-00 00:00:00'),
(4, '9', 4, 9, 9, 9, 9, 'Tidak', 0, 9, 9, 9, 9, '<p>99awdwad</p>\r\n', '2017-05-10 13:59:17'),
(9, '8', 2, 8, 8, 8, 8, 'Tidak', 0, 8, 8, 8, 8, '<p>87</p>\r\n', '2017-05-12 21:22:01');

-- --------------------------------------------------------

--
-- Table structure for table `rating`
--

CREATE TABLE IF NOT EXISTS `rating` (
  `id_rating` int(5) NOT NULL AUTO_INCREMENT,
  `id_pi` int(5) NOT NULL,
  `b1` int(5) NOT NULL,
  `b2` int(5) NOT NULL,
  `b3` int(5) NOT NULL,
  `b4` int(5) NOT NULL,
  `b5` int(5) NOT NULL,
  PRIMARY KEY (`id_rating`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `rating`
--

INSERT INTO `rating` (`id_rating`, `id_pi`, `b1`, `b2`, `b3`, `b4`, `b5`) VALUES
(1, 1, 2, 4, 2, 2, 2);

-- --------------------------------------------------------

--
-- Table structure for table `syarat`
--

CREATE TABLE IF NOT EXISTS `syarat` (
  `id_s` int(5) NOT NULL AUTO_INCREMENT,
  `syarat` text NOT NULL,
  PRIMARY KEY (`id_s`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=19 ;

--
-- Dumping data for table `syarat`
--

INSERT INTO `syarat` (`id_s`, `syarat`) VALUES
(5, 'awd'),
(6, 'awd'),
(7, 'sfeq'),
(8, 'a'),
(9, 'dbjh');

-- --------------------------------------------------------

--
-- Table structure for table `tentang`
--

CREATE TABLE IF NOT EXISTS `tentang` (
  `id_t` int(2) NOT NULL AUTO_INCREMENT,
  `gambar` varchar(40) NOT NULL,
  `deskripsi` text NOT NULL,
  PRIMARY KEY (`id_t`)
) ENGINE=MyISAM  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `tentang`
--

INSERT INTO `tentang` (`id_t`, `gambar`, `deskripsi`) VALUES
(1, 'submit.png', '<p>ddd aa</p>\r\n');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

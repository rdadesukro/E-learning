-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 25, 2020 at 09:56 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `e-leraning`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(10) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `username`, `password`) VALUES
(1, 'ejak', '1');

-- --------------------------------------------------------

--
-- Table structure for table `berita`
--

CREATE TABLE `berita` (
  `id_berita` int(11) NOT NULL,
  `judul` varchar(50) DEFAULT NULL,
  `jenis` varchar(50) DEFAULT NULL,
  `waktu` datetime DEFAULT NULL,
  `isi` text DEFAULT NULL,
  `foto` text DEFAULT NULL,
  `status` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `berita`
--

INSERT INTO `berita` (`id_berita`, `judul`, `jenis`, `waktu`, `isi`, `foto`, `status`) VALUES
(1, 'ADE', 'penting', '2019-05-09 23:59:00', 'Berdasarkan use case diatas yang menggambarkan interaksi beberapa aktor sebagai user terhadap sistem aplikasi tersebut, menjelaskan langkah-langkah bagaimana admin sebagai pengelola sistem dapat mengoperasikan menu-menu yang terdapat pada sistem tersebut seperti menu mengelola data admin, menu mengelola data anggota, menu mengelola data tarif SPP, menu mengelola data tahun pelajaran, menu mengelola kelas, menu mengelola pembayaran SPP, menu cetak rekap pembayaran SPP, serta menu keluar / log out  untuk keluar dari akses / log in sistem.  ', 'tes.jpg', '1'),
(2, 'ADE', 'penting', '2019-05-09 00:00:00', 'Berdasarkan use case diatas yang menggambarkan interaksi beberapa aktor sebagai user terhadap sistem aplikasi tersebut, menjelaskan langkah-langkah bagaimana admin sebagai pengelola sistem dapat mengoperasikan menu-menu yang terdapat pada sistem tersebut seperti menu mengelola data admin, menu mengelola data anggota, menu mengelola data tarif SPP, menu mengelola data tahun pelajaran, menu mengelola kelas, menu mengelola pembayaran SPP, menu cetak rekap pembayaran SPP, serta menu keluar / log out  untuk keluar dari akses / log in sistem.  ', 'tes.jpg', '0');

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `id_guru` int(10) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `ttl` date DEFAULT NULL,
  `jk` char(40) DEFAULT NULL,
  `agama` varchar(50) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `id_mapel` int(10) DEFAULT NULL,
  `foto` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`id_guru`, `nama`, `ttl`, `jk`, `agama`, `alamat`, `id_mapel`, `foto`) VALUES
(1, 'Tantok', '2020-07-11', 'LK', 'ISLAM', 'a', 2, 'ctscmqfsa4fj6cnmjmfp.png');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `id_kelas` int(10) NOT NULL,
  `nama_kelas` char(20) DEFAULT NULL,
  `id_guru` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`id_kelas`, `nama_kelas`, `id_guru`) VALUES
(1, 'XII IPA', 1),
(3, 'XII IPA 2', 0);

-- --------------------------------------------------------

--
-- Table structure for table `mapel`
--

CREATE TABLE `mapel` (
  `id_mapel` int(11) NOT NULL,
  `nama_mapel` varchar(100) DEFAULT NULL,
  `foto_mapel` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `mapel`
--

INSERT INTO `mapel` (`id_mapel`, `nama_mapel`, `foto_mapel`) VALUES
(1, 'Pemelihaaraan AC XXX', '5wae5k8fgx737h8dr4u1.png'),
(2, 'Pemeliharan Service Engine', 'WhatsApp Image 2020-04-13 at 08.24.58.jpeg');

-- --------------------------------------------------------

--
-- Table structure for table `mapel_new`
--

CREATE TABLE `mapel_new` (
  `id_mapel` int(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `id_kelas` int(10) NOT NULL,
  `id_guru` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `materi`
--

CREATE TABLE `materi` (
  `id` int(10) NOT NULL,
  `id_mapel` int(10) DEFAULT NULL,
  `bab` varchar(50) DEFAULT NULL,
  `pdf_url` varchar(200) DEFAULT NULL,
  `pdf_icon` varchar(200) DEFAULT NULL,
  `id_guru` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `materi`
--

INSERT INTO `materi` (`id`, `id_mapel`, `bab`, `pdf_url`, `pdf_icon`, `id_guru`) VALUES
(40, 1, 'BAB 1', 'jvjwhs2hnpjm3yiy0z9j36.pdf', '', 1),
(41, 2, 'NEW', 'kdtfwmnyti1kzp7qzsd335.pdf', '', 1);

-- --------------------------------------------------------

--
-- Table structure for table `nilai`
--

CREATE TABLE `nilai` (
  `id_nilai` int(10) NOT NULL,
  `nis_siswa` int(10) DEFAULT NULL,
  `id_kelas` int(10) DEFAULT NULL,
  `smester` char(2) DEFAULT NULL,
  `id_mapel` int(10) DEFAULT NULL,
  `nilai` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `nilai`
--

INSERT INTO `nilai` (`id_nilai`, `nis_siswa`, `id_kelas`, `smester`, `id_mapel`, `nilai`) VALUES
(1, 1, 1, '1', 1, 90),
(2, 1, 1, '2', 2, 85),
(3, 1, 1, '2', 1, 50);

-- --------------------------------------------------------

--
-- Table structure for table `pdftable`
--

CREATE TABLE `pdftable` (
  `id` int(10) NOT NULL,
  `PdfURL` varchar(100) DEFAULT NULL,
  `PdfName` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pdftable`
--

INSERT INTO `pdftable` (`id`, `PdfURL`, `PdfName`) VALUES
(1, 'http://192.168.43.48/e-learning/pdf/1.pdf', 'fccc'),
(2, 'http://192.168.43.48/e-learning/pdf/2.pdf', 'fccc'),
(3, 'pdf/3.pdf', 'ade');

-- --------------------------------------------------------

--
-- Table structure for table `quiz`
--

CREATE TABLE `quiz` (
  `id_quiz` int(10) NOT NULL,
  `nis` int(10) DEFAULT NULL,
  `mapel` int(10) DEFAULT NULL,
  `nilai` varchar(50) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  `id_guru` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `quiz`
--

INSERT INTO `quiz` (`id_quiz`, `nis`, `mapel`, `nilai`, `status`, `id_guru`) VALUES
(22, 2, 2, '20', '2', 2),
(23, 2, 1, '40', '1', 2),
(24, 3, 1, '40', '1', 3),
(25, 3, 2, '30', '2', 3);

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `nis` int(10) NOT NULL,
  `nama` varchar(100) DEFAULT NULL,
  `kelas` varchar(50) DEFAULT NULL,
  `ttl` varchar(50) DEFAULT NULL,
  `jenkel` char(2) DEFAULT NULL,
  `alamat` varchar(100) DEFAULT NULL,
  `agama` varchar(100) DEFAULT NULL,
  `foto` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`nis`, `nama`, `kelas`, `ttl`, `jenkel`, `alamat`, `agama`, `foto`) VALUES
(2, 'ari', 'XII IPA 12', '5/7/2019', 'LK', 'Jambi new', 'Islam', '99z57u67p43n70acaty7.png'),
(3, 'budi new', 'XII IPA', 'jambi5/7/2019', 'PR', 'jambi', 'Islam asli', '99z57u67p43n70acaty7.png');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `nis` int(10) NOT NULL,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `level` varchar(50) DEFAULT NULL,
  `kunci` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`nis`, `username`, `password`, `level`, `kunci`) VALUES
(1, 'admin', 'c4ca4238a0b923820dcc509a6f75849b', 'guru', 'null'),
(2, 'ari', 'c4ca4238a0b923820dcc509a6f75849b', 'siswa', 'ari'),
(3, 'budi', 'c4ca4238a0b923820dcc509a6f75849b', 'siswa', 'budi');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `berita`
--
ALTER TABLE `berita`
  ADD PRIMARY KEY (`id_berita`);

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`id_guru`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id_kelas`);

--
-- Indexes for table `mapel`
--
ALTER TABLE `mapel`
  ADD PRIMARY KEY (`id_mapel`);

--
-- Indexes for table `mapel_new`
--
ALTER TABLE `mapel_new`
  ADD PRIMARY KEY (`id_mapel`);

--
-- Indexes for table `materi`
--
ALTER TABLE `materi`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `nilai`
--
ALTER TABLE `nilai`
  ADD PRIMARY KEY (`id_nilai`);

--
-- Indexes for table `pdftable`
--
ALTER TABLE `pdftable`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `quiz`
--
ALTER TABLE `quiz`
  ADD PRIMARY KEY (`id_quiz`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`nis`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`nis`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `berita`
--
ALTER TABLE `berita`
  MODIFY `id_berita` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `kelas`
--
ALTER TABLE `kelas`
  MODIFY `id_kelas` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `mapel`
--
ALTER TABLE `mapel`
  MODIFY `id_mapel` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `mapel_new`
--
ALTER TABLE `mapel_new`
  MODIFY `id_mapel` int(10) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `materi`
--
ALTER TABLE `materi`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `nilai`
--
ALTER TABLE `nilai`
  MODIFY `id_nilai` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `pdftable`
--
ALTER TABLE `pdftable`
  MODIFY `id` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `quiz`
--
ALTER TABLE `quiz`
  MODIFY `id_quiz` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

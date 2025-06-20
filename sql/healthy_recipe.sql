-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Waktu pembuatan: 20 Jun 2025 pada 10.27
-- Versi server: 10.4.32-MariaDB
-- Versi PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `healthy_recipe`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `pasien`
--

CREATE TABLE `pasien` (
  `id` int(11) NOT NULL,
  `nama_pasien` varchar(100) DEFAULT NULL,
  `tanggal_kunjungan` date DEFAULT NULL,
  `tanggal_lahir` date DEFAULT NULL,
  `jenis_kelamin` enum('Laki-laki','Perempuan') DEFAULT NULL,
  `alamat` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `pasien`
--

INSERT INTO `pasien` (`id`, `nama_pasien`, `tanggal_kunjungan`, `tanggal_lahir`, `jenis_kelamin`, `alamat`) VALUES
(1, 'Dwi rahma wati', '2025-06-14', '0000-00-00', 'Perempuan', 'Jl.Penggilingan'),
(2, 'Yaumil hamida', '2025-06-14', '0000-00-00', 'Perempuan', 'Jl.Cipinang'),
(3, 'husnul khawatimi', '2025-06-13', '0000-00-00', 'Perempuan', 'Jl. priok'),
(4, 'Fairus', '2025-06-11', '0000-00-00', 'Perempuan', 'Jakarta selatan'),
(5, 'Septiana anggi', '2025-06-07', '0000-00-00', 'Perempuan', 'Jl. Sumur Batu'),
(6, 'Bella', '2025-06-16', '0000-00-00', 'Perempuan', 'Jl. Cempaka Baru'),
(7, 'Alvian Valentino Ferry', '2025-06-16', '2005-02-14', 'Laki-laki', 'Jl. Harapan Mulia'),
(8, 'Anto', '2025-06-19', '2004-06-18', 'Laki-laki', 'Jl. Cempaka putih'),
(9, 'sinta', '2025-06-19', '2006-06-10', 'Perempuan', 'Jl. jiung'),
(10, 'rani', '2025-06-20', '2025-06-19', 'Perempuan', 'Jl. kemayoran'),
(11, 'Andi', '2025-06-19', '2008-06-21', 'Laki-laki', 'Jl. cempaka putih');

-- --------------------------------------------------------

--
-- Struktur dari tabel `resep_obat`
--

CREATE TABLE `resep_obat` (
  `id` int(11) NOT NULL,
  `nama_pasien` varchar(100) DEFAULT NULL,
  `diagnosa` text DEFAULT NULL,
  `nama_obat` varchar(100) DEFAULT NULL,
  `dosis` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `resep_obat`
--

INSERT INTO `resep_obat` (`id`, `nama_pasien`, `diagnosa`, `nama_obat`, `dosis`) VALUES
(1, 'Fairus', 'Demam, Radang tenggorokan, Sakit gigi', 'Paracetamol, Dextromethorphan, Amoxicillin', '2 x 1 hari sesudah makan, 1 x 1 hari sebelum makan, 2 x 1 hari sesudah makan'),
(4, 'Septiana anggi', 'Batuk , Demam, Radang tenggorokan', 'Paracetamol, Dextromethorphan, Amoxicillin', '1 x 1 hari sesudah makan, 2 x 1 hari sesudah makan, 2 x 1 hari sesudah makan'),
(6, 'husnul khawatimi', 'Demam, Radang tenggorokan, Batuk ', 'Paracetamol, Promag, Dextromethorphan', '3 x 1 hari sesuadah makan, 1 x 1 hari sebelum makan, 3 x 1 hari sesuadah makan'),
(7, 'Bella', 'Demam, Batuk , Diare', 'Paracetamol, Dextromethorphan, Amoxicillin', '1 x 1 hari sesudah makan, 2 x 1 hari sesudah makan, 2 x 1 hari sesudah makan'),
(8, 'husnul khawatimi', 'Demam, Radang tenggorokan', 'Paracetamol, Dextromethorphan', '1 x 1 hari sesudah makan, 1 x 1 hari sesudah makan'),
(9, 'Alvian Valentino Ferry', 'Demam, Batuk ', 'Paracetamol, Dextromethorphan', '1 x 1 hari sesudah makan, 2 x 1 hari sesudah makan'),
(10, 'Anto', 'Batuk ', 'Paracetamol', '1 x 1 hari sesudah makan'),
(11, 'husnul khawatimi', 'Radang tenggorokan, Batuk ', 'Paracetamol', '3 x 1 hari sesuadah makan'),
(13, 'rani', 'Demam', 'Paracetamol', '1 x 1 hari sesudah makan'),
(14, 'Bella', 'Demam, Batuk ', 'Paracetamol, Amoxicillin', '1 x 1 hari sesudah makan, 1 x 1 hari sesudah makan'),
(15, 'Dwi rahma wati', 'Sakit gigi', 'Ponstan', '1 x 1 hari sesudah makan'),
(16, 'rani', 'Diare', 'Oralit', '1 x 1 hari sesudah makan'),
(17, 'Dwi rahma wati', 'Asam lambung', 'Amoxicillin', '1 x 1 hari sesudah makan'),
(18, 'Andi', 'Batuk ', 'Dextromethorphan, Amoxicillin', '1 x 1 hari sesudah makan, 1 x 1 hari sesudah makan'),
(19, 'husnul khawatimi', 'Batuk ', 'Dextromethorphan', '1 x 1 hari sesudah makan'),
(20, 'Bella', 'Asam lambung', 'Paracetamol', '1 x 1 hari sesudah makan');

-- --------------------------------------------------------

--
-- Struktur dari tabel `users`
--

CREATE TABLE `users` (
  `id_user` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `users`
--

INSERT INTO `users` (`id_user`, `username`, `password`, `role`) VALUES
(1, 'khansa', '12345678', 'dokter'),
(2, 'rahma', '87654321', 'staff farmasi'),
(5, 'hamida', '34567891', 'staff farmasi'),
(6, 'khawatimi', '11121314', 'dokter');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `pasien`
--
ALTER TABLE `pasien`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `resep_obat`
--
ALTER TABLE `resep_obat`
  ADD PRIMARY KEY (`id`);

--
-- Indeks untuk tabel `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_user`),
  ADD UNIQUE KEY `username` (`username`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `pasien`
--
ALTER TABLE `pasien`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT untuk tabel `resep_obat`
--
ALTER TABLE `resep_obat`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- AUTO_INCREMENT untuk tabel `users`
--
ALTER TABLE `users`
  MODIFY `id_user` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

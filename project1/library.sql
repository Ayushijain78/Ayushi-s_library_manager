-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 22, 2020 at 04:14 PM
-- Server version: 10.1.31-MariaDB
-- PHP Version: 5.6.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `library`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE `admin` (
  `id` int(50) NOT NULL,
  `name` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `pwd` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`id`, `name`, `email`, `phone`, `pwd`) VALUES
(1, 'ayushi', 'ayu@gmail.com', '7247302898', '12345');

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `book_id` int(50) NOT NULL,
  `isbn` int(20) NOT NULL,
  `b_name` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `qty` int(50) NOT NULL,
  `status` enum('available','issued') NOT NULL,
  `price` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`book_id`, `isbn`, `b_name`, `author`, `qty`, `status`, `price`) VALUES
(1, 121, 'english ', 'nabom', 20, 'available', 120),
(2, 101, 'network', 'frozen', 19, 'available', 200),
(3, 102, 'mobile', 'tennon', 10, 'available', 200),
(4, 103, 'english', 'nab', 20, 'available', 205),
(5, 105, 'microsprocessor', 'bhurchandi', 40, 'available', 500),
(6, 106, 'ramayan', 'maharshi balmiki', 5, 'available', 200),
(10, 107, 'hindi', 'dhracharya', 25, 'available', 450),
(11, 122, 'OPERATING SYSTEM', 'GALVIN', 50, 'available', 250),
(12, 123, 'malala', 'ravi prakash javedkar', 25, 'available', 260),
(13, 124, 'Godan', 'Munshi Premchand', 25, 'available', 865),
(14, 125, 'the powe of mind', 'st. josheph', 25, 'available', 100),
(15, 126, 'how to influence', 'joseph', 8, 'available', 500),
(16, 127, 'maths', 'rs agrawal', 12, 'available', 200),
(17, 128, 'english ', 'nab', 12, 'available', 400),
(18, 129, 'data  structures', 'soham', 2, 'available', 1230),
(19, 130, 'algorithms', 'coreman', 12, 'available', 1200),
(20, 131, 'c++', 'yashwan kanetkar', 13, 'available', 120),
(21, 132, 'programing in c', 'yashwant kanetkar', 12, 'available', 200),
(22, 133, 'datacommunication', 'shiri rk panit', 20, 'available', 200),
(23, 134, 'economics ', 'rs gupta', 12, 'available', 120),
(24, 135, 'webdevops', 'herry', 12, 'available', 250);

-- --------------------------------------------------------

--
-- Table structure for table `issue`
--

CREATE TABLE `issue` (
  `id` int(11) NOT NULL,
  `isbn` int(50) NOT NULL,
  `b_name` varchar(50) NOT NULL,
  `author` varchar(50) NOT NULL,
  `roll_no` int(50) NOT NULL,
  `sname` varchar(50) NOT NULL,
  `date_of_issue` date NOT NULL,
  `date_of_return` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE `student` (
  `id` int(100) NOT NULL,
  `roll_no` int(100) NOT NULL,
  `name` varchar(50) NOT NULL,
  `gender` varchar(10) NOT NULL,
  `course` varchar(50) NOT NULL,
  `email` varchar(50) NOT NULL,
  `mobile` varchar(10) NOT NULL,
  `pwd` varchar(20) NOT NULL,
  `image` longblob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin`
--
ALTER TABLE `admin`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`book_id`),
  ADD UNIQUE KEY `isbn` (`isbn`);

--
-- Indexes for table `issue`
--
ALTER TABLE `issue`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin`
--
ALTER TABLE `admin`
  MODIFY `id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `book_id` int(50) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;

--
-- AUTO_INCREMENT for table `issue`
--
ALTER TABLE `issue`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `student`
--
ALTER TABLE `student`
  MODIFY `id` int(100) NOT NULL AUTO_INCREMENT;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

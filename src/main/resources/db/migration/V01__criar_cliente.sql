CREATE TABLE cliente(
   id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
   nome VARCHAR(50) NOT NULL,
   idade INTEGER NOT NULL,
   temperatura_minima_dia VARCHAR(15),
   temperatura_maxima_dia VARCHAR(15)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- Creación de la tabla User
CREATE TABLE User (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100),
    password VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),
    image VARCHAR(255),
    disponible BOOLEAN,
    token VARCHAR(255)
);


-- Insert de datos provenientes de Employee
INSERT INTO User (id, dni, name, password, email, phone, image, disponible, token) VALUES
(1, '600123001', 'Juan Pérez', '$2a$12$nTeIMaCnIcvBJlug2YLwGOzVd9gB9bSYN7wZZnZL4/KaOWJqeVkcK', '', '600123001', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(2, '600123002', 'María López', '$2a$12$jkxtj76TpGMKlhzH/FjX6./f4uZ8EWD.fJunzhWdS1PbYebRSd1P6', '', '600123002', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(3, '600123003', 'Carlos Sánchez', '$2a$12$h7OELt9pBczNOP/FYnlo4OBJsDyd47CYFVzyTEiFgqc5eER/1fyNq', '', '600123003', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', FALSE, ''),
(4, '600123004', 'Ana García', '$2a$12$2N4jP.Zq9dQGxNmxAZHUH.C3/G1ukWlfMkRYqcbUaJDpvDlRgdDIK', '', '600123004', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(5, '600123005', 'Luis Torres', '$2a$12$32AvPGspcHV5gal/UYiIIuGH8uKf7eYGhGkHUwLbiDnKSwiHdRAKC', '', '600123005', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(6, '600123006', 'Lucía Martínez', '$2a$12$9C2gqnL16PXA.PiHApJ4M.ZPl4x0LuC8KjiUf1NJ6pmfQvQ0N7pwi', '', '600123006', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', FALSE, ''),
(7, '600123007', 'Miguel Romero', '$2a$12$iHDO3r.sYslAOvxhWj9ZNOZJf3excUwvl5dVVCKwt9ngVDOI9tWeG', '', '600123007', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(8, '600123008', 'Sofía Jiménez', '$2a$12$1wor8Zdzb8V6gLoUWDLUa.F7/6jlcYCw0i8yjERGAqAYkjIThWBzy', '', '600123008', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(9, '600123009', 'Pedro Castillo', '$2a$12$PVhmyBSkwXQKoL3dTfjX2uDNpO4/Tu0HFqea2qavLZQL4viIvZtMu', '', '600123009', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', FALSE, ''),
(10, '600123010', 'Laura Díaz', '$2a$12$crj6TZVB2lZtdjtASzh0mOyOaNwKgRIiap1YHmeVdwcO.ieTMcvpi', '', '600123010', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(11, '600123011', 'Diego Gómez', '$2a$12$bAW5C3B4t1RpIVNi0/uiqO6.eQ0EDqsGuiEV6HKqvBRbY0djN62Iy', '', '600123011', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(12, '600123012', 'Natalia Ruiz', '$2a$12$bMwY96mmJHwSZLfCh1c24.VfsnOpjmOMCjtSiazOlZFaORo7KcSQO', '', '600123012', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', FALSE, ''),
(13, '600123013', 'Javier Moreno', '$2a$12$c.oyYsEDm0QYS4tHVcfzOeeF/OU3iilC3rRWreNeqsiUdEwifFbh.', '', '600123013', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(14, '600123014', 'Elena Vega', '$2a$12$I6xbbL3QU2xfbpzfTufcfew0kmD33aVkpvZDp7bj5.yUvUul5EZsC', '', '600123014', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(15, '600123015', 'Ricardo Flores', '$2a$12$Jaz5Kh7bxukUOaomkWbRYOotDTu1p2Au.LkhbNvcg/T13CAyuDtSe', '', '600123015', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(16, '600123016', 'Patricia Navarro', '$2a$12$dV7STyQ475l3jYpoeSy2oOpwwIYyWccrJn5nwqCbbxY7XiVzpUnWm', '', '600123016', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(17, '600123017', 'Fernando Paredes', '$2a$12$V3ek4s6SJAJTK1nqn3b0O.i2u6tBsJouOx.JWMrqQivlcE2G3fzhu', '', '600123017', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', FALSE, ''),
(18, '600123018', 'Cristina Sáez', '$2a$12$OLO4c91R.K0CFmId7v81j.4VIiOAqn1ABH9aBC3Ac3npb6MWAfVQu', '', '600123018', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, ''),
(19, '600123019', 'Raúl Castro', '$2a$12$hDFf6YQwQlv.IVYh64BhA2g3nEXgK46y3uDd1pVZ7hHkXZLNm5bgq', '', '600123019', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', FALSE, ''),
(20, '600123020', 'Inés Sánchez', '$2a$12$zjfHzpz8hzTp8hlUoToCmOkv9gd9/fdScdzgGyjBbr0BFOttds0wA', '', '600123020', 'https://cdn.pixabay.com/photo/2023/05/27/19/15/call-center-8022155_960_720.jpg', TRUE, '');

-- Creación de la tabla User
DROP TABLE User;

CREATE TABLE IF NOT EXISTS `User` (
    id INT AUTO_INCREMENT PRIMARY KEY,
    dni VARCHAR(20) UNIQUE NOT NULL,
    name VARCHAR(100),
    password VARCHAR(255),
    email VARCHAR(255),
    phone VARCHAR(20),  -- Cambiado a VARCHAR para tus datos
    image VARCHAR(255),
    disponible BOOLEAN,
    token VARCHAR(255) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS Book (
    id INT AUTO_INCREMENT PRIMARY KEY,
    isbn VARCHAR(255) UNIQUE NOT NULL,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(255) NOT NULL,
    image VARCHAR(255)
);

-- Insert de datos provenientes de Employee
INSERT INTO User (id, dni, name, password, email, phone, image, disponible) VALUES
(1, '12345678A', 'Antonio Pérez', '$2b$10$v1S9kU3KJvP4D8D76cGpZeb8JczFuLzNMeCMunh41Z3yYqVuOaG4C', 'antonio.perez@email.com', '600123456', 'antonio.jpg', 1),
(2, '23456789B', 'María López', '$2b$10$e3PzB5FNP4CDHovztlPlq.MPZ4lB1K9bMG0YmpQmJok2ODfWtcw0i', 'maria.lopez@email.com', '611234567', 'maria.jpg', 1),
(3, '34567890C', 'Carlos Gómez', '$2b$10$YcvhNSU3.KMwz6Hq0vo56uZbIkYvJKFziTn5vR4TfRMhtLPpl7r2i', 'carlos.gomez@email.com', '622345678', 'carlos.jpg', 0),
(4, '45678901D', 'Laura Fernández', '$2b$10$f1TOb4JVot7n.bvGKtOReOwhXTh48KVRHwJkjUuV5LLX1JSdeVkWO', 'laura.fernandez@email.com', '633456789', 'laura.jpg', 1),
(5, '56789012E', 'Javier Ruiz', '$2b$10$5qL9ZK/7.AfFPVZEqeGL1uNvGnUwTFLRikXpIbF1NT5EQsJjV9RUy', 'javier.ruiz@email.com', '644567890', 'javier.jpg', 1),
(6, '67890123F', 'Sandra Morales', '$2b$10$3pfgMc7DDm3m/.KHnZIh7.vtC6CzJ5KBr1M/vZWE7nM6CqD7jlEsm', 'sandra.morales@email.com', '655678901', 'sandra.jpg', 0),
(7, '78901234G', 'Pedro Ramírez', '$2b$10$Zu2Yr/diP6P3bxxSOs3pdO0mJYjl1auDHHGZoW0MQgO1a74sMkgEy', 'pedro.ramirez@email.com', '666789012', 'pedro.jpg', 1),
(8, '89012345H', 'Elena Torres', '$2b$10$VVZayO6WXFL0Z1pOm0iwW.GQ3jOd1AqIC15/JHvcP1QzPKMecWe2S', 'elena.torres@email.com', '677890123', 'elena.jpg', 1),
(9, '90123456I', 'Roberto Díaz', '$2b$10$AItzyjJ54u06bGG7e3ws1u4zvEwPr4O1JjXLm2TgXJrpehZ8LXvhO', 'roberto.diaz@email.com', '688901234', 'roberto.jpg', 0),
(10, '01234567J', 'Isabel Martín', '$2b$10$2XhE3EToGg3Vk9aX/n9Ra.T8UDu5m7.NVhWOl7ZgyKZbrdbmS6GSi', 'isabel.martin@email.com', '699012345', 'isabel.jpg', 1);


INSERT INTO Book (isbn, name, description, image) VALUES
('978-0451524935', '1984', 'Clásico distópico sobre vigilancia gubernamental', 'https://example.com/images/1984.jpg'),
('978-0544003415', 'El Señor de los Anillos', 'Épica aventura en la Tierra Media', NULL),
('978-0061120084', 'Cien años de soledad', 'Realismo mágico en Macondo', 'https://example.com/images/cien-anos.jpg'),
('978-0140430721', 'Orgullo y prejuicio', 'Drama romántico en la Inglaterra del siglo XIX', NULL),
('978-0307474278', 'El código Da Vinci', 'Thriller de misterio histórico', 'https://example.com/images/da-vinci.jpg');
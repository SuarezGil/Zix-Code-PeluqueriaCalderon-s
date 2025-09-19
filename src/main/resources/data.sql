INSERT IGNORE INTO usuarios (username, password, rol, fecha_creacion, activo) VALUES
('admin', 'admin123', 'ADMIN', '2023-01-01 10:00:00', true),
('carlos_estilista', 'empleado123', 'EMPLEADO', '2023-01-02 09:00:00', true),
('laura_manicurista', 'empleado456', 'EMPLEADO', '2023-01-03 11:00:00', true),
('mario_barbero', 'empleado789', 'EMPLEADO', '2023-01-04 08:30:00', true);


INSERT IGNORE INTO clientes (nombre, telefono, correo, fecha_registro) VALUES
('Iosef Suárez', '53170347', 'ioss@gmail.com','2025-08-25'),
('Jeferson Yaxon', '12345678', 'ioss@gmail.com','2025-08-25'),
('Zimri Lopez', '87654321', 'ioss@gmail.com','2025-08-25'),
('Alvaro Calderon', '34568910', 'ioss@gmail.com','2025-08-25');

INSERT IGNORE INTO servicios (nombre, precio, duracion) VALUES
('Corte de pelo', 55.00 ,2),
       ('Corte de pelo y de barba', 125.00 ,4);
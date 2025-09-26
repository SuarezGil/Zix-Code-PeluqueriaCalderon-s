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

INSERT IGNORE INTO citas (codigo_cliente, codigo_servicio, fecha_hora) VALUES
(1, 1, '2025-09-18 14:30:00'),
(2, 2, '2025-09-19 10:00:00');

INSERT IGNORE INTO empleados (nombre, email, telefono, puesto, fecha_contratacion, activo) VALUES
('Carlos Mendoza', 'carlos.mendoza@peluqueriacalderon.com', '5551-2345', 'Estilista', '2023-01-02', true),
('Laura Hernandez', 'laura.hernandez@peluqueriacalderon.com', '5551-2346', 'Manicurista', '2023-01-03', true),
('Mario Rodriguez', 'mario.rodriguez@peluqueriacalderon.com', '5551-2347', 'Barbero', '2023-01-04', true),
('Ana Garcia', 'ana.garcia@peluqueriacalderon.com', '5551-2348', 'Colorista', '2023-01-05', true),
('Jorge Lopez', 'jorge.lopez@peluqueriacalderon.com', '5551-2349', 'Estilista', '2023-01-06', false);

INSERT IGNORE INTO facturas (fecha, total, cliente_id, empleado_id) VALUES
('2025-09-18', 55.00, 1, 1),
('2025-09-19', 125.00, 2, 3);

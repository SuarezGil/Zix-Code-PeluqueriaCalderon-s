INSERT IGNORE INTO usuarios (username, password, rol, fecha_creacion, activo) VALUES
('admin1', 'admin123', 'ADMIN', '2023-01-01 10:00:00', true),
('callos_adidas', 'mykekab123', 'EMPLEADO', '2023-01-02 09:00:00', true),
('listas_partenaria', 'mykekab456', 'EMPLEADO', '2023-01-03 11:00:00', true),
('listas_barbero', 'mykekab789', 'EMPLEADO', '2023-01-04 08:30:00', true);


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


INSERT IGNORE INTO inventario (nombre_producto, cantidad, precio_unitario, descripcion, categoria, minimo_stock) VALUES
('Shampoo Profesional', 50, 15.99, 'Shampoo para cabello teñido', 'Cuidado Capilar', 10),
('Acondicionador Reparador', 35, 18.50, 'Acondicionador intensivo', 'Cuidado Capilar', 8),
('Tinte Rubio Oscuro', 25, 25.75, 'Tinte permanente rubio oscuro', 'Coloración', 5),
('Tinte Negro', 30, 22.99, 'Tinte permanente negro azulado', 'Coloración', 6),
('Gel Fijador', 40, 12.50, 'Gel de alta fijación', 'Estilización', 12),
('Laca Professional', 28, 20.00, 'Laca de fijación extrema', 'Estilización', 7),
('Crema para Peinar', 15, 16.75, 'Crema sin enjuague', 'Estilización', 5),
('Mascarilla Capilar', 20, 30.00, 'Mascarilla nutritiva', 'Tratamiento', 4),
('Aceite Argan', 18, 35.50, 'Aceite de argán puro', 'Tratamiento', 3),
('Cepillo Profesional', 12, 45.00, 'Cepillo cerámico profesional', 'Herramientas', 5),
('Tijeras de Corte', 8, 120.00, 'Tijeras profesionales de corte', 'Herramientas', 2),
('Plancha Cerámica', 6, 85.00, 'Plancha de cerámica profesional', 'Herramientas', 2),
('Espuma para Barba Premium', 45, 14.99, 'Espuma de afeitar de alta calidad', 'Barba', 15),
('Aceite para Barba', 30, 28.00, 'Aceite nutritivo para barba y piel', 'Barba', 8),
('Bálsamo para Barba', 25, 23.50, 'Bálsamo hidratante para barba', 'Barba', 6),
('Loción After Shave', 38, 17.25, 'Loción refrescante post-afeitado', 'Barba', 10),
('Navajas Desechables', 200, 2.50, 'Navajas profesionales desechables', 'Barbería', 50),
('Cuchillas de Repuesto', 150, 3.25, 'Cuchillas para navajas de barbería', 'Barbería', 30);
/* Creacion de base de datos*/

CREATE DATABASE nja CHARACTER SET UTF8MB4 COLLATE UTF8MB4_GENERAL_CI;

/* Usar base de datos*/

USE nja;

/* Tabla Usuarios*/

CREATE TABLE `usuarios` (
    `us_id` INT(11) NOT NULL,
    `us_usuario` VARCHAR(50) NOT NULL,
    `us_password` VARCHAR(50) NOT NULL,
    `us_correo` VARCHAR(50) NOT NULL,
    `us_telefono` VARCHAR(15) NOT NULL,
    `us_rol` INT(11) NOT NULL,
    `us_activo` VARCHAR(1) NOT NULL DEFAULT 'S'
);

ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`us_id`);
  
  
ALTER TABLE `usuarios`
  MODIFY `us_id` INT(11) NOT NULL AUTO_INCREMENT;

/* Creacion de usuarios por default*/

/* Administradores */
INSERT INTO usuarios (us_usuario, us_password, us_correo, us_telefono, us_rol) VALUES 
	('Matthew','12345', 'Matthew@gmail.com', '3132254784', 1), ('Fernanda','54321', 'Fernanda@gmail.com', '3182254785', 1);

/* Vendedores */
INSERT INTO usuarios (us_usuario, us_password, us_correo, us_telefono, us_rol) VALUES 
	('Jorge','12345', 'jorge@gmail.com', '3152254784', 2), ('Raul','54321', 'raul@gmail.com', '3202254785', 2);
/*Usuarios normales*/
INSERT INTO usuarios (us_usuario, us_password, us_correo, us_telefono, us_rol) VALUES 
	('Ricardo','12345', 'ricardo@gmail.com', '3212254784', 3), ('Saul','54321', 'Saul@gmail.com', '3002254785', 3);
    
    
/* Tabla roles*/

CREATE TABLE `roles` (
    `rol_id` INT(11) NOT NULL,
    `rol_nombre` VARCHAR(50) NOT NULL
);

ALTER TABLE `roles`
  ADD PRIMARY KEY (`rol_id`);

ALTER TABLE `roles`
  MODIFY `rol_id` INT(11) NOT NULL AUTO_INCREMENT;

/* Creacion de roles por default*/

INSERT INTO roles (rol_nombre) VALUES 
	('Administrador'), ('Vendedor'), ('Usuario Normal');


/* Tabla Productos*/

CREATE TABLE `productos` (
    `po_id` INT(11) NOT NULL,
    `po_nombre` VARCHAR(200) NOT NULL,
    `po_marca` VARCHAR(100) NOT NULL DEFAULT 'NJA',
    `po_precio` DOUBLE NOT NULL DEFAULT '10',
    `po_categoria` VARCHAR(80) NOT NULL DEFAULT 'Camisa',
    `po_cantidad` INT(1) NOT NULL DEFAULT '1',
    `po_imagen` VARCHAR(200) NOT NULL DEFAULT 'default.jpg',
    `po_activo` VARCHAR(1) NOT NULL DEFAULT 'S'
);

ALTER TABLE `productos`
  ADD PRIMARY KEY (`po_id`);
  
  
ALTER TABLE `productos`
  MODIFY `po_id` INT(11) NOT NULL AUTO_INCREMENT;
  
/* Creacion de productos por default*/
  
INSERT INTO `productos` (`po_id`, `po_nombre`, `po_marca`, `po_precio`, `po_categoria`, `po_cantidad`, `po_imagen`, `po_activo`) VALUES
	(1, 'Adidos', 'NJA', 34.4, 'Zapatos', 1, 'default.jpg', 'S'),
	(2, 'Adidas 2', 'NJA', 34.4, 'Zapatos', 1, 'default.jpg', 'S');
  
  
/* Eliminar base de datos*/
  
DROP DATABASE nja;


/* Consultas */

SELECT us_id, us_usuario, us_correo, us_telefono, rol_nombre, us_activo
FROM usuarios
INNER JOIN roles
ON us_rol = rol_id
WHERE us_rol = 1;
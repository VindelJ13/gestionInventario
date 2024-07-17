CREATE DATABASE inventario;

CREATE TABLE Productos(
	id_producto int not null AUTO_INCREMENT,
    nombre varchar(30),
    descripcion varchar(100),
    precio float,
    costo_producto float,
    existencia int,
	primary key (id_producto)
);
CREATE TABLE Ventas (
    id_venta int not null AUTO_INCREMENT,
    id_producto int,
    cantidad int,
    fecha_venta date,
    primary key (id_venta),
    foreign key (id_producto) references Productos(id_producto)
);

DELIMITER //
CREATE TRIGGER before_insert_venta
BEFORE INSERT ON Ventas
FOR EACH ROW
BEGIN
    DECLARE existencia_actual INT;

    -- Obtiene la existencia actual del producto
    SELECT existencia INTO existencia_actual
    FROM Productos
    WHERE id_producto = NEW.id_producto;

    -- Verifica la existencia antes de insertar
    IF NEW.cantidad > existencia_actual OR existencia_actual = 0 THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'No se puede vender más de la existencia actual del producto';
    END IF;
END;
//
DELIMITER ;

-- Crear un trigger que se activará después de insertar en la tabla Ventas
DELIMITER //
CREATE TRIGGER after_insert_venta
AFTER INSERT ON Ventas FOR EACH ROW
BEGIN
    -- Actualizar la existencia del producto en la tabla Productos
    UPDATE Productos
    SET existencia = existencia - NEW.cantidad
    WHERE id_producto = NEW.id_producto;
END;
//
DELIMITER ;

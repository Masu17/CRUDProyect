SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
CREATE DATABASE IF NOT EXISTS Mercadillo
;

USE Mercadillo;

DROP TABLE IF EXISTS Cliente
;
DROP TABLE IF EXISTS Producto
;
DROP TABLE IF EXISTS Encargo
;
DROP TABLE IF EXISTS Pedido
;

CREATE TABLE Cliente (
                         numeroCliente INTEGER NOT NULL,
                         nombre varchar (30) NOT NULL,
                         direccion varchar (40) NOT NULL,
                         ciudad VARCHAR(30) NOT NULL,
                         provincia char (2) NOT NULL,
                         codpostal varchar (10) NOT NULL,
                         PRIMARY KEY (numeroCliente)
)
;

CREATE TABLE Producto (
                          codigoProducto char (7) NOT NULL,
                          descripcion varchar (40) NOT NULL,
                          precio DECIMAL(10, 2) NOT NULL,
                          PRIMARY KEY (codigoProducto)
)
;

CREATE TABLE Encargo (
                         numeroEncargo INTEGER NOT NULL,
                         numeroCliente INTEGER NOT NULL,
                         pago DECIMAL(10, 2) NOT NULL,
                         INDEX (numeroEncargo),
                         PRIMARY KEY (numeroEncargo)
)
;

CREATE TABLE Pedido (
                        numeroPedido  INTEGER NOT NULL,
                        codigoProducto CHAR(7) NOT NULL,
                        cantidad INTEGER NOT NULL
)
;

INSERT INTO Cliente VALUES (3175, 'Consultores La Burundanga SL.', '113 Calle de los Mangantes', 'Mordor', 'MA', '98765')
;
INSERT INTO Cliente VALUES (3176, 'Electronica Unlimitada & Co.', '1175 GranCanary', 'PioPio', 'LA', '45066')
;
INSERT INTO Cliente VALUES (3177, 'Sams Small Appliances Co.', '100 Main Street', 'Springfield', 'SU', '98555')
;
INSERT INTO Cliente VALUES (3178, 'Consumibles El Chinijo SL.', '1175 La  Panzada', 'Chicharroland', 'ER', '43366')
;
INSERT INTO Cliente VALUES (3179, 'eComerce Las Nuñas SL.', '1175 El Tanque', 'LanParty', 'TE', '42516')
;
INSERT INTO Producto VALUES ('116-064', 'Tostadora a pilas', 24.95);
INSERT INTO Producto VALUES ('257-535', 'Secador de pelo vintage', 29.95);
INSERT INTO Producto VALUES ('622-119', 'Aspiradora sin cables y a lo loco', 19.95);
INSERT INTO Producto VALUES ('166-054', 'Espada láser', 55.95);
INSERT INTO Producto VALUES ('299-515', 'Bola de cristal de murano', 66.95);

INSERT INTO Encargo VALUES (11731, 3175, 0);
INSERT INTO Encargo VALUES (11732, 3176, 249.50);
INSERT INTO Encargo VALUES (11733, 3179, 66.77);
INSERT INTO Encargo VALUES (11734, 3178, 322.19);
INSERT INTO Encargo VALUES (11735, 3177, 299);

INSERT INTO Pedido VALUES (11731, '116-064', 3);
INSERT INTO Pedido VALUES (11731, '257-535', 1);
INSERT INTO Pedido VALUES (11731, '622-119', 2);
INSERT INTO Pedido VALUES (11732, '116-064', 10);
INSERT INTO Pedido VALUES (11733, '299-515', 2);
INSERT INTO Pedido VALUES (11733, '166-054', 1);

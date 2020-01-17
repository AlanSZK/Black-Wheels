CREATE DATABASE JTN
SET DATEFORMAT dmy
GO
USE JTN

CREATE TABLE Cliente
(
	Rut char(10) NOT NULL PRIMARY KEY,
	Nombre char(20),
	Direccion char(20),
	Numero_de_contacto char(12) --+569XXXXXXXX
	
);

CREATE TABLE Modelo
(
	IdModelo int NOT NULL PRIMARY KEY,
	Modelo char(10)
);

CREATE TABLE Marca
(
	IdMarca int NOT NULL PRIMARY KEY,
	Marca char(10)
);

CREATE TABLE Color
(
	IdColor int NOT NULL PRIMARY KEY,
	Color char(15)
);

CREATE TABLE Vehiculo
(
	Patente char(8) NOT NULL PRIMARY KEY,
	RutCliente char(10) FOREIGN KEY REFERENCES Cliente(Rut),
	IdModelo int FOREIGN KEY REFERENCES Modelo(IdModelo),
	IdMarca int FOREIGN KEY REFERENCES Marca(IdMarca),
	IdColor int FOREIGN KEY REFERENCES Color(IdColor), 
	Ano int,
	Numero_de_chasis char(8), --Numeros de chasis incluyen 8 digitos
	Cilindrada char(5),
	Tipo_de_combustible char(10),
);

CREATE TABLE Mecanico
(
	Rut char(10) NOT NULL PRIMARY KEY,
	Nombre char(20),
	Direccion char(20),
	Numero_de_telefono char(12)
	
);

CREATE TABLE Tienda
(
	CodTienda int NOT NULL PRIMARY KEY,
	Nombre char(10),
	Numero_de_telefono char(12),
	Direccion char(20)
	
);

CREATE TABLE Repuesto
(
	Codigo_de_repuesto char(15) NOT NULL PRIMARY KEY,
	Tipo char(10),
	Marca char(10),
	Modelo char(10)
);

CREATE TABLE Proporcionan
(
	CodProporcionan int NOT NULL PRIMARY KEY,	
	CodTienda int FOREIGN KEY REFERENCES Tienda(CodTienda),
	Fecha_de_compra date
);

CREATE TABLE DetalleProporcionan
(
	CodDetalle int NOT NULL PRIMARY KEY,
	CodProporcionan int FOREIGN KEY REFERENCES Proporcionan(CodProporcionan),
	Codigo_de_repuesto char(15) FOREIGN KEY REFERENCES Repuesto(Codigo_de_repuesto),
	Cantidad int,
	Costo_unidad int
);

CREATE TABLE Utiliza
(
	CodUtiliza int NOT NULL PRIMARY KEY,
	RutMecanico char(10) FOREIGN KEY REFERENCES Mecanico(Rut),
	Patente char(8) FOREIGN KEY REFERENCES Vehiculo(Patente),
	Fecha_de_ingreso date,
	Fecha_de_salida date,
	Tipo_de_fallo char(30)
);

CREATE TABLE DetalleUtiliza
(
	CodDetalle int NOT NULL PRIMARY KEY,
	CodUtiliza int FOREIGN KEY REFERENCES Utiliza(CodUtiliza),
	Codigo_de_repuesto char(15) FOREIGN KEY REFERENCES Repuesto(Codigo_de_repuesto),
	Cantidad int,
	Cobro int,
	Descripcion char(100)
);
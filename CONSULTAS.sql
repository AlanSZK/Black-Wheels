--	¿Cuáles son los datos del cliente? (Se muestran sus datos ingresando su Rut o la patente de su vehículo) TERMINADO
SELECT  
	c.Rut AS Rut,c.Nombre AS Nombre, c.Direccion AS Dirección, c.Numero_de_contacto AS 'Numero contacto',
	v.patente
FROM Cliente c INNER JOIN Vehiculo v ON c.Rut = v.RutCliente
WHERE c.Rut = (RUT)

SELECT  
	c.Rut AS Rut, c.Nombre AS Nombre,c.Direccion AS Dirección,c.Numero_de_contacto AS 'Numero contacto',
	v.patente
FROM Vehiculo v INNER JOIN Cliente c ON v.RutCliente = C.Rut 
WHERE v.Patente = (PATENTE)

--	¿Cuál es el tipo de fallo más común? PARECE QUE ESTA BIEN
	SELECT MAX(Tipo_de_fallo) AS 'Tipo de fallo'
	FROM Utiliza

--	¿Qué fallo es mas común para cierta marca?
SELECT 
	m.Marca,
	u.Tipo_de_fallo AS 'Tipo de fallo'
FROM ((Marca m INNER JOIN Vehiculo v ON m.IdMarca = v.IdMarca) INNER JOIN Utiliza u ON V.Patente = u.Patente)
WHERE m.MARCA = (MARCA)	

--	¿Cuál es la tienda que tiene cierta marca de repuesto?
SELECT 
	t.Nombre AS Tienda, t.Direccion,
	r.Marca
FROM Tienda t,Proporcionan p,DetalleProporcionan dp,Repuesto r
WHERE t.CodTienda=p.CodTienda AND 
	  p.CodProporcionan=dp.CodProporcionan AND
	  dp.Codigo_de_repuesto = r.Codigo_de_repuesto AND
	  r.Marca = (MARCA)

--	¿Qué mecánico realizó la reparación o aún la esta realizando? 
SELECT 
	m.Nombre AS 'Nombre mecanico',
	u.CodUtiliza,u.Patente,u.Fecha_de_ingreso,u.Fecha_de_salida
FROM Mecanico m INNER JOIN Utiliza u ON m.Rut=u.RutMecanico
WHERE u.Patente = (PATENTE)

--	¿Qué reparación se le ha hecho a cierto vehículo? 
SELECT 
	u.Patente,
	u.Tipo_de_fallo AS 'Tipo de fallo',
	du.Descripcion AS 'Reparacion'
    	
FROM (Utiliza u INNER JOIN DetalleUtiliza du ON u.CodUtiliza = du.CodUtiliza)
WHERE Patente = (PATENTE)

--	¿Qué repuestos se compraron para cierto vehículo?
SELECT 
	u.Patente,
	r.Codigo_de_repuesto AS 'CODIGO DE REPUESTO',r.Tipo,r.Marca,r.Modelo
FROM ((Utiliza u INNER JOIN DetalleUtiliza du ON u.CodUtiliza = du.CodUtiliza) INNER JOIN Repuesto r ON r.Codigo_de_repuesto=du.Codigo_de_repuesto)
WHERE u.Patente = (PATENTE)

--	¿Cuántos vehículos posee el cliente y cuáles son? 	
SELECT 
	c.Rut AS RUT, 
	v.Patente
FROM (Cliente c INNER JOIN Vehiculo v ON c.Rut = v.RutCliente)
WHERE c.Rut=(RUT)
	
--	¿Qué vehículo sigue en el taller,de quién es y cuántos días lleva en él?
SELECT 
	c.Nombre, c.Rut,
	v.Patente,
	DATEDIFF(day,GETDATE(),u.Fecha_de_ingreso) AS 'Días en taller'
FROM (Vehiculo v INNER JOIN Utiliza u ON v.Patente = u.Patente) INNER JOIN Cliente c ON c.Rut = v.RutCliente
WHERE u.Fecha_de_salida IS NULL

--	¿Cuántos repuestos se han comprado en el mes y año seleccionado y cual ha sido el gasto total?
SELECT 
	COUNT(p.CodProporcionan),
	SUM(dp.Costo_unidad*dp.Cantidad)
FROM Proporcionan p INNER JOIN DetalleProporcionan dp ON p.CodProporcionan=dp.CodProporcionan
WHERE MONTH(p.Fecha_de_compra) =(MES) AND YEAR(p.Fecha_de_compra) = (AÑO)


--	¿Cuáles han sido los ingresos del mes?  
SELECT 
	Cobro AS Ganancia,Fecha_de_salida AS  'Dia correspondiente'
FROM DetalleUtiliza INNER JOIN Utiliza on DetalleUtiliza.CodUtiliza = Utiliza.CodUtiliza 
WHERE MONTH(Utiliza.Fecha_de_salida)=MONTH(GETDATE()) AND YEAR(Utiliza.Fecha_de_salida)= YEAR(GETDATE())

--	¿Cuáles son las tiendas para cierto repuesto ?
SELECT 
	t.Nombre, t.Numero_de_telefono
FROM Tienda t,Proporcionan p, DetalleProporcionan dp, Repuesto r
WHERE t.CodTienda=p.CodProporcionan AND p.CodProporcionan=dp.CodProporcionan AND dp.Codigo_de_repuesto=r.Codigo_de_repuesto
AND r.Tipo = (REPUESTO)


--  	¿Cuáles repuestos se han comprado en el mes?
SELECT
	r.Codigo_de_repuesto,r.Tipo,r.Marca,r.Modelo,
	p.Fecha_de_compra
	
FROM (Repuesto r INNER JOIN DetalleProporcionan dp ON r.Codigo_de_repuesto=dp.Codigo_de_repuesto) INNER JOIN Proporcionan p ON dp.CodProporcionan=p.CodProporcionan
WHERE MONTH(p.Fecha_de_compra) = (MES)

--	¿Cuánto costó cierta reparación? 
SELECT 
	u.Tipo_de_fallo AS FALLO,u.CodUtiliza,
	du.Cobro 
FROM Utiliza u INNER JOIN DetalleUtiliza du ON u.CodUtiliza = du.CodUtiliza
WHERE Tipo_de_fallo = (FALLO)


--	¿Cuántos trabajos se han realizado en el mes y cuales son?
SELECT 
	u.Patente,u.Tipo_de_fallo
FROM Utiliza u 
WHERE MONTH(u.Fecha_de_ingreso)=(MES) AND MONTH(u.Fecha_de_salida)=(MES)





 
	
	
	

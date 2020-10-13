INSERT INTO regiones (id,nombre) values ('1','Aguascalientes: Aguascalientes')
INSERT INTO regiones (id,nombre) values ('2','Baja California: Mexicali')
INSERT INTO regiones (id,nombre) values ('3','Baja California Sur: La Paz')
INSERT INTO regiones (id,nombre) values ('4','Campeche: San Francisco de Campeche')
INSERT INTO regiones (id,nombre) values ('5','Chihuahua: Chihuahua')
INSERT INTO regiones (id,nombre) values ('6','Chiapas: Tuxtla Gutiérrez')
INSERT INTO regiones (id,nombre) values ('7','Coahuila de Zaragoza: Saltillo')
INSERT INTO regiones (id,nombre) values ('8','Colima: Colima')
INSERT INTO regiones (id,nombre) values ('9','Durango: CD Victoria ')
INSERT INTO regiones (id,nombre) values ('10','Guanajuato: Guanajuato')
INSERT INTO regiones (id,nombre) values ('11','Guerrero: Chilpancingo de los Bravo')
INSERT INTO regiones (id,nombre) values ('12','Hidalgo: Pachuca de Soto')
INSERT INTO regiones (id,nombre) values ('13','Jalisco: Guadalajara')
INSERT INTO regiones (id,nombre) values ('14','México: Toluca de Lerdo')
INSERT INTO regiones (id,nombre) values ('15','Michoacán de Ocampo: Morelia')
INSERT INTO regiones (id,nombre) values ('16','Morelos: Cuernavaca')
INSERT INTO regiones (id,nombre) values ('17','Nayarit: Tepic')
INSERT INTO regiones (id,nombre) values ('18','Nuevo León: Monterrey')
INSERT INTO regiones (id,nombre) values ('19','Oaxaca: Oaxaca de Juárez')
INSERT INTO regiones (id,nombre) values ('20','Puebla: Puebla de Zaragoza')
INSERT INTO regiones (id,nombre) values ('21','Querétaro: Santiago de Querétaro')
INSERT INTO regiones (id,nombre) values ('22','Quintana Roo: Chetumal')
INSERT INTO regiones (id,nombre) values ('23','San Luis Potosí: San Luis Potosí')
INSERT INTO regiones (id,nombre) values ('24','Sinaloa: Culiacán Rosales')
INSERT INTO regiones (id,nombre) values ('25','Sonora: Hermosillo')
INSERT INTO regiones (id,nombre) values ('26','Tabasco: Villahermosa')
INSERT INTO regiones (id,nombre) values ('27','Tamaulipas: Ciudad Victoria')
INSERT INTO regiones (id,nombre) values ('28','Tlaxcala: Tlaxcala de Xicohténcatl')
INSERT INTO regiones (id,nombre) values ('29','Veracruz: Xalapa-Enríquez')
INSERT INTO regiones (id,nombre) values ('30','Yucatán: Mérida')
INSERT INTO regiones (id,nombre) values ('31','Zacatecas: Zacatecas')
INSERT INTO regiones (id,nombre) values ('32','CD MX')


 
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (3,'Aldair','Castillo','2020/5/12','dataem2012@homail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (3,'Juan','Bonilla','2018/7/12','bonilla2015@homail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (5,'Camacho','Izquierdo','2019/4/12','izquierdo@homail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (3,'Rusell','Wilson','2011/8/12','wil20@homail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (7,'Jimy','Valverde','2010/7/12','valverde25@homail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (4,'Enrique','Buchan','2020/5/12','Buchan@homail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (4,'Marcos','Cadena','2020/7/12','Markos@gmail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (4,'Aldo','Cadena','2020/7/12','Ald@hotmail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (3,'David ','Aguilar','2020/7/12','Aguilar@gmail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (3,'Edgar','Ruciles','2014/8/12','ruciles@gmail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (2,'Omar','Cadena','2020/7/12','Om@gmail.com');
INSERT INTO clientes (region_id, nombre,apellido,create_at,email) VALUES (3,'TOM ','BRADY','	2020/8/12','brady@gmail.com');

INSERT INTO `usuarios` (username, password, enabled,nombre,apellido,email) VALUES('aldair','$2a$10$Ac7HyMmHHQkxPuzKv86z4.w3li7yXuwi2E7KYk7y.6eRuSPC77gnC',1, 'Aldair','Castillo','dataem2011@hotmail.com');
INSERT INTO `usuarios` (username, password, enabled,nombre,apellido,email) VALUES('admin','$2a$10$moWxtsINC.d0eiFVLwxtY.TZIZmHRMwqwJvaG0kdeNdznFSmm8Gc6',1 , 'Joe','bored','joesgreen@gmail.com');
INSERT INTO `usuarios` (username, password, enabled,nombre,apellido,email) VALUES('ngg','$2a$10$moWxtsINC.d0eiFVLwxtY.TZIZmHRMwqwJvaG0kdeNdznFSmm8Gc6',1 , 'jesse','pinkman','jesse@gmail.com');



INSERT INTO  `roles` (nombre) VALUES ('ROLE_USER');
INSERT INTO  `roles` (nombre) VALUES ('ROLE_ADMIN');

INSERT INTO `usuarios_roles`(usuario_id, role_id) VALUES(1,1);
INSERT INTO `usuarios_roles`(usuario_id, role_id) VALUES(2,2);
INSERT INTO `usuarios_roles`(usuario_id, role_id) VALUES(2,1);


INSERT INTO productos (nombre,precio,create_at) VALUES('Celular Motorola',3500,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Celular Smasung',5500,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Laptop Dell inspiron 1500',17000,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Apple iphone 10x',17500,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Bocina boss',4200,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('tennis nike',1500,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('teniis acciss',2200,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('teniis rebook',2200,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('mouse minusu',2200,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('lamapara ',2200,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Laptop Hp',2200,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('Celular OPPO',2200,NOW());
INSERT INTO productos (nombre,precio,create_at) VALUES('ps5',2200,NOW());



INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES ('Equipos personales ',null,1,NOW());

INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (1,1,1)
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (2,1,4)
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (1,1,5)
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (1,1,7)

INSERT INTO facturas (descripcion,observacion,cliente_id,create_at) VALUES ('Factura de computadora','Alguna no ta importante',1,NOW());
INSERT INTO facturas_items(cantidad, factura_id, producto_id) VALUES (3,2,6) 











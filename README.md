1. Crear base de datos en MariaDB con el nombre de "bdflujoefectivo" y con cotejamiento utf8_spanish_ci

2. Importar el archivo bdflujoefectivo.sql dentro de la base de datos que se creo con anterioridad

3. (Opcional) puede ejecutar el siguiente script si es que no ha realizado el paso 2

create table cuentas(
	id int PRIMARY KEY AUTO_INCREMENT,
    numero_cuenta varchar(50),
    saldo decimal(18,2),
    estado varchar(20),
    moneda varchar(3)
);

create table transacciones(
	id int PRIMARY KEY AUTO_INCREMENT,
    operacion varchar(20),
    monto decimal(18,2),
	moneda varchar(3),
    fecha datetime,
    idcuenta int
);

ALTER TABLE transacciones
ADD CONSTRAINT fk_transacciones_cuentas
FOREIGN key (idcuenta) REFERENCES cuentas(id);
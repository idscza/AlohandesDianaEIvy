//----------------------------------
// remake operadores
//----------------------------------

create table operadores(
id varchar(60),
capacidad integer Default 0,
nombre varchar(60),
telefono varchar(20),
direccion varchar(60),
estrellas number(1),
rut varchar(60),
habDisponibles integer DEFAULT 0,
habOcupadas integer DEFAULT 0,
horacierre number(2),
horaapertura number(2),
cedula varchar(20),
edad Number(3),
tipo varchar(10)
);

alter table operadores
modify capacidad not null;

alter table operadores
modify tipo not null;

alter table operadores
modify nombre not null;

alter table operadores
modify telefono not null;

alter table operadores
add CONSTRAINT CHK_HotHostHabitaciones CHECK ( HabDisponibles >= 0 and habOcupadas >= 0);

alter table operadores
add CONSTRAINT CHK_HotelesEst CHECK (estrellas>=0 and estrellas<=5);

alter table operadores
add CONSTRAINT CHK_HorasHostal CHECK ( horacierre >= 0 and horacierre <= 23 and horaapertura >= 0 and horaapertura <= 23);

alter table operadores
add constraint CHK_tipoopvalido check (tipo in ('hotel','pernat','percom','hostal','vivuni'));

alter table operadores 
add CONSTRAINT PK_Operador PRIMARY KEY (id);


//----------------------------------
// remake Usuarios
//----------------------------------

create table usuarios(
id varchar(60),
contrasenia varchar(64),
login varchar (64),
cedula varchar(20),
edad Number(3),
nombre varchar(60),
telefono varchar(20),
operador varchar(60),
tipo varchar (20)
);

alter table usuarios
modify contrasenia not null;

alter table usuarios
modify login not null;

alter table usuarios
modify cedula not null;

alter table usuarios
modify edad not null;

alter table usuarios
modify nombre not null;

alter table usuarios
modify telefono not null;

alter table usuarios
add CONSTRAINT CHK_Cliente CHECK (edad>=18);

alter table usuarios
add constraint CHK_tipousvalido check (tipo in ('cliente','responsable'));

alter table usuarios
add constraint FK_Operador FOREIGN KEY (operador) references operadores(id);

alter table usuarios
add CONSTRAINT PK_Usuario PRIMARY KEY (id);


//----------------------------------
// remake alojamientos
//----------------------------------

create table alojamientos(
id varchar(60) NOT NULL PRIMARY KEY ,
capacidad number(2) NOT NULL,
tamanio NUMBER(3) NOT NULL ,
menaje NUMBER(1),
amoblado NUMBER(1),
numhabitaciones NUMBER(1),
direccion varchar(60) NOT NULL,
diasuso number(2),
categoria varchar(60),
compartida number(1),
numerohab varchar(60),
operador varchar(60) NOT NULL,
tipo varchar(60) NOT NULL
);

alter table alojamientos
add CONSTRAINT CHK_AlojamientoConSentido CHECK ( capacidad >= 0 and tamanio >= 0);

alter table alojamientos
add CONSTRAINT CHK_booleanmenaje CHECK ( menaje = 1 or menaje = 0);

alter table alojamientos
add CONSTRAINT CHK_booleanamoblado CHECK ( menaje = 1 or menaje = 0);

alter table alojamientos
add CONSTRAINT CHK_habitaciones CHECK ( numhabitaciones > 0);

alter table alojamientos
add CONSTRAINT CHK_vdadiasuso CHECK ( diasuso >= 0 and diasuso <= 30);

alter table alojamientos
add CONSTRAINT CHK_habcompartida CHECK ( compartida = 1 or compartida = 0);

alter table alojamientos
add constraint CHK_catehotel check (categoria in ('suite','semisuite','estandar'));

alter table alojamientos
add constraint CHK_tipoalvalido check (tipo in ('habhot','aparta','habuni','vivcom','habita'));

alter table alojamientos
add constraint FK_Operador2 FOREIGN KEY (operador) references operadores(id);


//----------------------------------
// ofertas
//----------------------------------

create table ofertas(
id varchar(60),
costo number(9,2),
fecharetiro date,
nombre varchar(60),
operador varchar(60),
alojamiento varchar(60),
deshabilitada NUMBER(1)
);

alter table ofertas
modify costo not null;

alter table ofertas
modify operador not null;

alter table ofertas
modify alojamiento not null;

alter table ofertas
modify deshabilitada not null;

alter table ofertas
add CONSTRAINT CHK_deshabilitacion CHECK ( deshabilitada = 1 or deshabilitada = 0);

alter table ofertas
add CONSTRAINT PK_ofertas PRIMARY KEY (id);

alter table ofertas
add FOREIGN KEY (operador) references operadores(id);

alter table ofertas
add FOREIGN KEY (alojamiento) references alojamientos(id) ;

//----------------------------------
// reservas
//----------------------------------

create table reservas(
id varchar(60),
cobro number(9,2) default 0,
fecharealizacion date,
fechainicio date,
fechafin date,
personas number(2),
operador varchar(60),
oferta varchar(60),
cliente varchar(60),
estado varchar(60),
idmaestro varchar(60)  
);

alter table reservas
modify cobro not null;

alter table reservas
modify fecharealizacion not null;

alter table reservas
modify fechainicio not null;

alter table reservas
modify fechafin not null;

alter table reservas
modify personas not null;

alter table reservas
modify operador not null;

alter table reservas
modify oferta not null;

alter table reservas
modify cliente not null;

alter table reservas
modify estado not null;

alter table reservas
add constraint CHK_estado check (estado in ('activa','cancelada','terminada'));

alter table reservas
add CONSTRAINT PK_reservas PRIMARY KEY (id);

alter table reservas
add FOREIGN KEY (operador) references operadores(id);

alter table reservas
add FOREIGN KEY (oferta) references ofertas(id);

alter table reservas
add FOREIGN KEY (cliente) references usuarios(id);

alter table reservas
add CONSTRAINT CHK_reservacoherente CHECK ( fechainicio < fechafin and fecharealizacion < fechainicio);

//----------------------------------
// servicios
//----------------------------------

create table servicios(
id varchar(60),
costo number (8,2),
descripcion varchar(60),
nombre varchar(60),
oferta varchar(60)
);

alter table servicios
modify nombre not null;

alter table servicios
modify oferta not null;

alter table servicios
add CONSTRAINT PK_servicios PRIMARY KEY (id);

alter table servicios
add FOREIGN KEY (oferta) references ofertas(id);

//----------------------------------
// super reservas
//----------------------------------

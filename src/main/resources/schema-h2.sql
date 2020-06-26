
create table chofer
(
    dni         varchar (100) primary key,
    Nombre      varchar(100)  not null,
    apellidos   varchar(100)  not null,
    email       varchar(100)  not null,
    fecIngreso  date not null
);


create table moto
(
    numero     identity primary key,
    placa      varchar(100)  not null,
    modelo     varchar(100)  not null,
    color      varchar(100)  not null,
    estadoOpe  varchar (100) not null,
    dni        varchar (100)  not null,
    constraint moto_chofer_dni_fk
        foreign key (dni) references chofer (dni)
);


create table directivo
(
    dni         varchar (100) primary key,
    Nombre       varchar (100)  not null,
    apellidos    varchar (100)  not null,
    fecasociado  date           not null,
    nrochoferes  Integer        not null
)




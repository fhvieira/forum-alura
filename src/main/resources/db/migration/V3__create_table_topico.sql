create table topico (
    id bigint not null generated by default as identity,
    mensagem varchar(255) not null,
    titulo varchar(255) not null ,
    status varchar(50) not null,
    autor_id bigint not null,
    curso_id bigint not null,
    data_criacao timestamp not null,
    primary key (id),
    foreign key (autor_id) references usuario(id),
    foreign key (curso_id) references curso(id)
);

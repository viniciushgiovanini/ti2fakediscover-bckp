create table Perguntas (
    id int not null,
    veracidade char(11) not null,
    descricao text not null,
    primary key (id)
);

create table Respostas (
    id int not null,
    id_pergunta int not null,
    descricao text not null,
    primary key (id), 
    foreign key (id_pergunta) references Perguntas(id)
    on update cascade on delete cascade
);


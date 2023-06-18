--TODO: Criar um script que crie 4 tipos de categoria [Sugestão, Crítica, Elogio, Comentário] e crie uma menos 1 registro com base numa categoria.

 create table categories(
     id int primary key auto_increment,
     description varchar(100) not null
 );

  insert into categories (description) values ('Sugestão');
  insert into categories (description) values ('Crítica');
  insert into categories (description) values ('Elogio');
  insert into categories (description) values ('Comentário');

   create table suggestions(
       id int primary key auto_increment,
       description varchar(500) not null,
       data date default now(),
       category_id int,
       foreign key (category_id) references categories(id)
   );

    insert into suggestions (description, category_id) values ('Criar validações', 1);
    insert into suggestions (description, category_id) values ('Você foi muito mal', 2);
    insert into suggestions (description, category_id) values ('Você foi muito bem', 3);
    insert into suggestions (description, category_id) values ('Vamos pra o próximo semestre', 4);
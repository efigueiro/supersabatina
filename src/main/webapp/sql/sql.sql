-- SuperSabatina

-- Ver locales no linux com o comando locale -a
-- Depois logar no postgres e criar banco
-- Os valores abaixo do with são opcionais, eu usei somente collate, ctype e template0
-- Fazendo isso os outros valores usados vem do template0 ou seja default.

CREATE DATABASE supersabatina WITH OWNER = postgres ENCODING = "UTF8" LC_COLLATE = 'pt_BR.UTF-8' LC_CTYPE = 'pt_BR.UTF-8' TABLESPACE = pg_default CONNECTION LIMIT -1 TEMPLATE = template0;

INSERT INTO users (username, email, password)
VALUES ('carlos', 'carlos.figueiro@gmail.com', 'teste123');

create table users
(
  user_id bigserial not null,
  user_name varchar(400) not null,
  email varchar(400) not null,
  password varchar(400) not null,
  avatar varchar(400),
  visibility varchar(10) not null,
  tutorial varchar(4),
  description text,
  unique(user_id, email, user_name),
  constraint user_pk primary key(user_id)
);

create table question_group 
(
  question_group_id bigserial not null,
  user_id bigserial not null,
  title text not null,
  description text not null,
  unique(question_group_id),
  constraint question_group_pk primary key(question_group_id),
  constraint user_fk foreign key(user_id) references users(user_id)
);

create table question 
(
  question_id bigserial not null,
  user_id bigserial not null,
  subject text not null,
  visibility varchar(10) not null,
  question text not null,
  answer text not null,
  unique(question_id),
  constraint question_pk primary key(question_id),
  constraint user_fk foreign key(user_id) references users(user_id)
);

========================================================================================

create table workday 
(
  workday_id bigserial not null,
  user_id int not null,
  day date not null, 
  start_time time,
  end_time time,
  start_interval time,
  end_interval time,
  comment text,
  unique(workday_id),
  constraint workday_pk primary key(workday_id),
  constraint user_fk foreign key(user_id) references users(user_id) on delete cascade
);

=============================================

create table test_plan 
(
  test_plan_id serial not null,
  name varchar(300) not null,
  description text,
  status varchar(100) not null,
  unique(test_plan_id, name),
  constraint test_plan_pk primary key(test_plan_id)
) ;

create table user_test_plan
(
  user_id integer not null,
  test_plan_id integer not null,
  constraint user_fk foreign key(user_id) references users(user_id) on delete cascade,
  constraint test_plan_fk foreign key(test_plan_id) references test_plan(test_plan_id) on delete cascade
);

create table test_case 
(
  test_case_id serial not null,
  title varchar(300) not null,
  description text,
  priority varchar(100) not null,
  post_condition text,
  pre_condition text,
  result varchar(150) not null,
  created_by varchar(300) not null,
  unique(test_case_id, title),
  constraint test_case_pk primary key(test_case_id)
);

create table test_plan_test_case
(
  test_plan_id integer not null,
  test_case_id integer not null,
  constraint test_plan_fk foreign key(test_plan_id) references test_plan(test_plan_id) on delete cascade,
  constraint test_case_fk foreign key(test_case_id) references test_case(test_case_id) on delete cascade
);

create table action_step 
(
  action_step_id serial not null,
  test_case_id int not null,
  feature_id int not null,
  description text not null,
  result varchar(150) not null,
  unique(action_step_id),
  constraint action_step_pk primary key(action_step_id),
  constraint test_case_fk foreign key(test_case_id) references test_case(test_case_id) on delete cascade
);

create table task 
(
  task_id serial not null,
  user_id int not null,
  title varchar(150) not null,
  description text not null,
  completed varchar(150) not null,
  unique(task_id),
  constraint task_pk primary key(task_id),
  constraint user_fk foreign key(user_id) references users(user_id) on delete cascade
);

create table category
(
  category_id serial not null,
  user_id int not null,
  name varchar(150) not null,
  description text not null,
  unique(category_id),
  constraint category_pk primary key(category_id),
  constraint user_fk foreign key(user_id) references users(user_id) on delete cascade
);

create table how_to
(
  how_to_id serial not null,
  category_id int not null,
  title varchar(150) not null,
  description text not null,
  unique(how_to_id),
  constraint how_to_pk primary key(how_to_id),
  constraint category_fk foreign key(category_id) references category(category_id) on delete cascade
);

insert into users(email, password)
values('everson.figueiro@gmail.com', 'scorpion2015');





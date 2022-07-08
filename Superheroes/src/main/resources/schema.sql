
create table superheroe (id int4 not null, nombre varchar(255) not null, vivo boolean not null, universo_id int4 not null, primary key (id));

create table universo (id int4 not null, nombre varchar(255) not null, primary key (id));

create table poder (id int4 not null, nombre varchar(255) not null, primary key (id));

create table superheroe_universo (s_u_heroe_id int4 not null, s_u_universo_id int4 not null, primary key (s_u_heroe_id, s_u_universo_id));

create table superheroe_poder (s_p_heroe_id int4 not null, s_p_poder_id int4 not null, primary key (s_p_heroe_id, s_p_poder_id));

alter table superheroe add constraint fki3qd9rkjxnylftla4c9912616 foreign key (universo_id) references universo;

alter table superheroe_universo add constraint fkmpd67mwhola6empsxnuh9cyia foreign key (s_u_heroe_id) references superheroe;

alter table superheroe_universo add constraint fkse06iapdxu9cjylc6uda01jtw foreign key (s_u_universo_id) references universo;

alter table superheroe_poder add constraint fkkbvlketj3wldy4w7twblsw85w foreign key (s_p_heroe_id) references superheroe;

alter table superheroe_poder add constraint fkcjk6wcn5bdya8u6bvkjdrt39o foreign key (s_p_poder_id) references poder;
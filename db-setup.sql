
drop table users cascade;
drop table planets cascade;
drop table moons cascade;

create table users(
	id serial primary key,
	username varchar(20) unique,
	password varchar(20)
);

create table planets(
	id serial primary key,
	name varchar(20),
	ownerId int references users(id) on delete cascade --ensures referential integrity (orphaned record)
	--or on delete set null
);

create table moons(
	id serial primary key,
	name varchar(20),
	myPlanetId int references planets(id) on delete cascade --prevents orphaned record
	--or on delete set null
);

insert into users (id, username, password) values (default, 'Username', 'Password');

-- add 3 planets to planets table 
insert into planets (id, name, ownerid) values (default, 'Mars', 1);
insert into planets (id, name, ownerid) values (default, 'Jupiter', 1);
insert into planets (id, name, ownerid) values (default, 'Saturn', 1);

-- add 3 moons to a planet
insert into moons values (default, 'Luna', 1);
insert into moons values (default, 'Luna2', 1);
insert into moons values (default, 'Cookie', 1);


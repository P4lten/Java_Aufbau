create database PlayerDB CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

use PlayerDB;

create table Players (
	name  varchar(150)  NOT NULL  PRIMARY KEY ,
	rank    int   NOT NULL,
	points  int   NOT NULL,
	time  long NOT NULL,
	date date  NOT NULL
	
);

create table Games (
	name  varchar(150) NOT NULL,
	points  int NOT NULL,
	time  long  NOT NULL,
	date  date  NOT NULL,
	constraint  fk_Players_Games
	foreign key(name) 
	references  Players(name)
);



insert into Players (name, rank, points, time, date)
values ('Peter', 1, 1000, 500 , '2021-07-10');

insert into Players (name, rank, points, time, date)
values ('Thomas', 2, 900, 400 , '2021-06-10');

insert into Players (name, rank, points, time, date)
values ('Tom', 5, 900, 300 , '2021-06-10');

insert into Players (name, rank, points, time, date)
values ('Tim', 3, 800, 300 , '2021-09-10');

insert into Games (name, points, time, date)
values ('Peter', 1000, 500 , '2021-07-10');

insert into Games (name, points, time, date)
values ('Thomas', 800, 500 , '2021-07-10');

delete from players where points = 0;

select * from Players;
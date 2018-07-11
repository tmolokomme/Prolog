create table AccountTypes (id int identity not null, active tinyint not null, types varchar(255) null, primary key (id));
create table ContactType (id int identity not null, active tinyint not null, types varchar(255) null, primary key (id));
create table PropertyType (id int identity not null, active tinyint not null, types varchar(255) null, primary key (id));
create table RequestReason (id int identity not null, active tinyint not null, prime tinyint not null, types varchar(255) null, primary key (id));

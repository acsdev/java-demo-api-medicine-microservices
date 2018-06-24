use account

create table if not exists account (
	id bigint not null auto_increment,
	nr_identity bigint not null unique,
	ds_usename varchar(10) not null unique,
	ds_password varchar(200) not null,
	ds_name varchar(200) not null,
	dt_birth date not null,
	ds_email varchar(200) not null,
	ds_address varchar(200) not null,
	vl_latitude decimal(10, 8),
	vl_longitude decimal(11, 8),
	primary key (id)
);
use drugstore

create table drugstore (
	id_store bigint not null auto_increment primary key,
	ds_name varchar(200) not null,
	ds_address varchar(200) not null,
	vl_latitude decimal(10, 8),
	vl_longitude decimal(11, 8)
);

create table drug (
  id_drug bigint not null auto_increment primary key,
  ds_name varchar(200) not null unique,
  ds_description varchar(500) not null
);

create table store_drugs (
  id_store bigint not null,
  id_drug bigint not null,
  vl_price decimal(11, 8),
  foreign key (id_store) REFERENCES drugstore (id_store),
  foreign key (id_drug) REFERENCES drug (id_drug)
);
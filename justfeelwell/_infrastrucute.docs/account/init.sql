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

-- USER FOR TESTE ORDER, PASS 123
-- INSERT INTO account.account (id, nr_identity, ds_usename, ds_password, ds_name, dt_birth, ds_email, ds_address, vl_latitude, vl_longitude)
-- VALUES (1, 1000000098, 'tst.order', '$2a$10$f5k20/hWBUz50Rl6XtF6cOCQ3D0XrEWt9yFaM94DxywpPnjf2ltjm', 'TST.Order02', '1986-05-14', 'tstuser@gmail.com', '424-338 Home St Winnipeg, MB R3G 1X4', 49.88709760, -97.16837690);
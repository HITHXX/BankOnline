# BankOnline
### DataBase Mysql   
#### user:  
    create table user ( user varchar(20) primary key, password varchar(256), balance varchar(20));
#### uservcode:
    create table uservcode( user varchar(20) primary key, vcode varchar (8));
#### transfer:
    create table transfer ( src varchar(20),  dest varchar(20), date varchar(20), amount varchar(20));

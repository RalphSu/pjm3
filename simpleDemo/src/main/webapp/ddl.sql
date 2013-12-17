-- login as root
create database pjm default character set UTF-8;


CREATE USER 'pjm'@'localhost' IDENTIFIED BY 'pjm@local!@#';

 grant all privileges on pjm.* to 'pjm'@'localhost';
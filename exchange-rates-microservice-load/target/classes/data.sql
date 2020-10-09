drop table USER;

create table USER (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  username VARCHAR(250) NOT NULL UNIQUE,
  password VARCHAR(250) NOT NULL,
  role VARCHAR(250) DEFAULT NULL
);

INSERT INTO USER (username, password, role) VALUES
  ('user', 'user123', 'USER'),
  ('admin', 'admin123', 'ADMIN');
  
 /* 
create table ROLE_AUTHORITY (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  role VARCHAR(250) NOT NULL UNIQUE,
  authority VARCHAR(250) NOT NULL,
  active CHAR(1)
);


INSERT INTO ROLE_AUTHORITY (role, authority, active) VALUES
  ('USER', 'READ', 1),
  ('ADMIN', 'READ', 1)
  ('ADMIN', 'WRITE', 1);
  */
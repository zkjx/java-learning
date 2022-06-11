DROP TABLE IF EXISTS score;
DROP TABLE IF EXISTS course;
DROP TABLE IF EXISTS student;

CREATE TABLE student(
  id int auto_increment,
  name varchar(32) NOT NULL,
  age int DEFAULT NULL,
  sex char(1) DEFAULT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE course(
  id int auto_increment,
  name varchar(32) NOT NULL,
  PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE score(
  sid int,
  cid int,
  score int NOT NULL,
  PRIMARY KEY(sid,cid),
  FOREIGN KEY(sid) REFERENCES student(id),
  FOREIGN KEY(cid) REFERENCES course(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into student(name,age,sex) values('李军',21,'M'),('王丽',20,'F'),('陆华',19,'M'),('李芬',20,'F'),('王虎',21,'M');
insert into course(name) values('数据库'),('算法与数据结构'),('计算机原理'),('操作系统'),('Java语言');
insert into score(sid, cid, score) values(1, 1, 88),(1, 3, 80),(1, 5, 75),(2, 1, 90),(2, 2, 70),(2, 3, 82),(3, 1, 60),(3, 4, 76),(4, 1, 98),(4, 5, 90),(5, 1, 66),(5, 4, 78),(5, 5, 95);
CREATE TABLE book (
                        id int(11) NOT NULL auto_increment,
                        title varchar(32) NOT NULL COMMENT '书名',
                        author varchar(32) default NULL COMMENT '作者',
                        publication_date datetime default NULL COMMENT '出版日期',
                        price int(32) default NULL COMMENT '价格',
                        PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert  into book(id,title,author,publication_date,price) values (1,'西游记','吴承恩','1980-02-27 17:47:08',65),(2,'三国演义','罗贯中','1991-03-02 15:09:37',59),(3,'水浒传','施耐庵','1984-05-02 17:29:33',61),(4,'红楼梦','曹雪芹','1994-06-06 21:33:18',88);
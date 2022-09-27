create table tb_library
(
    id          int auto_increment
        primary key,
    bookNumber  varchar(10) null,
    bookName    varchar(20) null,
    author      varchar(20) null,
    price       varchar(20) null,
    description varchar(20) null,
    status      varchar(10) null
);

INSERT INTO library_information.tb_library (id, bookNumber, bookName, author, price, description, status) VALUES (1, '00001', '苏菲的世界', '乔斯坦·贾德', '65', '一本好书', '4.0');
INSERT INTO library_information.tb_library (id, bookNumber, bookName, author, price, description, status) VALUES (3, '00002', '麦田里的守望者', '塞林格', '45', '一本好书', '4.0');
INSERT INTO library_information.tb_library (id, bookNumber, bookName, author, price, description, status) VALUES (4, '00003', '红龙', '字符人。', '59', '吃人魔的救赎 ', null);
INSERT INTO library_information.tb_library (id, bookNumber, bookName, author, price, description, status) VALUES (5, '00004', '穆斯林的葬礼', '霍达', '35', '一本好书', '4.0');
INSERT INTO library_information.tb_library (id, bookNumber, bookName, author, price, description, status) VALUES (6, '00005', '基督山伯爵', '大仲马', '65', '一本好书', '4.0');

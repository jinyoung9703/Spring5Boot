-- member
create table member2(
  mno       int             auto_increment,
  userid    varchar(18)     unique,
  passwd    varchar(18)     not null,
  name      varchar(10)     not null,
  email     varchar(50)     unique,
  zipcode   char(7)         not null,
  addr1     varchar(100)    not null,
  addr2     varchar(100)    not null,
  phone     char(13)        not null,
  regdate   datetime        default current_timestamp,
  primary key (mno)
);

insert into member2
(userid, passwd, name, email, zipcode, addr1, addr2, phone)
values ('abc123','987xyz','com','com@abc123.co.kr',
        '123-456','서울 관악구','블라블라','123-4567-8912');

select * from member2;

-- board
create table board2 (
    bno         int             auto_increment,
    title       varchar(100)    not null,
    userid      varchar(18)     not null,
    regdate     datetime        default current_timestamp,
    thumbs      int             default 0,
    views       int             default 0,
    contents    text            not null,
    ipaddr      varchar(15)     not null,
    primary key (bno)
    -- ,foreign key (userid) references member2(userid) 외래키 제약
);

alter table board2
    add constraint fkuid -- 위와 다르게 제약조건 이름을 지정했기 때문에 바로 찾을 수 있다. 그래서 따로 작성하는 편이 좋음
        foreign key (userid) references member2 (userid);

insert into board2(userid,title,contents,ipaddr)
    value ('abc123','집가고싶다','aas','123.12.123.123');

insert into board2(userid,title,contents,ipaddr)
    value ('abc1234','집가고싶다1','abcd','123.12.123.132');

insert into board2(userid,title,contents,ipaddr)
    value ('qwer1234','집가고싶다2','abcde','123.12.123.113');

select count(userid) cnt, ceil(count(userid) / 25) pages from board2;

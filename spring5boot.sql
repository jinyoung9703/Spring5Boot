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


select  * from board2
where title like '%이강인%';

 -- pds
 create table pds(
     pno         int             auto_increment,
     title       varchar(100)    not null,
     userid      varchar(18)     not null,
     regdate     datetime        default current_timestamp,
     thumbs      int             default 0,
     views       int             default 0,
     contents    text            not null,
     ipaddr      varchar(15)     not null,
     primary key (pno)
 );

create table pdsattach (
    pano        int             auto_increment,
    pno         int             not null, -- 게시글번호
    fname       varchar(200)    not null, -- uuid 포함
    ftype       varchar(3)      not null,
    fsize       float           not null,
    fdown       int             default 0,
    primary key (pano)
);

alter table pds
    add constraint fkpuid
        foreign key (userid) references member2 (userid);

alter table pdsattach
    add constraint fkpno
        foreign key (pno) references pds (pno);

-- join
select* from pds p join pdsattach pa
                        using(pno) where p.pno = '12';

-- view (가상테이블)
create view ppa
as
select * from pds p join pdsattach pa
                         using(pno);

select * from ppa where pno = '12';


-- comments
create table pdscomments (
    cno             int             auto_increment,
    comments        mediumtext      not null,
    userid          varchar(18)     not null,
    regdate         datetime        default current_timestamp,
    pno             int             not null,
    ref             int             not null,
    primary key (cno)
);

alter table pdscomments
 add constraint fkpcrefcno
    foreign key (ref) references pdscomments(cno);

alter table pdscomments
    add constraint fkpnopno
        foreign key (pno) references pds(pno);

insert into pdscomments (userid, comments, ref, pno)
values ('abc123','댓글1','1','14');

insert into pdscomments (userid, comments, ref, pno)
values ('abc123','댓글2','2','14');

insert into pdscomments (userid, comments, ref, pno)
values ('abc123','댓글3','3','14');

insert into pdscomments (userid, comments, ref, pno)
values ('abc','대댓글1','2','14');

insert into pdscomments (userid, comments, ref, pno)
values ('abc123','댓글4','4','14');

select * from pdscomments;

select * from pdscomments where pno =14
order by ref;

-- gallery
create table gallery(
gno         int             auto_increment,
title       varchar(100)    not null,
userid      varchar(18)     not null,
regdate     datetime        default current_timestamp,
thumbs      int             default 0,
views       int             default 0,
contents    text            not null,
ipaddr      varchar(15)     not null,
primary key (gno)
);

-- galattach

create table galattach (
gano        int              auto_increment,
gno         int              not null, -- 게시글번호
fname       varchar(1024)    not null, -- uuid 포함
fsize       varchar(256)    not null,
primary key (gano)
);

alter table gallery
    add constraint fkguid
        foreign key (userid) references member2 (userid);

alter table galattach
    add constraint fkgno
        foreign key (gno) references gallery (gno);

-- view (가상테이블)
create view gga
as
select * from gallery g join galattach ga using(gno);

select * from gga;

create view gga0
as

select gno, title, userid, substring(regdate,1,10) regdate,
       thumbs, views, fname
    from gallery g join galattach ga using (gno);
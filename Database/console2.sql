set  schema 'library';

create table magazine(
    id serial primary key,
    publisher varchar(50) not null,
    title varchar(50) not null,
    volume integer,
    date date not null,
    genre varchar(50),
    librarian_ssn ssn_librarian references librarian(ssn) on delete set null on update cascade
);

alter table book
alter column librarian_ssn drop not null;


alter table book
alter column publisher set not null;

alter table magazine
alter column title set not null ;

alter  table  magazine
alter column publisher set not null;

alter table magazine
alter column date set not  null;

alter table librarian
add constraint valid_ssn check(ssn> 999999999999 and ssn<=9999999999999);

create table Librarian (
    ssn ssn_librarian primary key check(ssn> 999999999999 and ssn<=9999999999999),
    password varchar(20)  not null,
    f_name  varchar(50) not null,
    l_name varchar(50) not null,
    dateOfEmployment date  not null
);
drop table librarian;
create domain ssn_librarian as bigint ;
drop  domain ssn_librarian;

create table  Book(
    id serial primary key,
    isbn varchar(20) unique not null,
    publisher varchar(50) not null,
    title varchar(50) not null,
    year_published integer,
    author varchar(50),
    edition integer,
    librarian_ssn ssn_librarian not null references librarian(ssn) on delete set null on update cascade/* I  have chosen this because in our requirements we do not require to find who added some book*/
);

alter table book
add column ssn_librarian not null;

create table Genre (
    id  serial primary key,/*I was going to set null but it is controversial with being pk*/
    genre varchar(50) /*which genres wwe want?*/
);

create table book_genre(
    book_id  integer ,
    genre_id integer ,
    PRIMARY KEY(book_id, genre_id),
    FOREIGN KEY (book_id) REFERENCES Book(id) on delete cascade  on update cascade ,
    FOREIGN KEY (genre_id)REFERENCES Genre(id) on delete restrict on update cascade
);

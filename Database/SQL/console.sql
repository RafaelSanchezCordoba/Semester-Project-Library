create schema if not exists library;
set schema 'library';


create domain ssn_librarian as integer;

create table Librarian (
    ssn ssn_librarian primary key,
    password varchar(20)  not null,
    f_name  varchar(50) not null,
    l_name varchar(50) not null,
    dateOfEmployment date  not null
);


create table Magazine(
    id serial primary key,
    publisher varchar(50),
    title varchar(50),
    volume integer,
    date date,
    genre_id  integer references  Genre(id)on update cascade on delete restrict ,
    librarian_ssn ssn_librarian not null references librarian(ssn) on delete set null on update cascade
);
create table  Book(
    id serial primary key,
    isbn integer unique not null,
    publisher varchar(50),
    title varchar(50) not null,
    year_published integer,
    author varchar(50),
    edition integer,
    librarian_ssn ssn_librarian not null references librarian(ssn) on delete set null on update cascade/* I  have chosen this because in our requirements we do not require to find who added some book*/
);



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

insert into  librarian(ssn,password,f_name,l_name,dateOfEmployment)
values (1802007570,'lalalalibrarian', 'lola', 'petersen', '2000-02-18');

insert into book(isbn, publisher, title, author, edition, librarian_ssn)
values (345676789,  'lalalla', 'lelele','pepe','2',1802007570);
insert into book(isbn, publisher, title, author, edition, librarian_ssn)
values (345676788,  'lalalla', '','rosa','2',1802007570);
insert into genre(genre)
values ('horror'),('comedy');


insert into book_genre(book_id, genre_id)
values (1, 1),(1,2),(2,1),(2,2);

insert into magazine(publisher, title, volume, genre_id, librarian_ssn)
values ('dfgh','dsfdf',3);


update Book
set id=2
where id=4;

delete from Book
where id= 2;

delete from genre
where genre='horror';

delete from  genre
where id=3;

delete from  book_genre
where genre_id=2 and book_id=5;

alter table book_genre
drop constraint book_genre_book_id_fkey;

alter table  book_genre
add constraint book_id FOREIGN KEY (book_id)REFERENCES Book(id) on delete cascade on  update cascade;

alter table book_genre
drop constraint book_genre_genre_id_fkey;

alter table book_genre
drop constraint genre_id;
alter table book_genre
add constraint genre_id FOREIGN KEY (genre_id)REFERENCES Genre(id) on delete restrict on  update cascade;


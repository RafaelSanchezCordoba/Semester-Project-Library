SET SCHEMA 'library';

CREATE DOMAIN ssn_libraryUser char(13);

CREATE TABLE library_user(
    ssn ssn_libraryUser PRIMARY KEY,
    password varchar(20)  NOT NULL ,
    f_name  varchar(50) NOT NULL,
    l_name varchar(50) NOT NULL,
    librarian_ssn ssn_librarian REFERENCES librarian(ssn) ON DELETE SET NULL ON UPDATE CASCADE
);
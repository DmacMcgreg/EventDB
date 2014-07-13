--SQL Fake Book Song Data


--CREATE DATABASE TABLES
--=======================

create table if not exists venues(
      venueId integer primary key not null, --auto increment key 
      capacity int, --title of the songs
      country text,  --book code for the the fake book the song is from
      street text, --page number in book where song appears
      city text  --student who contributed the data
);

create table if not exists events(
      eventId integer primary key not null, --auto increment key 
      time int, --title of the songs
      name text,  --book code for the the fake book the song is from
      price text, --page number in book where song appears
      eventDate text,  --student who contributed the data
      type text,
      location text,
      attendeeList text,
      foreign key(location) references venues(venueID) on delete cascade
);

create table if not exists person(
      personId integer primary key not null, --auto increment key 
      firstName text, --title of the songs
      lastname text
);

--INSERT DATA
--=======================

begin transaction;



insert into person(firstName, lastname) values ('Dave', 'McGregor');
insert into person(firstName, lastname) values ('Gordon', 'McGregor');
insert into person(firstName, lastname) values ('Ben', 'Sweett');
insert into person(firstName, lastname) values ('Lou', 'Nel');

insert into events(time, name, price, eventDate, type, location, attendeeList) values (4 , 'Bluesfest' , '$100' , 'July 4', 'Concert' , 'Lebreton Flats' , 'Dave McGregor');

/*
  Replace the above insert statements with ones that contribute your data
*/

end transaction;
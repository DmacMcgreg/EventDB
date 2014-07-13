--SQL Fake Book Song Data


--CREATE DATABASE TABLES
--=======================

create table if not exists songs(
id integer primary key not null, --auto increment key 
songTitle text NOT NULL, --title of the songs
bookCode text NOT NULL, --book code for the the fake book the song is from
page int, --page number in book where song appears
student_number text NOT NULL --student who contributed the data
);

--INSERT DATA
--=======================

BEGIN TRANSACTION;

--Insert songs
insert into songs(songTitle, bookCode, page, student_number) values ('La Valse Des Lilas','OTH1', 170, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Lady''s Blues','OTH1', 140, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Last Page, The','OTH1', 138, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Let Me Love You','OTH1', 141, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Little One','OTH1', 142, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Liza','OTH1', 144, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Lover','OTH1', 139, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Lucille','OTH1', 92, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Luminescence','OTH1', 146, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Lyresto','OTH1', 147, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Mambo Bounce','OTH1', 148, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Man I Love, The','OTH1', 149, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Mannix','OTH1', 246, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('March On, March On','OTH1', 150, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Mean to Me','OTH1', 151, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Minha Saudade','OTH1', 152, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Minor Mishap','OTH1', 153, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Miss Lucy','OTH1', 154, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Miss Toni','OTH1', 155, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Mrs. Miniver','OTH1', 156, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('My Blues','OTH1', 157, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Nancy','OTH1', 158, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Naptown','OTH1', 159, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Nearness of You, The','OTH1', 160, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('New Thing, A','OTH1', 161, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Nice Work If You Can Get It','OTH1', 162, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('No Moe','OTH1', 163, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('North Atlantic Run','OTH1', 164, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Ode To A Flugelhorn','OTH1', 167, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Old Man and the Child, The','OTH1', 168, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('On A Clear Day','OTH1', 169, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('On the Lion','OTH1', 172, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Once Upon A Summertime','OTH1', 170, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('111-44','OTH1', 171, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Our Delight','OTH1', 173, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Out Back of the Barn','OTH1', 174, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Out of Nowhere','OTH1', 175, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Panther, The','OTH1', 176, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Paradox','OTH1', 175, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Pedal Up','OTH1', 178, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('People Will Say We''re In Love','OTH1', 179, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Picture of Heath','OTH1', 180, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Playin'' in the Yard','OTH1', 181, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Polka Dots and Moonbeams','OTH1', 182, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Poom-A-Loom','OTH1', 183, '100853551');
insert into songs(songTitle, bookCode, page, student_number) values ('Portrait of Jennie','OTH1', 184, '100853551');


/*
Replace the insert statements above with ones that contain the data that you are responsible for.
*/

END TRANSACTION;
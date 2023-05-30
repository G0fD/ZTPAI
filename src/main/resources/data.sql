insert into genders(name) values ('male');
insert into genders(name) values ('female');
insert into genders(name) values ('other');

insert into roles(name) values ('administrator');
insert into roles(name) values ('user');

insert into providers(name) values ('spotify');
insert into providers(name) values ('tidal');
insert into providers(name) values ('youtube');

insert into genres(name) values ('rap');
insert into genres(name) values ('rock');
insert into genres(name) values ('hip-hop');

-- INSERT INTO user_details
-- VALUES (1,'admin@email.com','John','Doe',1);
-- INSERT INTO user_details
-- VALUES (2,'user@email.com','Adam','New', 1);
-- INSERT INTO user_details
-- VALUES (3,'other@email.com','Other','Somebody', 3);
--
-- INSERT INTO users
-- VALUES (1, 'admin', 'admin', 1, 1);
-- INSERT INTO users
-- VALUES (2, 'user', 'user', 2, 2);
-- INSERT INTO users
-- VALUES (3, 'password', 'other', 3, 2);

-- insert into user_preferences values (1, 2);
-- insert into user_preferences values (1, 3);
-- insert into user_preferences values (2, 2);
-- insert into user_preferences values (3, 3);
-- insert into user_preferences values (3, 2);
-- insert into user_preferences values (3, 1);

insert into songs values (1, 'Midnight','Set It Off','midnight.jpg','Killer In The Mirror');
insert into songs values (2, 'Hotel Kalifornia','Hollywood Undead','hk.png','Ruin My Life');

insert into song_providers values (1, 1);
insert into song_providers values (1, 2);
insert into song_providers values (1, 3);
insert into song_providers values (2, 1);

insert into song_genres values (1, 1);
insert into song_genres values (1, 2);
insert into song_genres values (2, 1);
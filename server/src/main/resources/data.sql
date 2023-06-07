insert into genders(name) values ('male');
insert into genders(name) values ('female');
insert into genders(name) values ('other');

insert into roles(name) values ('administrator');
insert into roles(name)
values ('user');

insert into providers(name)
values ('spotify');
insert into providers(name)
values ('tidal');
insert into providers(name)
values ('youtube');

insert into genres(name)
values ('rap');
insert into genres(name)
values ('rock');
insert into genres(name)
values ('hip-hop');

INSERT INTO user_details(email, name, surname, id_gender)
VALUES ('admin@email.com', 'John', 'Doe', 1);
INSERT INTO user_details(email, name, surname, id_gender)
VALUES ('user@email.com', 'Adam', 'New', 1);
INSERT INTO user_details(email, name, surname, id_gender)
VALUES ('other@email.com', 'Other', 'Somebody', 3);

INSERT INTO users(password, username, id_user_details, id_role)
VALUES ('$2a$10$BecK5gXdoePNsyG/FOu6L.cyVL4wYF4UAYkOslqn9mFPGHUJNQVPS', 'admin', 1, 1);
INSERT INTO users(password, username, id_user_details, id_role)
VALUES ('$2a$10$oMheMLZ6Tc3HIyKYQJPQ0.JCR5xeQBO9FrbpM1GDllyV75uD6bjBC', 'user', 2, 2);
INSERT INTO users(password, username, id_user_details, id_role)
VALUES ('$2a$10$DHSJDINzOwbTjRI1D/fSy.EqVSAcApGdefRiq2Osn47r/F/B2omKC', 'other', 3, 2);

insert into user_preferences
values (1, 2);
insert into user_preferences
values (1, 3);
insert into user_preferences
values (2, 2);
insert into user_preferences
values (3, 3);
insert into user_preferences
values (3, 2);
insert into user_preferences
values (3, 1);

insert into songs
values (1, 'Midnight', 'Set It Off', 'midnight.jpg', 'Killer In The Mirror');
insert into songs
values (2, 'Hotel Kalifornia', 'Hollywood Undead', 'hk.png', 'Ruin My Life');

insert into song_providers
values (1, 1);
insert into song_providers
values (1, 2);
insert into song_providers
values (1, 3);
insert into song_providers
values (2, 1);

insert into song_genres
values (1, 1);
insert into song_genres values (1, 2);
insert into song_genres values (2, 1);
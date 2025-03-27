-- A few tables providing some data with which to write SQL queries
-- (in some instances of CSC 370).
-- 
-- Any resemblance to reality is coincidental.
---

drop table Beers cascade;
create table Beers (
    name  char(40) primary key,
    manf  char(25)
);

insert into Beers values ('Blue', 'Labatt''s');
insert into Beers values ('Bud', 'Anheuser-Busch');
insert into Beers values ('Bud Light', 'Anheuser-Busch');
insert into Beers values ('Longboat Porter', 'Phillips');
insert into Beers values ('Amnesiac', 'Phillips');
insert into Beers values ('Blue Buck', 'Phillips');
insert into Beers values ('Chimay Blue', 'Chimay');
insert into Beers values ('Hop Circle IPA', 'Phillips');
insert into Beers values ('Dr Funk', 'Phillips');
insert into Beers values ('Helios', 'Hoyne');
insert into Beers values ('Off the Grid', 'Hoyne');
insert into Beers values ('Dark Matter', 'Hoyne');
insert into Beers values ('Hoyne Pilsner', 'Hoyne');
insert into Beers values ('Down Easy Pale Ale', 'Hoyne');
insert into Beers values ('Devil''s Dream IPA', 'Hoyne');
insert into Beers values ('Wolf Vine', 'Hoyne');
insert into Beers values ('Voltage Espresso Stout', 'Hoyne');
insert into Beers values ('The Big Bock', 'Hoyne');
insert into Beers values ('Driftwood Ale', 'Driftwood');
insert into Beers values ('Farmhand Saison', 'Driftwood');
insert into Beers values ('White Bark Witbier', 'Driftwood');
insert into Beers values ('Crooked Coast Altbier', 'Driftwood');
insert into Beers values ('Fat Tug IPA', 'Driftwood');
insert into Beers values ('Blackstone Porter', 'Driftwood');
insert into Beers values ('Schneider Weisse', 'G. Schneider und Sohn');
insert into Beers values ('Schneider Weisse Adventinus', 'G. Schneider und Sohn');



drop table Sells cascade;
create table Sells (
    pub  char(20),
    beer char(40),
    price real,
    primary key (pub, beer)
--    primary key (pub, beer),
--    foreign key(beer) references Beers(name)
--        on delete set null
--        on update cascade
);

insert into Sells values ('Bard and Banker', 'Blue', 3.50);
insert into Sells values ('Bard and Banker', 'Amnesiac', 7.25);
insert into Sells values ('The Hacked Library', 'Blue', 3.25);
insert into Sells values ('The Hacked Library', 'Amnesiac', 7.00);
insert into Sells values ('The Hacked Library', 'Bud Light', 4.00);
insert into Sells values ('Free Lunch Cafe', 'Blue', null);
insert into Sells values ('Moe''s', 'Blue', 4.50);
insert into Sells values ('Moe''s', 'Off the Grid', 5.00);

drop table Likes cascade;
create table Likes (
    patron char(20),
    beer   char(40)
);
insert into Likes values ('Cliff', 'Blue');
insert into Likes values ('Cliff', 'Bud Light');
insert into Likes values ('Mike', 'Amnesiac');
insert into Likes values ('Abe', 'Guinness');
insert into Likes values ('Norm', 'Blue');
insert into Likes values ('Frasier', 'Chimay Blue');
insert into Likes values ('Weirdo', 'Slashdot_Ale');
insert into Likes values ('Weirdo', 'The 12% solution');

drop table Frequents cascade;
create table Frequents (
    pub     char(20),
    patron  char(20),
    primary key (patron)
);
insert into Frequents values ('Bard and Banker', 'Cliff');
insert into Frequents values ('Bard and Banker', 'Abe');
insert into Frequents values ('Fernwood Inn', 'Mike');
insert into Frequents values ('Cheers', 'Norm');

drop table Patrons cascade;
create table Patrons (
    name    char(20),
    address char(20),
    phone   char(16)
);
insert into Patrons values ('Cliff', 'Fort Street', '250-555-1212');
insert into Patrons values ('Frasier', 'Beach Drive', '250-555-1213');
insert into Patrons values ('Norm', 'Shelbourne Street', '250-555-1214');

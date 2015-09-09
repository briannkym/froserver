CREATE TABLE vans (
van int PRIMARY KEY,
description text
);

CREATE TABLE mileage (
van int REFERENCES vans,
date date,
route int,
miles int,
PRIMARY KEY(van, date, route)
);


CREATE TABLE sites (
site text PRIMARY KEY,
address text,
information text,
pickup boolean
);

CREATE TABLE categories (
category text PRIMARY KEY
);

CREATE TABLE routes (
route int,
stop int,
site text REFERENCES sites,
PRIMARY KEY(route, stop)
);

CREATE TABLE expectedcat (
site text REFERENCES sites,
category text REFERENCES categories,
PRIMARY KEY(site, category)
);


CREATE TABLE pickuppounds (
site text REFERENCES sites,
date date,
category text REFERENCES categories,
pounds int CHECK (pounds >= 0),
PRIMARY KEY(site, date, category)
);

CREATE TABLE dropoffpounds (
site text REFERENCES sites,
date date,
category text REFERENCES categories,
pounds int CHECK (pounds >= 0),
PRIMARY KEY(site, date, category)
);

CREATE TABLE standingrequests (
date date,
category text REFERENCES categories,
pounds int CHECK (pounds >= 0),
PRIMARY KEY(date, category)
);

//Use pg_dump to back up server:
http://stackoverflow.com/questions/1237725/copying-postgresql-database-to-another-server

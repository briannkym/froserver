CREATE TABLE vans (
van int PRIMARY KEY,
description text
);

CREATE TABLE mileage (
van int REFERENCES vans ON UPDATE CASCADE,
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
site text REFERENCES sites ON UPDATE CASCADE,
PRIMARY KEY(route, stop),
UNIQUE(route, site)
);

CREATE TABLE expectedcat (
site text REFERENCES sites ON UPDATE CASCADE,
category text REFERENCES categories ON UPDATE CASCADE,
PRIMARY KEY(site, category)
);

CREATE TABLE pickuppounds (
site text REFERENCES sites ON UPDATE CASCADE,
date date,
category text REFERENCES categories ON UPDATE CASCADE,
pounds int CHECK (pounds >= 0),
PRIMARY KEY(site, date, category)
);

CREATE TABLE dropoffpounds (
site text REFERENCES sites ON UPDATE CASCADE,
date date,
category text REFERENCES categories ON UPDATE CASCADE,
route int,
pounds int CHECK (pounds >= 0),
PRIMARY KEY(site, date, category),
FOREIGN KEY(route, site) REFERENCES routes(route, site) ON UPDATE CASCADE
);

CREATE TABLE standingrequests (
date date,
category text REFERENCES categories ON UPDATE CASCADE,
pounds int CHECK (pounds >= 0),
description text,
PRIMARY KEY(date, description)
);

//Use pg_dump to back up server:
http://stackoverflow.com/questions/1237725/copying-postgresql-database-to-another-server

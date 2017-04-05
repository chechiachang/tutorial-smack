CREATE TABLE campaign(
  id uuid,
  year int,
  month int,
  day int,
  views bigint,
  clicks bigint,
  PRIMARY KEY(id, year, month, day)
);

INSERT INTO campaign(id, year, month, day, views, clicks) VALUES(uuid(), 2017, 4, 4, 1000, 42);

// SELECT views, clicks FROM campaign WHERE year = 2015 AND month > 8;

CREATE TABLE event(
  id uuid,
  ad_id uuid,
  campaign uuid,
  ts bigint,
  type text,
  PRIMARY KEY(id)
);
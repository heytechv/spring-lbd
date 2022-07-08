DROP TABLE IF EXISTS SPRINT_USER_STORY;
DROP TABLE IF EXISTS Sprint;
DROP TABLE IF EXISTS UserStory;

CREATE TABLE IF NOT EXISTS Sprint(
    id integer PRIMARY KEY AUTO_INCREMENT,
    name text NOT NULL,
    start_date timestamp,
    end_date timestamp,
    description text,
    status text NOT NULL
);

CREATE TABLE IF NOT EXISTS UserStory(
    id integer PRIMARY KEY AUTO_INCREMENT,
    name text NOT NULL,
    description text NOT NULL,
    story_points_amount integer,
    status varchar(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS SPRINT_USER_STORY(
    sprint_id integer NOT NULL references Sprint(id),
    user_story_id integer NOT NULL references UserStory(id)
);
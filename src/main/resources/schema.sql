DROP TABLE IF EXISTS Attachment;
DROP TABLE IF EXISTS SPRINT_USER_STORY;
DROP TABLE IF EXISTS Sprint;
DROP TABLE IF EXISTS UserStory;

CREATE TABLE IF NOT EXISTS Sprint(
    id integer PRIMARY KEY AUTO_INCREMENT,
    name text NOT NULL,
    start_date timestamp,
    end_date timestamp,
    description text,
    status ENUM('PENDING','IN_PROGRESS','FINISHED','CANCELED') NOT NULL
);

CREATE TABLE IF NOT EXISTS UserStory(
    id integer PRIMARY KEY AUTO_INCREMENT,
    name text NOT NULL,
    description text NOT NULL,
    story_points_amount integer,
    status ENUM('TO_DO', 'IN_PROGRESS', 'REVIEW', 'DONE') NOT NULL
);

CREATE TABLE IF NOT EXISTS SPRINT_USER_STORY(
    sprint_id integer NOT NULL references Sprint(id),
    user_story_id integer NOT NULL references UserStory(id)
);

-- https://stackoverflow.com/questions/4450432/sql-server-how-to-store-binary-data-e-g-word-file
CREATE TABLE IF NOT EXISTS Attachment(
    id integer PRIMARY KEY AUTO_INCREMENT,
    user_story_id integer NOT NULL references UserStory(id),
    file_name text,
    binary_file varbinary
);


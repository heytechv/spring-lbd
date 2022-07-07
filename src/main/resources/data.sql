-- Calosc jako transaction ?

-- 1xSprint
INSERT INTO Sprint(name, start_date, description, status) VALUES ( 'Sprint1', CURRENT_TIMESTAMP, 'Opis1', 'PENDING' );
SET @sprintKey= (SELECT MAX(id) FROM Sprint);

-- 4xUserStories
INSERT INTO UserStory(name, description, status) VALUES ( 'UserStory1', 'Opis1', 'PENDING' );
SET @userStoryKey=(SELECT MAX(id) FROM UserStory);
INSERT INTO SprintUserStory(sprint_id, user_story_id) VALUES ( @sprintKey, @userStoryKey );

INSERT INTO UserStory(name, description, status) VALUES ( 'UserStory1', 'Opis1', 'PENDING' );
SET @userStoryKey=(SELECT MAX(id) FROM UserStory);
INSERT INTO SprintUserStory(sprint_id, user_story_id) VALUES ( @sprintKey, @userStoryKey );

INSERT INTO UserStory(name, description, status) VALUES ( 'UserStory1', 'Opis1', 'PENDING' );
SET @userStoryKey=(SELECT MAX(id) FROM UserStory);
INSERT INTO SprintUserStory(sprint_id, user_story_id) VALUES ( @sprintKey, @userStoryKey );

INSERT INTO UserStory(name, description, status) VALUES ( 'UserStory1', 'Opis1', 'PENDING' );
SET @userStoryKey=(SELECT MAX(id) FROM UserStory);
INSERT INTO SprintUserStory(sprint_id, user_story_id) VALUES ( @sprintKey, @userStoryKey );
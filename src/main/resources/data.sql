-- Calosc jako transaction ?

-- 1xSprint
INSERT INTO Sprint(name, start_date, description, status) VALUES ( 'Sprint1', CURRENT_TIMESTAMP, 'Opis1', 'PENDING' );
SET @sprintKey= (SELECT MAX(id) FROM Sprint);

-- 4xUserStories
INSERT INTO UserStory(name, story_points_amount, description, status) VALUES ( 'UserStory1', 5, 'Opis1', 'DONE' );
SET @userStoryKey=(SELECT MAX(id) FROM UserStory);
INSERT INTO SPRINT_USER_STORY(sprint_id, user_story_id) VALUES ( @sprintKey, @userStoryKey );

INSERT INTO UserStory(name, description, status) VALUES ( 'UserStory2', 'jest git', 'IN_PROGRESS' );
SET @userStoryKey=(SELECT MAX(id) FROM UserStory);
INSERT INTO SPRINT_USER_STORY(sprint_id, user_story_id) VALUES ( @sprintKey, @userStoryKey );

INSERT INTO UserStory(name, description, status) VALUES ( 'UserStory3', 'suuppeeer', 'TO_DO' );
SET @userStoryKey=(SELECT MAX(id) FROM UserStory);
INSERT INTO SPRINT_USER_STORY(sprint_id, user_story_id) VALUES ( @sprintKey, @userStoryKey );

INSERT INTO UserStory(name, story_points_amount, description, status) VALUES ( 'UserStory4', 2, 'Opis jakis', 'DONE' );
SET @userStoryKey=(SELECT MAX(id) FROM UserStory);
INSERT INTO SPRINT_USER_STORY(sprint_id, user_story_id) VALUES ( @sprintKey, @userStoryKey );
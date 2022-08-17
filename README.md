# REST API

### `POST /sprint`
Add new Sprint

### `GET /sprint`
Get all Sprints

### `GET /sprint/{id}`
Get Sprint by id

### `POST /sprint/{id}/adduserstory`
Create and add UserStory to Sprint

### `GET /sprint/{id}/storypoints`
Get sum of all story points that Sprint contains

### `GET /sprint/{id}/userstories`
Get UserStories that belongs to Sprint

### `PUT /sprint/{id}/updatestatus`
Update status of Sprint

### `GET /sprint/daterange`
Get Sprints in date range


### `GET /userstory/{id}/description`
Get description of UserStory

### `DELETE /userstory/{id}`
Delete UserStory

### `GET /userstory/sorted`
Get UserStories by page with limit

### `POST /userstory/{id}/addattachment`
Add new Attachment to UserStory

### `GET /userstory/{id}/attachment`
Get list of all Atachments that belongs to UserStory


### `GET /attachment/{id}`
Download Attachment by id


### `GET /whoami`
Whoami (logged in user)

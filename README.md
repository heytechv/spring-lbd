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




### `GET /sprints`
Get list of Sprints.
- http://localhost:8080/sprints
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Default value</td>
        <td>Required</td>
        <td>Description</td>
    </tr>
    <tr>
        <td>tasks</td>
        <td>boolean</td>
        <td>false</td>
        <td>No</td>
        <td>show UserStoryList for every Sprint</td>
    </tr>
</table>

Result:
```json
{
  "status": 200,
  "data": [
    {
      "id": 1,
      "name": "Sprint1",
      "description": "Opis1",
      "status": "PENDING"
    },
    {
      "id": 2,
      "name": "7ec68612-b4fc-4be9-a79f-c55e7021d6ce",
      "description": "1d6e73fb-b07d-45f1-8966-6c5cf2b41167",
      "status": "FINISHED"
    },
    {
      "id": 3,
      "name": "b99a0984-f255-49a7-b395-fa99691bb3f3",
      "description": "11598ee8-44d2-4f08-b3c6-f3d096c43ebf",
      "status": "FINISHED"
    },
    {
      "id": 4,
      "name": "d9d39875-610f-4789-b0df-63aabd0ce891",
      "description": "e652713e-6645-4e93-80c8-a16e7521d030",
      "status": "IN_PROGRESS"
    },
    {
      "id": 5,
      "name": "73ba8057-1bde-415d-9654-58ff419132d1",
      "description": "bd3046b4-4588-4811-a5f9-8fa7555c4dc4",
      "status": "FINISHED"
    },
    {
      "id": 6,
      "name": "8ec0e410-5cfa-45ed-9422-83850dd844f1",
      "description": "0614815e-7097-4d31-aada-6160cff40ad7",
      "status": "IN_PROGRESS"
    }
  ],
  "message": "found"
}
```

### `POST /sprints/addstory`
Add new UserStory with default data to Sprint located by id.
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
    </tr>
    <tr>
        <td>sprintId</td>
        <td>Long</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>userStoryName</td>
        <td>String</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>userStoryDesc</td>
        <td>String</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>userStoryPoints</td>
        <td>Integer</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>userStoryStatus</td>
        <td>TO_DO, IN_PROGRESS, REVIEW, DONE</td>
        <td>Yes</td>
    </tr>
</table>

Response:
```json
{
    "status": 200,
    "data": "",
    "message": "added"
}
```

### `GET /sprints/storypoints`
Get sum of all UserStory points from Sprint located by id.
- http://localhost:8080/sprints/storypoints?sprintId=1
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
    </tr>
    <tr>
        <td>sprintId</td>
        <td>Long</td>
        <td>Yes</td>
    </tr>
</table>

Result:
```json
{
    "status": 200,
    "data": 10,
    "message": "found"
}
```

### `GET /sprints/userstories`
Get list of UserStory from Sprint located by id.
- http://localhost:8080/sprints/userstories?sprintId=1
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
    </tr>
    <tr>
        <td>sprintId</td>
        <td>Long</td>
        <td>Yes</td>
    </tr>
</table>

Result:
```json
{
  "status": 200,
  "data": [
    {
      "id": 3,
      "name": "UserStory3",
      "status": "TO_DO"
    },
    {
      "id": 4,
      "name": "UserStory4",
      "status": "DONE",
      "storyPointsAmount": 2
    },
    {
      "id": 2,
      "name": "UserStory2",
      "status": "IN_PROGRESS"
    },
    {
      "id": 1,
      "name": "UserStory1",
      "status": "DONE",
      "storyPointsAmount": 5
    }
  ],
  "message": "found"
}
```

### `GET /userstories/description`
Get list description from UserStory located by id.
- http://localhost:8080/userstories/description?userStoryId=1
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
    </tr>
    <tr>
        <td>userStoryId</td>
        <td>Long</td>
        <td>Yes</td>
    </tr>
</table>

Response:
```json
{
    "status": 200,
    "data": "Opis1",
    "message": "found"
}
```

### `POST /userstories/addattachment`
Add an attachment with default data to UserStory located by id.
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
    </tr>
    <tr>
        <td>userStoryId</td>
        <td>Long</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>attachmentFile</td>
        <td>MultipartFile?</td>
        <td>Yes</td>
    </tr>
</table>

Response:
```json
{
    "status": 200,
    "data": "",
    "message": "added"
}
```

### `GET /userstories/attachments`
Get list of attachments from UserStory located by id.
- http://localhost:8080/userstories/attachments?userStoryId=1
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
    </tr>
    <tr>
        <td>userStoryId</td>
        <td>Long</td>
        <td>Yes</td>
    </tr>
</table>

Response:
```json
{
  "status": 200,
  "data": [
    {
      "binaryFile": "IyBodgR3mddKGh0dHBzOlcy90dXRvcmlhbHMvcmVzdC8pCgo="
    }
  ],
  "message": "found"
}
```

### `PUT /sprints/updatestatus`
Update status of Sprint located by id.
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
    </tr>
    <tr>
        <td>sprintId</td>
        <td>Long</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>newStatus</td>
        <td>PENDING, IN_PROGRESS, FINISHED, CANCELED</td>
        <td>Yes</td>
    </tr>
</table>

Response:
```json
{
    "status": 200,
    "data": "OK",
    "message": "updated"
}
```

### `DELETE /userstories/deleteuserstory`
Delete UserStory located by id.
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
    </tr>
    <tr>
        <td>userStoryId</td>
        <td>Long</td>
        <td>Yes</td>
    </tr>
</table>

Response:
```json
{
    "status": 200,
    "data": "",
    "message": "deleted"
}
```

### `GET /sprints/daterange`
Get list of Sprints where startDate is in range.
- http://localhost:8080/sprints/daterange?startDate=2022-01-01%2000:00:00.0&endDate=2023-01-01%2000:00:00.0
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
        <td>Example</td>
    </tr>
    <tr>
        <td>startDate</td>
        <td>String</td>
        <td>Yes</td>
        <td>2022-01-01 00:00:00.0</td>
    </tr>
    <tr>
        <td>endDate</td>
        <td>String</td>
        <td>Yes</td>
        <td>2022-09-01 00:00:00.0</td>
    </tr>
</table>

Response:
```json
{
  "status": 200,
  "data": [
    {
      "id": 1,
      "name": "Sprint1",
      "startDate": "2022-07-15T11:32:43.934+00:00",
      "status": "PENDING"
    },
    {
      "id": 2,
      "name": "0c2e094b-cf70-4d66-a5c9-a826448a1574",
      "startDate": "2022-02-28T23:00:00.000+00:00",
      "endDate": "2022-04-01T03:00:00.000+00:00",
      "status": "IN_PROGRESS"
    },
    {
      "id": 4,
      "name": "703ef4fb-c9cc-498b-9265-929fa23e7edb",
      "startDate": "2022-05-31T22:00:00.000+00:00",
      "endDate": "2022-07-01T03:00:00.000+00:00",
      "status": "FINISHED"
    }
  ],
  "message": "ok"
}
```

### `GET /userstories/sorted`
Get list of UserStory paged and sorted.
- http://localhost:8080/userstories/sorted?page=0&limit=2
<table>
    <tr>
        <td>Param</td>
        <td>Type</td>
        <td>Required</td>
    </tr>
    <tr>
        <td>page</td>
        <td>int</td>
        <td>Yes</td>
    </tr>
    <tr>
        <td>limit</td>
        <td>int</td>
        <td>Yes</td>
    </tr>
</table>

Response:
```json
{
  "status": 200,
  "data": [
    {
      "id": 6,
      "name": "UserStory0",
      "status": "TO_DO",
      "storyPointsAmount": 3
    },
    {
      "id": 1,
      "name": "UserStory1",
      "status": "DONE",
      "storyPointsAmount": 5
    },
    {
      "id": 2,
      "name": "UserStory2",
      "status": "IN_PROGRESS"
    },
    {
      "id": 3,
      "name": "UserStory3",
      "status": "TO_DO"
    },
    {
      "id": 4,
      "name": "UserStory4",
      "status": "DONE",
      "storyPointsAmount": 2
    },
    {
      "id": 5,
      "name": "UserStorya",
      "status": "TO_DO",
      "storyPointsAmount": 3
    }
  ],
  "message": "found"
}
```

### `GET /whoami`
Whoami - you need to sign in.
- http://localhost:8080/whoami

<b>[USER]</b><br/>
user: usern<br/>
pass: usern<br/>

<b>[ADMIN]</b><br/>
user: usera<br/>
pass: usera<br/>


Response:
```json
{
  "status": 200,
  "data": "usera [ROLE_ADMIN]",
  "message": "logged in user"
}
```


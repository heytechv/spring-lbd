# REST API

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
  "status": "OK",
  "data": [
    {
      "id": 1,
      "name": "Sprint1",
      "description": "Opis1",
      "status": "PENDING",
      "userStoryDtos": [
        
      ]
    },
    {
      "id": 2,
      "name": "a802da91-db9a-47f3-9adb-5a012e031664",
      "description": "80b237d6-099d-469b-af14-1f10798074dc",
      "status": "PENDING",
      "userStoryDtos": [
        
      ]
    }],
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
    "status": "OK",
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
  "status": "OK",
  "data": 7,
  "message": "ID found."
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
  "status": "OK",
  "data": [
    {
      "id": 6,
      "name": "stronaName",
      "status": "TO_DO",
      "storyPointsAmount": 22
    },
    {
      "id": 4,
      "name": "UserStory4",
      "status": "DONE",
      "storyPointsAmount": 2
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
  "status": "OK",
  "data": "Opis1",
  "message": "foud"
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
    "status": "OK",
    "data": "",
    "message": "dodano"
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
  "status": "OK",
  "data": [
    {
      "id": 1,
      "binaryFile": "c2llbWE="
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
    "status": "OK",
    "data": "OK",
    "message": "Updated!"
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
    "status": "OK",
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


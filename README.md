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
    }]
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
</table>

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
  ]
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
</table>

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
  "status": "OK",
  "data": [
    {
      "id": 1,
      "name": "Sprint1",
      "startDate": "2022-07-13T12:00:11.481+00:00",
      "endDate": null,
      "status": "PENDING"
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
  "status": "OK",
  "data": [
    {
      "id": 3,
      "name": "UserStory3",
      "status": "TO_DO",
      "storyPointsAmount": null
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

### `GET /whoami`
Whoami - you need to sign in.
- http://localhost:8080/userstories/sorted?page=0&limit=2

Response:
```json
{
  "status": "OK",
  "data": "usern [ROLE_USER]",
  "message": "show logger user"
}
```


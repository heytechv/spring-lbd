# -1. Sources
- https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/actuator-api/html/
- https://docs.spring.io/spring-boot/docs/2.1.7.RELEASE/reference/html/production-ready-endpoints.html
- https://www.baeldung.com/spring-boot-actuators
- https://www.javacodegeeks.com/2017/03/spring-boot-security-events-actuator.html
- https://docs.spring.io/spring-boot/docs/current/actuator-api/htmlsingle/

# 0. Base endpoint
### `http://localhost:8080`

# 1. Audit Events
### Provides information about the application’s audit events (security related events AUTH).

## 1.1. Retrieving Audit Events
### `GET /actuator/auditevents`
<table style="border: 1px solid grey">
    <tr>
        <th>Paramater</th>
        <th>Mandatory</th>
        <th>Description</th>
        <th>Example</th>
    </tr>
    <tr>
        <td>after</td>
        <td>NO</td>
        <td>Date after the event occurred in the following format</td>
        <td></td>
    </tr>
    <tr>
        <td>principal</td>
        <td>NO</td>
        <td>Restricts the events to those with the given principal.</td>
        <td>yyyy-MM-dd'T'HH:mm:ss</td>
    </tr>
    <tr>
        <td>type</td>
        <td>NO</td>
        <td>Restricts the events to those with the given type.</td>
        <td>AUTHORIZATION_FAILURE, AUTHENTICATION_SUCCESS, AUTHENTICATION_FAILURE, AUTHENTICATION_SWITCH</td>
    </tr>
</table>


# 2. Beans
### Displays a list of all beans in your application.

## 2.1. Retrieving the Beans
### `GET /actuator/beans`


# 3. Caches
### Provides access to the application’s caches.

## 3.1. Retrieving All Caches
### `GET /actuator/caches`

## 3.2. Retrieving Caches by Name
### `GET /actuator/caches/{name}`

## 3.3. Evict All Caches
### `DELETE /actuator/caches`

## 3.4. Evict a Cache by Name
### `DELETE /actuator/caches/{name}`


# 4. Conditions Evaluation Report
### Shows the conditions that were evaluated on configuration and auto-configuration classes and the reasons why they did or did not match.

## 4.1. Retrieving the Report
### `GET /actuator/conditions`


# 5. Configuration Properties
### Displays a list of all @ConfigurationProperties.

## 5.1. Retrieving the @ConfigurationProperties Bean
### `GET /actuator/configprops`


# 6. Environment
### Exposes properties from Spring’s ConfigurableEnvironment.

## 6.1. Retrieving the Entire Environment
### `GET /actuator/env`

## 6.2. Retrieving a Single Property
### `GET /actuator/env/{property.name}`


# 7. Flyway
### Provides information about database migrations performed by Flyway.

## 7.1. Retrieving the Migrations
### `GET /actuator/flyway`


# 8. Health
### Provides detailed information about the health of the application (eg. status of the components).

## 8.1. Retrieving the Health of the application
### `GET /actuator/health`

## 8.2. Retrieving the Health of a component
### `GET /actuator/health/{component}`

## 8.3. Retrieving the Health of a component instance
### `GET /actuator/health/{component}/{instance}`


# 9. Heap Dump
### Provides a heap dump from the application’s JVM.

## 9.1. Retrieving the Heap Dump
### `GET /actuator/heapdump`


# 10. HTTP Trace
### Provides information about HTTP request-response exchanges (HTTP logs).

## 10.1. Retrieving the Traces
### `GET /actuator/httptrace`


# 11. Info
### Provides general information about the application.

## 11.1. Retrieving the Info
### `GET /actuator/info`


# 12. Spring Integration graph
### Shows a graph containing all Spring Integration components.

## 12.1. Retrieving the Spring Integration graph
### `GET /actuator/integrationgraph`

## 12.2. Rebuilding the Spring Integration graph
### `POST /actuator/integrationgraph`


# 13. Liquibase
### Provides information about database changes applied by Liquibase.

## 13.1. Retrieving the Changes
### `GET /actuator/liquibase`


# 14. Log File
### Returns the contents of the logfile.

## 14.1. Retrieving the Log File
### `GET /actuator/logfile`

## 14.2. Retrieving Part of the Log File
### `GET /actuator/logfile`
- ### header `Range: bytes=0-1023`


# 15. Loggers 
### Shows and modifies the configuration of loggers in the application.

## 15.1. Retrieving All Loggers
### `GET /actuator/loggers`

## 15.2. Retrieving a Single Logger
### `GET /actuator/loggers/{logger.name}`

## 15.3. Setting a Log Level
### `POST /actuator/loggers/{logger.name}`
<table style="border: 1px solid grey">
    <tr>
        <th>Json path</th>
        <th>Type</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>configuredLevel</td>
        <td>String</td>
        <td>Level for the logger. May be omitted to clear the level.</td>
    </tr>
</table>

## 15.4. Clearing a Log Level
### `POST /actuator/loggers/{logger.name}`
<table style="border: 1px solid grey">
    <tr>
        <th>Json path</th>
        <th>Type</th>
        <th>Description</th>
        <th>Example</th>
    </tr>
    <tr>
        <td>$</td>
        <td>empty</td>
        <td>Empty JSON body</td>
        <td>{}</td>
    </tr>
</table>


# 16. Mappings
### Displays a list of all @RequestMapping paths.

## 16.1. Retrieving the Mappings
### `GET /actuator/mappings`


# 17. Metrics
### Provides access to application metrics (eg. jvm.buffer.memory.used).

## 17.1. Retrieving Metric Names
### `GET /actuator/metrics`

## 17.2. Retrieving a Metric
### `GET /actuator/metrics/{metric.name}`

## 17.3. Drilling Down
### `GET /actuator/metrics/{metric.name}`
<table style="border: 1px solid grey">
    <tr>
        <th>Parameter</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>tag</td>
        <td>A tag to use for drill-down in the form name:value.</td>
    </tr>
</table>


# 18. Prometheus
### Exposes metrics in a format that can be scraped by a Prometheus server (monitoring app).

## 18.1. Retrieving the Metrics
### `GET /actuator/prometheus`


# 19. Scheduled Tasks
### Provides information about the application’s scheduled tasks.

## 19.1. Retrieving the Scheduled Tasks
### `GET /actuator/scheduledtasks`


# 20. Sessions
### Provides information about the application’s HTTP sessions that are managed by Spring Session.

## 20.1. Retrieving Sessions
### `GET /actuator/sessions`
<table style="border: 1px solid grey">
    <tr>
        <th>Parameter</th>
        <th>Description</th>
    </tr>
    <tr>
        <td>username</td>
        <td>Name of the user.</td>
    </tr>
</table>

## 20.2. Retrieving a Single Session
### `GET /actuator/sessions/{id}`


## 20.3. Deleting a Session
### `DELETE /actuator/sessions/{id}`


# 21. Shutdown
### Shuts down the application.

## 21.1. Shutting Down the Application
### `POST /actuator/shutdown`


# 22. Thread Dump
### Provides a thread dump from the application’s JVM.

## 22.1. Retrieving the Thread Dump
### `GET /actuator/threaddump`

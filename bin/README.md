# in app_server
## install java 17
```bash
sudo yum install java-17* -y
```
### security group for RDS - EC2 --> all-traffic & Mysql

## install maven with latest version to check 
```bash
mvn --version
```
## create package
```bash
mvn package -DskipTests
```
## run spring propject
```bash
java -jar my-shop-1.0.jar
```
## to create admin user
```bash
http://3.83.106.26:1234/admin/create-admin/admin/admin
```
----------------------------------------------------------------
# in Database server

## insatll mysql
```bash
sudo yum install mysql -y
```

## connect with mysql 
```bash
mysql -h <endpoint> -P 3306 -u admin -p
```

## to show databses
```bash
show databases;
```

## to use databses
```bash
use myshop;
```

## to show tables
```bash
show tables;
```

## to fecth data
```bash
select * from customer;
```

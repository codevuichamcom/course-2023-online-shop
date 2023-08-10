docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=root -d -p 3306:3306 -v mysql-volume:/var/lib/mysql mysql:8.0


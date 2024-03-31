docker run --name mysql -d \
	-p 3306:3306 \
	-e MYSQL_ROOT_PASSWORD=1 \
	mysql:8.3.0

# sudo docker exec -it mysql bash
# mysql -uroot -p1
# CREATE USER 'toan'@'%' IDENTIFIED BY '1';
# GRANT ALL PRIVILEGES ON *.* TO 'toan'@'localhost' WITH GRANT OPTION;
# SELECT user FROM mysql. user;


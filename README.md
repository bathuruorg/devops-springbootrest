# SpringRestServices

Developed in springboot

http://192.168.43.117:8080/api/v1/courses
http://192.168.43.117:8080/api/v1/courses/1


docker build -t bathurudocker/simpleapprest -f Dockerfile .
docker run -d -p 3000:3000 bathurudocker/simpleapprest:latest
docker rm -f
docker rmi bathurudocker/simpleapprest:latest

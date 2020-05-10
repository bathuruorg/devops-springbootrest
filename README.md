# simpleapprest

http://192.168.43.117:8080/instructors/in28minutes/courses

http://192.168.43.117:8080/instructors/in28minutes/courses/1

http://192.168.43.117:8080/instructors/in28minutes/courses/2


docker build -t bathurudocker/simpleapprest -f Dockerfile .
docker run -d -p 3000:3000 bathurudocker/simpleapprest:latest
docker rm -f
docker rmi bathurudocker/simpleapprest:latest

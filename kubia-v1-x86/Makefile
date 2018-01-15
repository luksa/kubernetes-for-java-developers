build:
	mvn clean install

image: build
	docker build -t luksa/kubia-java:v1 .

push: image
	docker push luksa/kubia-java:v1

run: image
	docker run -it -p 8080:8080 luksa/kubia-java:v1

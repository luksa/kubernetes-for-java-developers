build:
	mvn clean install

image: build
	docker build -t luksa/kubia-java-arm:broken .

push: image
	docker push luksa/kubia-java-arm:broken

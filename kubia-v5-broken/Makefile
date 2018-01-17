build:
	mvn clean install

image: build
	docker build -t luksa/kubia-java-arm:v5-broken .

push: image
	docker push luksa/kubia-java-arm:v5-broken

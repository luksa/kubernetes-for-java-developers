build:
	mvn clean install

image: build
	docker build -t luksa/java-system-info .

push: image
	docker push luksa/java-system-info

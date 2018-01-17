build:
	mvn clean install

image: build
	docker build -t luksa/kubia-java-arm:break-on-10th-request .

push: image
	docker push luksa/kubia-java-arm:break-on-10th-request

build:
	mvn clean install

image: build
	docker build -t luksa/kubia-client-arm:v1 .

push: image
	docker push luksa/kubia-client-arm:v1

run: push
	kubectl apply -f client.yaml

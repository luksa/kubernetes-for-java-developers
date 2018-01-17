build:
	mvn clean install

image: build
	docker build -t luksa/java-system-info-arm .

push: image
	docker push luksa/java-system-info-arm

x86image: build
	docker build -f Dockerfile.x86 -t luksa/java-system-info .

x86push: x86image
	docker push luksa/java-system-info

x86run:
	docker run -it --rm --memory 20M --cpus 1 -env JAVA_OPTIONS=-XX:+UseCGroupMemoryLimitForHeap luksa/java-system-info
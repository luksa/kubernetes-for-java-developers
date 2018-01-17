build:
	mvn clean install

push:	build
	mvn fabric8:push

deploy:	push
	mvn fabric8:deploy

run:	push
	mvn fabric8:run

debug:	push
	mvn fabric8:debug

undeploy:
	mvn fabric8:undeploy

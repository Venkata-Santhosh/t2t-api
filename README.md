
Steps to run t2t-api

Step 0:
	mvn clean install -U
Step 1:
	Setup docker environment
	eval “$(docker-machine env myBoxName)”
Step 2:
	Start docker mysql container
		docker start <containerID>
		docker stop  <containerID>
Step 3:
	Create Docker image for restful application
		docker images ( list all images)
		docker build -f Dockerfile -t t2t-api-docker .
Step 4:
	Create Docker container form image
	docker ps -a (list all containers)
	docker run -p 8080:8080 t2t-api-docker

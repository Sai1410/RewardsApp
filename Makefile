
setup:
	docker pull mongo
	docker stop mongodb
	docker rm mongodb
	docker run --name mongodb -d -p 27017:27017 mongo
	mvn clean install

start:
	./mvnw spring-boot:run

integration:
	mvn -Dtest=RewardsHomeworkIT test

example:
	curl -X POST http://localhost:8080/transactions -H 'Content-Type: application/json' -d '{"id":"1","customerName":"Andrew", "date": "10.12.2021", "payment":51.14}'
	curl -X POST http://localhost:8080/transactions -H 'Content-Type: application/json' -d '{"id":"2","customerName":"Andrew", "date": "10.11.2021", "payment":120.23}'
	curl http://localhost:8080/rewards
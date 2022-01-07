# Rewards App

Application calculating rewards with database in MongoDB.
In order to test solution there should be added Transactions first with /transactions endpoint and then calculate 
rewards with calling GET to /rewards endpoint.

## Prerequisites

There has to be installed in terminal
1. Docker
2. Mvn
3. Makefile
4. curl (to run example)

Then run command:
- `docker login`

to log in to hub.docker.com.

## Quick start
Run following commands in terminal:
1. `make setup` - pulls mongodb image, installs dependencies, starts database
2. `make start` - starts application
3. `make integration` - runs integration tests with test data specified in /src/it/mocks/dataSet.json
4. `make example` - adds 2 transactions to database and calls /rewards endpoint to print them. Expected output is:
`{"Andrew":{"total":91,"perMonth":{"11":90,"12":1}}}`, which means that for Andrew total amount of gained points is 91 and he gained
90 points in 11th month (November) and 1 point in 12th month (December).

## Endpoints

Check http://localhost:8080/swagger-ui/index.html

## Set up db

```
docker login
docker pull mongo
docker run --name mongodb -d -p 27017:27017 mongo
```

## Problem
A retailer offers a rewards program to its customers, awarding points based on each recorded purchase.



A customer receives 2 points for every dollar spent over $100 in each transaction, plus 1 point for every dollar spent over $50 in each transaction

(e.g. a $120 purchase = 2x$20 + 1x$50 = 90 points).



Given a record of every transaction during a three month period, calculate the reward points earned for each customer per month and total.
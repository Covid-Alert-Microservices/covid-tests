# Covid Tests [![Build](https://github.com/Covid-Alert-Microservices/covid-tests/actions/workflows/build.yaml/badge.svg)](https://github.com/Covid-Alert-Microservices/covid-tests/actions/workflows/build.yaml) [![codecov](https://codecov.io/gh/Covid-Alert-Microservices/covid-tests/branch/master/graph/badge.svg?token=M3BQVEPCHE)](https://codecov.io/gh/Covid-Alert-Microservices/covid-tests)

This repository manages covid tests for Covid-Alert

## Environment variables

The following environment variables can be configured:
- `KEYCLOAK_URL`: the URL to the Keycloak instance (default: `http://localhost:5000`)
- `KAFKA_URL`: the URL to the Kafka node (default: `http://localhost:29092`)
- `POSTGRES_HOST`: the host for the PostgreSQL database (default: `localhost:5432/postgres`)
- `POSTGRES_USER`: the user for the PostgreSQL database (default: `postgres`)
- `POSTGRES_PASSWORD`: the password for the PostgreSQL database (default: `postgres`)

## Dependencies

In order to use this microservice, make sure you cloned [Kafka](https://github.com/Covid-Alert-Microservices/kafka) and [Keycloak](https://github.com/Covid-Alert-Microservices/keycloak) repositories.

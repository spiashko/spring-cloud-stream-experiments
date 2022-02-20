# spring-cloud-stream-experiments

## Start the demo

Start all components:

```console
$ docker compose up -d
```

Start services in IDE or via `mvn spring-boot:run` in each service folder

It's important to note that sometimes the order or shipment service may fail to start if the dependent database takes
longer than expected to initialize. If that happens, simply re-execute the command again and it will start the remaining
services.

## Call producer API

Trigger message production:

```console
$ http POST "http://localhost:8080/produce?count=1" 
```

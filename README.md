# GrabJokes

How to start the GrabJokes application
---

1. Run `mvn clean install` to build your application
1. Start application with `java -jar target/grab-jokes-1.0-SNAPSHOT.jar server config.yml`
1. To run the get jokes request, enter url `http://localhost:8080/grab-jokes?keyword={keyword}`

The same keyword is rate limited 5 times per minute. Rate limiting is done using [bucket4j](https://github.com/bucket4j/bucket4j), which bases on token-bucket algorithm.

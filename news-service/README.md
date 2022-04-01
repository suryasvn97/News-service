# News Service

An HTTP Service that returns random news results back.

## Running the app
You can run the app - locally with Maven, or in your IDE.

**Prerequisites**
These instructions assume you have the following installed: 
- Maven
- Java
- JDK

Installation instructions for the above are readily available online.

**Running via Maven**

- Clone the repo to your development machine
- cd into the project folder once cloned
- mvn spring-boot:run

Once running, you can interact with the local server via localhost:8080

## API Docs

Detailed API Docs is provided by Swagger - available locally at http://localhost:8080/swagger-ui/index.html when the
app is running

## Monitoring

Micrometer has been included in the application, and a prometheus scraper endpoint exposed to allow observation of the
app.

Scraper Endpoint: http://localhost:8080/actuator/prometheus

# Payment API

Rest API using Java, Spring Boot and H2.

Application address 
`http://localhost:8080`

H2 console ``http://localhost:8080/h2-console``

### Running 
Before running - Maven and Java JDK should be installed

In cmd go to project location and enter
`mvn spring-boot:run`

###Available endpoints
- /payments [POST] accepts JSON data format
- /payment-files [POST] accepts CSV file 
- /payments [GET] gets List<Payments> from database
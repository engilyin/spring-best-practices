# RESTful API basic sample

This sample demonstrates:

- API generated from the OpenAPI specification
- JWT based token security
- JPA data layer
- In-Memory DB
- Integration tests
    - API tests
    - JPA tests

## Security

You'll need to generate unique JWT secret to set `jwt.secret` parameter.
You can run junit test `/rest-backend/src/integrationTest/java/com/engilyin/bestpractices/rest/utils/JwtTest.java` for this purpose.

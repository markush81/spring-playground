# Spring Playground

## Purpose

Having a project, always ready, as starting point for a Spring (Boot) Application or just a playground to try something [Spring Framework](https://spring.io) related.

## Features

* Spring Boot Graceful Shutdown [Spring Boot Issue 4657](https://github.com/spring-projects/spring-boot/issues/4657).
* Configure DispatcherServlet for `loadOnStartup`: do not delay first request with initialization.
* Custom ErrorController (`sample.service.error.CustomErrorHandler`): replace whitelabel error page.
* Custom ErrorAdvice (`sample.service.error.CustomErrorAdvice`): handle your exception as you need.
* RestController Integration Test (`ApplicationIntegrationTest`) example.

## Notes

* http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

### Happy coding!

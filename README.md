# Spring Playground

## Purpose

Having a project, always ready, as starting point for a Spring (Boot) Application or just a playground to try something [Spring Framework](https://spring.io) related.

## Features

* Configure DispatcherServlet for `loadOnStartup`, so no delays on first call consumed (`sample.Application.initializeDispatcherServlet`).
** replaced by `spring.mvc.servlet.load-on-startup: 1`
* Custom ErrorController (`sample.service.ErrorHandler`)
* Spring Boot 1.5.x: in case of "Application Failed to start" the process itself does not exit, therefore added `ApplicationFailedEvent` listener to call `System.exit(-1)` in this case.
* Handle specific exceptions in `@RestController` with custom response (`sample.service.Greeting.handleException`).
* RestController Integration Test (`sample.ApplicationIntegrationTest`).
* ... more to come!


## Notes

* http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html

### Happy coding!
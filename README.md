# Spring Playground

## Purpose

Having a project always ready as starting point for a spring boot application or just a playground to try something.

## Features

* Configure DispatcherServlet for loadOnStartup, so no delays on first call consumed (`ample.Application.initializeDispatcherServlet`).
* Custom ErrorController (`sample.service.ErrorHandler`)
* Handle specific exceptions in `@RestController` with custom response (`sample.service.RestService.handleException`).
* RestController Integration Test (`sample.ApplicationIntegrationTest`).
* ... more to come!

### Happy coding!
# Spring Playground

## Purpose

Having a project, always ready, as starting point for a Spring (Boot) Application or just a playground to try something [Spring Framework](https://spring.io) related.

## Features

* Configure DispatcherServlet for `loadOnStartup`: do not delay first request with initialization.
* Custom ErrorController (`sample.service.error.CustomErrorHandler`): replace whitelabel error page.
* Custom ErrorAdvice (`sample.service.error.CustomErrorAdvice`): handle your exception as you need.
* RestController Integration Test (`ApplicationIntegrationTest`) example.
* Spring `bootBuildImage` example with explanation how to add custom CA(s).
* Spring Kafka Reset Offset on-demand (activate with `spring.profiles.active=kafka`)

## `bootBuildImage`

```bash
./gradlew bootBuildImage
```

### Custom CA(s) at Runtime

Add your CA(s) as PEM to `bindings/ca-certificates`.

Run the containern with `--env SERVICE_BINDING_ROOT=/bindings --volume "$(pwd)/bindings/ca-certificates:/bindings/ca-certificates"`

```bash
docker container run --rm --name spring-playground --env SERVICE_BINDING_ROOT=/bindings --volume "$(pwd)/bindings/ca-certificates:/bindings/ca-certificates" -p 8080:8080 spring-playground 
```
In output - at the very beginning - sth. like 

```bash
Added 1 additional CA certificate(s) to system truststore
```

should be seen, now get into container:


```bash
docker container exec -ti spring-playground bash
```

you can check truststore content:

```bash
/layers/paketo-buildpacks_bellsoft-liberica/jre/bin/keytool -list -cacerts
# Passwort: changeit
```

You will find one which has an alias starting with `/tmp/ca-certificates....` which is yours.

```
cnb@54a0707470ca:/$ /layers/paketo-buildpacks_bellsoft-liberica/jre/bin/keytool -list -cacerts -alias /tmp/ca-certificates979321829/954ac54b.0-0
Enter keystore password:
/tmp/ca-certificates979321829/954ac54b.0-0, Jan 1, 1980, trustedCertEntry,
Certificate fingerprint (SHA-256): CA:DB:CB:FC:DA:10:FB:36:7D:1F:7F:9D:7C:BF:EF:E7:16:AB:CC:20:9C:B5:B4:DE:62:7E:12:92:50:EF:E6:4E
```

## Notes

* [http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html](http://docs.spring.io/spring-boot/docs/current/reference/html/common-application-properties.html)
* [https://paketo.io/docs/buildpacks/language-family-buildpacks/java/](https://paketo.io/docs/buildpacks/language-family-buildpacks/java/)

### Happy coding!

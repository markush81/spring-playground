# Basic Project Setup

## Purpose

Setting up a new project is always doing the same things.

1. Creating a build.gradle
2. Setting up integrationTest configuration
3. Adding standard dependencies
4. ... and so on.

Therefore i share my basic project and keep a copy for myself here at github.com.

## Configuration

```groovy

apply plugin: 'java'
apply plugin: 'idea'

// === Repository config ===

repositories {
    mavenCentral()
}

// === JAVA configuration ===

//noinspection GroovyAssignabilityCheck
compileJava {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

// === Integration Test Configuration ===

//noinspection GroovyAssignabilityCheck
sourceSets {
    integrationTest {
        java {
            compileClasspath += main.output
            runtimeClasspath += main.output
        }
    }
}

//noinspection GroovyAssignabilityCheck
configurations {
    integrationTestCompile.extendsFrom testCompile
    integrationTestRuntime.extendsFrom testRuntime
}

task integrationTest(type: Test) {
    testClassesDir = sourceSets.integrationTest.output.classesDir
    classpath = sourceSets.integrationTest.runtimeClasspath
}
check.dependsOn integrationTest
integrationTest.mustRunAfter test

tasks.withType(Test) {
    reports.html.destination = file("${reporting.baseDir}/${name}")
}

// === Dependencies ===

dependencies {
    //logging
    compile 'org.slf4j:slf4j-api:1.7.21'
    compile 'ch.qos.logback:logback-classic:1.1.7'

    //testing
    testCompile 'junit:junit:4.12'
    testCompile 'org.hamcrest:hamcrest-library:1.3'

    //integration testing
    integrationTestCompile 'com.h2database:h2:1.4.191'
}

// === Wrapper generation ===

task wrapper(type: Wrapper) {
    gradleVersion = '2.13'
}

```

## HowTo

If you want to use this as your starting point:

1. Clone the project `git clone  https://github.com/markush81/basic-project.git yourprojectname`
2. Remove git remote reference `git remote rm origin`
3. Add your own origin as you need

**Happy coding!**
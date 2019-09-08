# Tax-Calc API
This is a simple api that makes use of the tax calculation api provided by "taxee.io". Credit goes to those individuals who maintain that great, simple to use web api! 

## Dependencies

Spring Framework, Spring Web, Gradle, javax.json-api, glassfish.javax.json

## Running

The project is built with Gradle, so to access a war you'd need to install gradle to your system and run this command at the root of the project folder:

```
gradle build
```
The war should should show up at 'build/libs/*.war'. The exact name is subject to change, but there should only be one war file.
You can either deploy this to a server, or build to a development Tomcat server with gradle by running:
```
gradle bootRun
```
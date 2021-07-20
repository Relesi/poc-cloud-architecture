

## Integrated Guide Poc Cloud Architecture

Biometric system API with Java and Spring Boot.
#### RESTful API Details
The RESTful Modernization Biometric API contains the following features:
* Project created with Spring Boot and Java 8
* MySQL database with JPA and Spring Data JPA
* Authentication and authorization with Spring Security and JWT (JSON Web Token) tokens
* Database migration with Flyway
* Unit tests and integration with JUnit and Mockito
* Caching with EhCache
* Continuous integration with TravisCI
#### How to run the application
Be sure to have Maven installed and added to the PATH of your operating system, just like Git.

### Travis Setup

* Add to your `.travis.yml` file.
```yml
language: java
after_success:
  -language: java

matrix:
  include:
    - os: linux
      sudo: required
      jdk: openjdk11
    - os: linux
      sudo: required
      jdk: openjdk12

script: ./gradlew check --info --stacktrace --console=plain --max-workers=1 --no-daemon --build-cache -Dkotlin.colors.enabled=false

before_cache:
  - rm -f  $HOME/.gradle/caches/modules-2/modules-2.lock
  - rm -fr $HOME/.gradle/caches/*/plugin-resolution/

cache:
  directories:
    - $HOME/.gradle/caches/
    - $HOME/.gradle/wrapper/
  

```
Private Repos
- Add to your `.travis.yml` file.



# springai-openai-hello-world
A spring-ai powered spring-boot microservice accessing OpenAI's api

## Technologies used
1. spring-boot: '3.2.1'
2. spring-ai: 0.8.0-SNAPSHOT
3. gradle: 8.5
4. java: 21
5. openai: API_KEY obtained from https://platform.openai.com/api-keys

 

This spring-boot api example is OpenAI client application by using [Spring-AI](https://docs.spring.io/spring-ai/reference/index.html). 
<b>The Spring AI project aims to streamline the development of applications that incorporate artificial intelligence functionality without unnecessary complexity. </b>

As mentioned above, the objective of project <b>Spring AI</b> is to focus on enabling smooth AI integration and to do 
that spring team has created a library <u>spring-ai-openai-spring-boot-starter</u>. The library does not have a release
version yet so a snapshot version can be used for now to play around. As of today (28/12/2023), the latest library version 
is 0.8.0-SNAPSOT.
Following steps can be used to smoothly integrate AI(OpenAI) in a spring-boot application.


Step 1: Create a spring boot application

Create a spring-boot application using https://start.spring.io/ (spring initializer) with only <u>spring-boot-starter-web</u> dependency to begin with


Step 2: Add spring-ai's openai starter dependency 

```groovy 
implementation 'org.springframework.ai:spring-ai-openai-spring-boot-starter:0.8.0-SNAPSHOT' 
```

As this library is still not released, the snapshots repository needs to be added to build.gradle to access snapshot versions.

```groovy
repositories {
	mavenCentral()
	maven { url 'https://repo.spring.io/snapshot' }
}
```

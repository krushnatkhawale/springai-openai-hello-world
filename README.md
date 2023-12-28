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

## Steps for SpringAI and OpenAI integration
Following steps can be used to smoothly integrate AI(OpenAI) in a spring-boot application.


#### Step 1: Create a spring boot application

Create a spring-boot application using https://start.spring.io/ (spring initializer) with only <u>spring-boot-starter-web</u> dependency to begin with


#### Step 2: Add spring-ai's openai starter dependency 

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

#### Step 3: Using org.springframework.ai.chat.ChatClient to interact with OpenAi api

The interface org.springframework.ai.chat.ChatClient is the base for all currently supported spring-ai implementations such as OpenAI, 
Azure OpenAI, Hugging Face and Ollama. Planned implementations are Amazon Bedrock,Google Vertex: 'Bard'. 

Currently, [OpenAiChatClient](https://github.com/spring-projects/spring-ai/blob/23e5c9ff7866075a48c13685dcc388e7851eb9db/models/spring-ai-openai/src/main/java/org/springframework/ai/openai/client/OpenAiChatClient.java#L62) which is OpenAI implementation of ChatClient enables communication with <b>gpt-3.5-turbo</b>.

In this example, ChatClient is used in a simplest Rest Controller as below

```java
package com.practice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ai.chat.ChatClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    
    private final ChatClient chatClient;
    
    public Controller(ChatClient chatClient) {
        this.chatClient = chatClient;
    }
    @GetMapping("greeting")
    String greeting(){
        LOGGER.info("Greeting request received");
        final String aiClientResponse = chatClient.generate(
                """
                As a modern generative AI model,
                Generate a 5 liner greeting message in your language style for a human in text form.
                Thanks in advance.     
                """
        );
        LOGGER.info("AI Response: {}", aiClientResponse);
        return aiClientResponse;
    }
}
```

#### Step 4: Configure API key to enable OpenAI api access

The last step to enable this spring-boot app and OpenAI api communication is to add API to configuration file
`application.properties` as shown below.

```properties
spring.ai.openai.api-key=${YOUR_OPENAI_API_KEY}
```
Remember not to add your key in config file, instead store it in your environments variable using below command, and 
it will be automatically accessed by spring-boot.

```shell
export YOUR_OPENAI_API_KEY=<INSERT YOUR KEY HERE>
```

With these steps, your application is ready to talk to OpenAI using spring-ai library.
Run the application and hit url <u>http://localhost:8080/greeting</u> using a browser or curl command.

The response to above request would be a response from OpenAI api in the requested form.

Time spent on this repo:      [![wakatime](https://wakatime.com/badge/user/c0c95904-b67a-4a62-bb09-8d5a5255068b/project/018c8690-4de0-4a98-96a9-d7efc0be0195.svg)](https://wakatime.com/badge/user/c0c95904-b67a-4a62-bb09-8d5a5255068b/project/018c8690-4de0-4a98-96a9-d7efc0be0195)
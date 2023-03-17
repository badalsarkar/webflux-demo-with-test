package ca.badalsarkar.webfluxdemowithtest;

import org.springframework.web.reactive.function.client.WebClient;

public class ExternalApiCaller {
    public <T> T call(Config config, Class<T> returnType){
       var client = WebClient.create(config.uri());
       var result = client.get()
               .uri(config.uri())
               .retrieve()
               .bodyToMono(returnType).block();
       return result;
    }
}

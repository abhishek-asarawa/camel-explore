package com.jss.camel.components.rest;

import com.jss.camel.dto.GreetingDto;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
// import org.apache.camel.util.json.JsonObject;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class RestJavaDsl extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("rest:get:/")
                .process(this::greetings);

        from("rest:get:/data")
                .setBody(simple("${null}"))
                .toD("https://jsonplaceholder.typicode.com/posts" + "?httpMethod=GET&bridgeEndpoint=true");
    }

    private void greetings(Exchange exchange) {
        Message message = new DefaultMessage(exchange.getContext());
        GreetingDto greetingMessage = new GreetingDto();
        greetingMessage.setMessage("hello from dev");
        message.setBody(greetingMessage.getMessage());
        exchange.setMessage(message);
    }

    // private void jsonData(Exchange exchange) {
    // Message message = new DefaultMessage(exchange.getContext());
    // JsonObject data = message.getBody(JsonObject.class);
    // message.setBody(data);
    // exchange.setMessage(message);
    // }

}

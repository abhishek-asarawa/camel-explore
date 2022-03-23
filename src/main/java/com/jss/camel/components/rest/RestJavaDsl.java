package com.jss.camel.components.rest;

import com.jss.camel.dto.GreetingDto;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;

@Component
public class RestJavaDsl extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        from("rest:get:/")
                .process(this::greetings);
    }

    private void greetings(Exchange exchange) {
        Message message = new DefaultMessage(exchange.getContext());
        GreetingDto greetingMessage = new GreetingDto();
        greetingMessage.setMessage("hello from dev");
        message.setBody(greetingMessage.getMessage());
        exchange.setMessage(message);
    }

}

package com.jss.camel.components.rest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.apache.camel.support.DefaultMessage;
// import org.apache.camel.support.DefaultMessage;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

// import static org.apache.camel.model.rest.RestParamType.body;

import java.util.ArrayList;

import com.jss.camel.dto.PrescriberDto;
// import com.jss.camel.dto.PrescriberDto;
import com.jss.camel.dto.PrescribersDto;

import org.apache.camel.Exchange;
// import org.apache.camel.Message;
import org.apache.camel.Message;

@Component
@RestController
public class PrescriberDsl extends RouteBuilder {

    private PrescribersDto prescribers = new PrescribersDto();

    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.auto);

        rest()
                .post("prescriber/add")
                .type(PrescriberDto.class)
                .to("direct:save-prescriber")
                .get("prescribers")
                .to("direct:all-prescriber");

        from("direct:save-prescriber")
                .process(this::savePrescriber);

        from("direct:all-prescriber")
                .process(this::getPrescribers);
    }

    public void savePrescriber(Exchange exchange) {
        try {
            PrescriberDto body = exchange.getMessage().getBody(PrescriberDto.class);
            prescribers.addPrescriber(body);
        } catch (Exception e) {
            log.error("Error while saving prescriber", e);
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
        }
    }

    public void getPrescribers(Exchange exchange) {
        try {
            ArrayList<PrescriberDto> allPres = prescribers.getAllPrescribersInJson();
            Message message = new DefaultMessage(exchange.getContext());
            message.setBody(allPres);
            exchange.setMessage(message);

        } catch (Exception e) {
            log.error("Error while saving prescriber", e);
            exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, 500);
        }
    }

}

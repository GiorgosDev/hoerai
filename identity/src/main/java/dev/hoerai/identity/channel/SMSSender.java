package dev.hoerai.identity.channel;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import static org.springframework.boot.logging.log4j2.Log4J2LoggingSystem.getEnvironment;


@Component
public class SMSSender {

    @Value("${sender.sms.sid}")
    private String accountSID;
    @Value("${sender.sms.token}")
    private String accountToken;
    @Value("${sender.sms.phone}")
    private String accountPhone;


    private TwilioRestClient client;

    public void send(String to, String message) {
        if(client == null){
            this.client = new TwilioRestClient.Builder(accountSID, accountToken).build();
        }
        new MessageCreator(
                new PhoneNumber(to),
                new PhoneNumber(accountPhone),
                message
        ).create(client);
    }



}



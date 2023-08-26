package dev.hoerai.identity.controller;

import dev.hoerai.identity.channel.SMSSender;
import dev.hoerai.identity.entity.RegistrationDTO;
import dev.hoerai.identity.entity.ResponseMessage;
import dev.hoerai.identity.service.IDService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static dev.hoerai.identity.Constants.*;


@Slf4j

@RestController
@Secured(APP_ROLE)
public class RegistrationController {

    private IDService idService;

    public RegistrationController(IDService idService) {
        this.idService = idService;
    }

    @PostMapping(path = "/register")
    @ResponseBody
    public ResponseEntity<ResponseMessage> register( @RequestBody RegistrationDTO registration) {

        idService.register(registration.getId(), registration.getPhone());
        return new ResponseEntity<>(new ResponseMessage(STATUS_OK, MESSAGE_OK, ""), HttpStatus.OK);

    }

    public IDService getIdService() {
        return idService;
    }

    public void setIdService(IDService idService) {
        this.idService = idService;
    }
}

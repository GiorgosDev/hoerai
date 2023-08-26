package dev.hoerai.identity.controller;

import dev.hoerai.identity.channel.SMSSender;
import dev.hoerai.identity.entity.AlarmDTO;
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
@Secured(SENSOR_ROLE)
public class AlarmController {

    private IDService idService;
    private SMSSender sender;

    public AlarmController(SMSSender sender, IDService idService) {
        this.sender = sender;
        this.idService = idService;
    }

    @GetMapping(path = "/reportAlarm/{type}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> reportAlarm( @PathVariable String type) {

        sender.send("+41762629538","Alarm received");

        return new ResponseEntity<>(new ResponseMessage(STATUS_OK, MESSAGE_OK, type), HttpStatus.OK);
    }

    @PostMapping(path = "/alarm")
    @ResponseBody
    public ResponseEntity<ResponseMessage> reportAlarm( @RequestBody AlarmDTO alarm) {
        idService.getPhones(alarm.getId()).forEach( phone -> sender.send(phone,"Alarm received"));
        return new ResponseEntity<>(new ResponseMessage(STATUS_OK, MESSAGE_OK, ""), HttpStatus.OK);
    }


    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }


}

package dev.hoerai.identity.controller;

import dev.hoerai.identity.entity.ResponseMessage;
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

    @GetMapping(path = "/reportAlarm/{type}")
    @ResponseBody
    public ResponseEntity<ResponseMessage> reportAlarm( @PathVariable String type) {

        return new ResponseEntity<>(new ResponseMessage(STATUS_OK, MESSAGE_OK, type), HttpStatus.OK);
    }

    @PostMapping("/test-request")
    public ResponseEntity<String> testPostRequest() {
        return ResponseEntity.ok("POST request successful");
    }


}
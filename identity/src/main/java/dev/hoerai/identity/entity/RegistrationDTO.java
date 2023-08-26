package dev.hoerai.identity.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RegistrationDTO {
    @JsonProperty("phone")
    String phone;
    @JsonProperty("id")
    String id;
}

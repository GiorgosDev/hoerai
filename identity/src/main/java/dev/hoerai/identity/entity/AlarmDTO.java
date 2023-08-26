package dev.hoerai.identity.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AlarmDTO {
    @JsonProperty("id")
    String id;
    @JsonProperty("type")
    String type;
}

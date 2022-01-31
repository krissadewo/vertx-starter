package id.or.greenlabs.vertx.starter.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.LinkedHashMap;

/**
 * @author krissadewo
 * @date 1/19/22 1:44 PM
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@NoArgsConstructor
public class Environment {

    @JsonProperty("active.profile")
    private String activeProfile;

    @JsonProperty("service.name")
    private String serviceName;

    @JsonProperty("service.port")
    private int servicePort;

    @JsonProperty("kafka")
    private LinkedHashMap<String, String> kafka;

    @JsonProperty("client")
    private LinkedHashMap<String, LinkedHashMap<String, String>> client;

    @JsonProperty("mongodb")
    private LinkedHashMap<String, String> mongodb;

    @JsonProperty("jaeger")
    private LinkedHashMap<String, String> jaeger;
}

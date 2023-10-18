package Infrastructure.BackEnd.Model.WeatherResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MainClass {
    @JsonProperty("temp")
    public double temp;
    @JsonProperty("feels_like")
    public double feels_like;
    @JsonProperty("temp_min")
    public double temp_min;
    @JsonProperty("temp_max")
    public double temp_max;
    @JsonProperty("pressure")
    public int pressure;
    @JsonProperty("humidity")
    public int humidity;
}

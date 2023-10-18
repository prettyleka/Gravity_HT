package Infrastructure.BackEnd.Model.WeatherResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Sys {
    @JsonProperty("type")
    public int type;
    @JsonProperty("id")
    public int id;
    @JsonProperty("country")
    public String country;
    @JsonProperty("sunrise")
    public int sunrise;
    @JsonProperty("sunset")
    public int sunset;
}

package Infrastructure.BackEnd.Model.WeatherResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    @JsonProperty("coord")
    public Coord coord;
    @JsonProperty("weather")
    public List<Weather> weather;
    @JsonProperty("base")
    public String base;
    @JsonProperty("main")
    public MainClass main;
    @JsonProperty("visibility")
    public int visibility;
    @JsonProperty("wind")
    public Wind wind;
    @JsonProperty("clouds")
    public Clouds clouds;
    @JsonProperty("dt")
    public int dt;
    @JsonProperty("sys")
    public Sys sys;
    @JsonProperty("timezone")
    public int timezone;
    @JsonProperty("id")
    public int id;
    @JsonProperty("name")
    public String name;
    @JsonProperty("cod")
    public int cod;
}

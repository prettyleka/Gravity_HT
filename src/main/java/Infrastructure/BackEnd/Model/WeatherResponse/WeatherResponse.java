package Infrastructure.BackEnd.Model.WeatherResponse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class AddBookRequest {
    String userId;
    List<Isbns> collectionOfIsbns;

    public static AddBookRequest defaultValue(String userId, String isbn){
        return AddBookRequest.builder()
                .userId(userId)
                .collectionOfIsbns(List.of(Isbns.builder().isbn(isbn).build()))
                .build();
    }

}

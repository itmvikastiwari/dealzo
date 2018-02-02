package dealzo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealFilterResponse {

    Set<String> productType;
    Set<String> categoryName;
    Set<String> subcategoryName;
    Set<String> brandName;
    Set<String> modelName;
    Set<String> dealType;

}

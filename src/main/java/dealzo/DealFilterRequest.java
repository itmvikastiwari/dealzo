package dealzo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealFilterRequest {
    String productType;
    String categoryName;
    String subcategoryName;
    Long startTime;
    Long endTime;
    String dealType;
    String brandName;
    Double startPrice;
    Double endPrice;
}

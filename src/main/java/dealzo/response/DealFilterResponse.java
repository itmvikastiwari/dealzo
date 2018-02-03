package dealzo.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealFilterResponse {
    private String id;

    private String categoryName;
    private String subcategoryName;


    private String title;


    private String productDescription;


    private Long startTime;

    private Long endTime;


    private String sellerName;


    private String email;

    String mobileNo;


    String brandName;


    String modelName;


    List<String> imageUrl;


    Integer quantity;


    String dealType;


    Double sellerPrice;


    Double discountPrice;


    String productType;

    String status;


}

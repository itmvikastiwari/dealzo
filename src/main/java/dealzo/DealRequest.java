package dealzo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class DealRequest {
    private static final long serialVersionUID = 1L;


    private String productType;


    private String categoryName;

    private String subcategoryName;


    private String description;


    private Long startTime;


    private Long endTime;


    private String sellerName;

    private String email;

    private String mobileNo;

    private String brandName;

    private String modelNo;

    private String title;

    private List<String> imageLinks;

    private Integer quantity;

    private String dealType;


}

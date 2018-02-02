package dealzo;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateDealRequest {
    private static final long serialVersionUID = 1L;


    private String productType;


    private String categoryName;

    private String subcategoryName;


    private String productTypeName;


    private String productDescription;


    private Long startTime;


    private Long endTime;


    private String userName;

    private String email;

    private String mobileNo;

    String brandName;

    String modelNo;

    String title;
}

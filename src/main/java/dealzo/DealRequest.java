package dealzo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import dealzo.document.Deal;
import dealzo.enums.ErrorCode;
import dealzo.exception.DealzoException;
import dealzo.util.Utils;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.sql.Timestamp;
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

    private String modelName;

    private String title;

    private List<String> imageLinks;

    private Integer quantity;

    private String dealType;

    private Double sellerPrice;

    private Double discountPrice;


    public static Deal from(DealRequest dealRequest) {
        validate(dealRequest);
        return Deal.builder()
                .categoryName(dealRequest.getCategoryName())
                .subcategoryName(dealRequest.getSubcategoryName())
                .productDescription(dealRequest.getDescription())
                .startTime(new Timestamp(dealRequest.getStartTime()*1000))
                .endTime(new Timestamp(dealRequest.getEndTime()*1000))
                .sellerName(dealRequest.getSellerName())
                .email(dealRequest.getEmail())
                .mobileNo(dealRequest.getMobileNo())
                .brandName(dealRequest.getBrandName())
                .modelName(dealRequest.getModelName())
                .title(dealRequest.getTitle())
                .sellerPrice(dealRequest.getSellerPrice())
                .discountPrice(dealRequest.getDiscountPrice())
                .dealType(dealRequest.getDealType())
                .imageUrl(dealRequest.getImageLinks())
                .quantity(dealRequest.getQuantity() == null ? 1 : dealRequest.getQuantity())
                .build();
    }

    private static void validate(DealRequest dealRequest) {
        if(null == dealRequest.getStartTime()){
            throw new DealzoException("Invalid start time", ErrorCode.BAD_REQUEST);
        }
        if(null == dealRequest.getEndTime()){
            throw new DealzoException("Invalid end time", ErrorCode.BAD_REQUEST);
        }
        Timestamp startTime = new Timestamp(dealRequest.getStartTime()*1000);
        Timestamp endTime = new Timestamp(dealRequest.getEndTime()*1000);

        if(endTime.before(Utils.getCurrentTimestamp())){
            throw new DealzoException("End time should be in future", ErrorCode.BAD_REQUEST);
        }
        if(startTime.after(endTime)){
            throw new DealzoException("End time should be after start time", ErrorCode.BAD_REQUEST);
        }

        if(StringUtils.isEmpty(dealRequest.getSubcategoryName())){
            throw new DealzoException("Invalid Sub category name", ErrorCode.BAD_REQUEST);
        }
        if(StringUtils.isEmpty(dealRequest.getCategoryName())){
            throw new DealzoException("Invalid category name", ErrorCode.BAD_REQUEST);
        }
        if(StringUtils.isEmpty(dealRequest.getTitle())){
            throw new DealzoException("Invalid Title", ErrorCode.BAD_REQUEST);
        }
    }
}

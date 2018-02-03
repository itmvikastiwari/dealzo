package dealzo.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;
import java.util.List;

@Document(collection = "deal")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Deal extends BaseDocument {

    private static final long serialVersionUID = 1L;

    @Field("category_name")
    private String categoryName;

    @Field("subcategory_name")
    private String subcategoryName;

    @Field("title")
    private String title;

    @Field("product_description")
    private String productDescription;

    @Field("start_time")
    private Date startTime;

    @Field("end_time")
    private Date endTime;

    @Field("seller_name")
    private String sellerName;

    @Field("email")
    private String email;

    @Field("mobile_no")
     String mobileNo;

    @Field("brand_name")
    String brandName;

    @Field("model_name")
    String modelName;

    @Field("image_link")
    List<String> imageUrl;

    @Field("quantity")
    Integer quantity;

    @Field("deal_type")
    String dealType;

    @Field("selling_price")
    Double sellerPrice;

    @Field("discount_price")
    Double discountPrice;

    @Field("product_type")
    String productType;

}
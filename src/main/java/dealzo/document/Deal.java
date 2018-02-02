package dealzo.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import javax.persistence.Id;
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

    @Field("product_description")
    private String productDescription;

    @Field("start_time")
    private Date startTime;

    @Field("end_time")
    private Date endTime;

    @Field("user_name")
    private String userName;

    @Field("email")
    private String email;

    @Field("mobile_no")
    private String mobileNo;

    @Field("brand_name")
    String brandName;

    @Field("model_no")
    String modelNo;

    @Field("image_line")
    List<String> imageUrl;

}
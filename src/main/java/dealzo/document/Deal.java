package dealzo.document;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "deal")
public class Deal extends BaseDocument {

    private static final long serialVersionUID = 1L;

    @Field("category_id")
    private Integer categoryId;

    @Field("subcategory_id")
    private Integer subcategoryId;

    @Field("product_type")
    private Integer productType;

    @Field("category_name")
    private String categoryName;

    @Field("subcategory_name")
    private String subcategoryName;

    @Field("product_type_name")
    private String productTypeName;

    @Field("current_version_id")
    private String currentVersionId;

}
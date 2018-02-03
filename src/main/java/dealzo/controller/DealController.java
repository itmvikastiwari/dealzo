package dealzo.controller;

import com.mongodb.*;
import dealzo.DealFilterRequest;
import dealzo.DealRequest;
import dealzo.document.Deal;
import dealzo.repository.DealRepository;
import dealzo.response.DealzoResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/deals/v1")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> submitDeal(@RequestBody DealRequest dealRequest) {
        Deal deal = DealRequest.from(dealRequest);
       deal= dealRepository.save(deal);
        return DealzoResponseEntity.buildSuccessResponse(deal);
    }

    @RequestMapping(value = "fetch", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchDeal(@RequestBody DealFilterRequest dealFilterRequest) {
        if (dealFilterRequest.getStartTime() == null || dealFilterRequest.getEndTime() == null) {
            throw new IllegalArgumentException("startTime or endTime can not be null");
        }
        MongoClient mongoClient = new MongoClient("192.168.124.33", 27019);
        DB db = mongoClient.getDB("dealzo_db");

        DBCollection collection = db.getCollection("deal");
        ;
        BasicDBObject query = new BasicDBObject();
        query.put("startTime", new BasicDBObject("$gte", new Timestamp(dealFilterRequest.getStartTime()*1000)));
        query.put("endTime", new BasicDBObject("$lte", new Timestamp(dealFilterRequest.getEndTime()*1000)));
        if (dealFilterRequest.getBrandName() != null) {
            query.put("brandName", dealFilterRequest.getBrandName());
        }
        if (dealFilterRequest.getCategoryName() != null) {
            query.put("categoryName", dealFilterRequest.getCategoryName());
        }
        if (dealFilterRequest.getDealType() != null) {
            query.put("dealType", dealFilterRequest.getDealType());
        }
        if (dealFilterRequest.getSubcategoryName() != null) {
            query.put("categoryName", dealFilterRequest.getCategoryName());
        }
        if (dealFilterRequest.getProductType() != null) {
            query.put("productType", dealFilterRequest.getProductType());
        }
        if (dealFilterRequest.getStartPrice() != null && dealFilterRequest.getEndTime() != null) {
            query.put("discountPrice", BasicDBObjectBuilder.start("$gte", dealFilterRequest.getStartPrice()).add("$lte", dealFilterRequest.getEndPrice()).get());
        }

        DBCursor dbCursor = collection.find(query);
        List<Deal> dealList = new ArrayList<>();
        dbCursor.forEach(dbObject ->
                Deal.builder()
                        .quantity((Integer) dbObject.get("quantity"))
                        .mobileNo((String) dbObject.get("mobileNo"))
                        .brandName((String) dbObject.get("brandName"))
                        .email((String) dbObject.get("email"))
                        .productDescription((String) dbObject.get("productDescription"))
                        .subcategoryName((String) dbObject.get("subcategoryName"))
                        .categoryName((String) dbObject.get("categoryName"))
                        .endTime((Timestamp) dbObject.get("endTime"))
                        .startTime((Timestamp) dbObject.get("startTime"))
                        .imageUrl((List<String>) dbObject.get("imageUrl"))
                        .title((String) dbObject.get("title"))
                        .sellerPrice((Double) dbObject.get("sellerPrice"))
                        .discountPrice((Double) dbObject.get("discountPrice"))
                        .dealType((String) dbObject.get("dealType"))
                        .build());
        return DealzoResponseEntity.buildSuccessResponse(null);
    }
}

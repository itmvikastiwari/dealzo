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
        MongoClient mongoClient = new MongoClient("192.168.124.33", 27019);
        DB db = mongoClient.getDB("dealzo_db");

        DBCollection collection = db.getCollection("deal");
        ;
        BasicDBObject query = new BasicDBObject();
//        query.put("startTime", new BasicDBObject("$gte", new Timestamp(dealFilterRequest.getStartTime()*1000)));
//        query.put("endTime", new BasicDBObject("$lte", new Timestamp(dealFilterRequest.getEndTime()*1000)));
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
        return null;
    }

    @RequestMapping(value = "fetch2", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchDeal2(@RequestBody DealFilterRequest dealFilterRequest){
        return DealzoResponseEntity.buildSuccessResponse(dealRepository.findAll());
    }

}

package dealzo.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
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

@Controller
@RequestMapping("/deals/v1")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> submitDeal(@RequestBody DealRequest dealRequest) {
        Deal deal = Deal.builder()
                .categoryName(dealRequest.getCategoryName())
                .subcategoryName(dealRequest.getSubcategoryName())
                .productDescription(dealRequest.getDescription())
                .startTime(new Timestamp(dealRequest.getStartTime()*1000))
                .endTime(new Timestamp(dealRequest.getEndTime()*1000))
                .sellerName(dealRequest.getSellerName())
                .email(dealRequest.getEmail())
                .mobileNo(dealRequest.getMobileNo())
                .brandName(dealRequest.getBrandName())
                .modelName(dealRequest.getModelNo())
                .quantity(dealRequest.getQuantity())
                .build();
        dealRepository.save(deal);
        return DealzoResponseEntity.buildSuccessResponse(null);
    }

    @RequestMapping(value = "fetch",method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchDeal(@RequestBody DealRequest dealRequest){
        if(dealRequest.getStartTime()==null || dealRequest.getEndTime()==null){
            throw new IllegalArgumentException("startTime or endTime can not be null");
        }
        BasicDBObject query=new BasicDBObject();
        query.put("startTime", BasicDBObjectBuilder.start("$gte",new Timestamp(dealRequest.getStartTime())));

        return DealzoResponseEntity.buildSuccessResponse(null);
    }
}

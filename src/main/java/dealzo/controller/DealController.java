package dealzo.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
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

@Controller
@RequestMapping("/deals/v1")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> submitDeal(@RequestBody DealRequest dealRequest) {
        Deal deal = DealRequest.from(dealRequest);
        dealRepository.save(deal);
        return DealzoResponseEntity.buildSuccessResponse(null);
    }

    @RequestMapping(value = "fetch",method = RequestMethod.POST,consumes = {MediaType.APPLICATION_JSON_VALUE},produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchDeal(@RequestBody DealFilterRequest dealFilterRequest){
        if(dealFilterRequest.getStartTime()==null || dealFilterRequest.getEndTime()==null){
            throw new IllegalArgumentException("startTime or endTime can not be null");
        }
        BasicDBObject query=new BasicDBObject();
        query.put("startTime", BasicDBObjectBuilder.start("$gte",new Timestamp(dealFilterRequest.getStartTime())));
        query.put("endTime",BasicDBObjectBuilder.start("$lte",new Timestamp(dealFilterRequest.getEndTime())));

        return DealzoResponseEntity.buildSuccessResponse(null);
    }
}

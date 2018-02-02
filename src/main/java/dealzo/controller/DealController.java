package dealzo.controller;

import dealzo.CreateDealRequest;
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
@RequestMapping("/deals")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> submitDeal(@RequestBody CreateDealRequest createDealRequest) {
        Deal deal = Deal.builder()
                .categoryName(createDealRequest.getCategoryName())
                .subcategoryName(createDealRequest.getSubcategoryName())
                .productDescription(createDealRequest.getDescription())
                .startTime(new Timestamp(createDealRequest.getStartTime()*1000))
                .endTime(new Timestamp(createDealRequest.getEndTime()*1000))
                .userName(createDealRequest.getSellerName())
                .email(createDealRequest.getEmail())
                .mobileNo(createDealRequest.getMobileNo())
                .brandName(createDealRequest.getBrandName())
                .modelNo(createDealRequest.getModelNo())
                .build();
        dealRepository.save(deal);
        return DealzoResponseEntity.buildSuccessResponse(null);
    }
}

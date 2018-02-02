package dealzo.controller;

import dealzo.CreateDealRequest;
import dealzo.document.Deal;
import dealzo.repository.DealRepository;
import dealzo.response.DealzoResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/deals/v1")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> submitDeal(CreateDealRequest createDealRequest) {
        Deal deal = Deal.builder()
                .categoryName(createDealRequest.getCategoryName())
                .subcategoryName(createDealRequest.getSubcategoryName())
                .productDescription(createDealRequest.getProductDescription())
                .startTime(createDealRequest.getStartTime())
                .endTime(createDealRequest.getEndTime())
                .userName(createDealRequest.getUserName())
                .email(createDealRequest.getEmail())
                .mobileNo(createDealRequest.getMobileNo())
                .brandName(createDealRequest.getBrandName())
                .modelNo(createDealRequest.getModelNo())
                .build();
        dealRepository.save(deal);
        return DealzoResponseEntity.buildSuccessResponse(null);
    }
}

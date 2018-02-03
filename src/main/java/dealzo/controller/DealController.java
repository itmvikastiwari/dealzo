package dealzo.controller;

import com.mongodb.*;
import dealzo.DealFilterRequest;
import dealzo.DealRequest;
import dealzo.document.Deal;
import dealzo.repository.DealRepository;
import dealzo.response.DealFilterResponse;
import dealzo.response.DealzoResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/deals/v1")
public class DealController {

    @Autowired
    private DealRepository dealRepository;

    @RequestMapping(value = "create", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> submitDeal(@RequestBody DealRequest dealRequest) {
        Deal deal = DealRequest.from(dealRequest);
        deal = dealRepository.save(deal);
        return DealzoResponseEntity.buildSuccessResponse(deal);
    }

    @RequestMapping(value = "fetch", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchDeal(@RequestBody DealFilterRequest dealFilterRequest) {
        try {
            Deal deal = new Deal();
            if (dealFilterRequest.getDealType() != null) {
                deal.setDealType(dealFilterRequest.getDealType());
            }
            if (dealFilterRequest.getProductType() != null) {
                deal.setProductType(dealFilterRequest.getProductType());
            }
            if (dealFilterRequest.getSubcategoryName() != null) {
                deal.setSubcategoryName(dealFilterRequest.getSubcategoryName());
            }
            if (dealFilterRequest.getBrandName() != null) {
                deal.setBrandName(dealFilterRequest.getBrandName());
            }
            if (dealFilterRequest.getCategoryName() != null) {
                deal.setCategoryName(dealFilterRequest.getCategoryName());
            }
            List<Deal> deals = dealRepository.findAll(Example.of(deal));

            List<DealFilterResponse> dealFilterResponses = null;
            if (deals != null) {
                dealFilterResponses = deals.stream().map(dealType -> DealFilterResponse.builder()
                        .id(dealType.getId())
                        .categoryName(dealType.getCategoryName())
                        .subcategoryName(dealType.getSubcategoryName())
                        .title(dealType.getTitle())
                        .productDescription(dealType.getProductDescription())
                        .startTime(dealType.getStartTime().getTime() / 1000)
                        .endTime(dealType.getEndTime().getTime() / 1000)
                        .sellerName(dealType.getSellerName())
                        .email(dealType.getEmail())
                        .mobileNo(dealType.getMobileNo())
                        .brandName(dealType.getBrandName())
                        .modelName(dealType.getModelName())
                        .imageUrl(dealType.getImageUrl())
                        .quantity(dealType.getQuantity())
                        .dealType(dealType.getDealType())
                        .discountPrice(dealType.getDiscountPrice())
                        .sellerPrice(dealType.getSellerPrice())
                        .status(getDealStatus(dealType.getStartTime(), dealType.getEndTime()))
                        .build()).collect(Collectors.toList());
            }
            return DealzoResponseEntity.buildSuccessResponse(dealFilterResponses);
        } catch (Exception e) {
            List<Deal> deals = dealRepository.findAll();
            List<DealFilterResponse> dealFilterResponses = null;
            if (deals != null) {
                dealFilterResponses = deals.stream().map(dealType -> DealFilterResponse.builder()
                        .id(dealType.getId())
                        .categoryName(dealType.getCategoryName())
                        .subcategoryName(dealType.getSubcategoryName())
                        .title(dealType.getTitle())
                        .productDescription(dealType.getProductDescription())
                        .startTime(dealType.getStartTime().getTime() / 1000)
                        .endTime(dealType.getEndTime().getTime() / 1000)
                        .sellerName(dealType.getSellerName())
                        .email(dealType.getEmail())
                        .mobileNo(dealType.getMobileNo())
                        .brandName(dealType.getBrandName())
                        .modelName(dealType.getModelName())
                        .imageUrl(dealType.getImageUrl())
                        .quantity(dealType.getQuantity())
                        .dealType(dealType.getDealType())
                        .discountPrice(dealType.getDiscountPrice())
                        .sellerPrice(dealType.getSellerPrice())
                        .status(getDealStatus(dealType.getStartTime(), dealType.getEndTime()))
                        .build()).collect(Collectors.toList());
            }
            return DealzoResponseEntity.buildSuccessResponse(dealFilterResponses);
        }

    }

    private String getDealStatus(Date startTime, Date endTime) {
        if (startTime.after(new Date())) {
            return "UPCOMING";
        }
        if (endTime.before(new Date())) {
            return "MISSED";
        }
        return "CURRENT";
    }

    @RequestMapping(value = "fetch2", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> fetchDeal2(@RequestBody DealFilterRequest dealFilterRequest){
        return DealzoResponseEntity.buildSuccessResponse(dealRepository.findAll());
    }

}

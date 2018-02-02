package dealzo.controller;

import dealzo.document.Deal;
import dealzo.repository.DealRepository;
import dealzo.response.DealFilterResponse;
import dealzo.response.DealzoResponseEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping(value ="dealzo/v1")
public class FilterController {

    @Autowired
    private DealRepository dealRepository;

    @RequestMapping(value ="filter", method = RequestMethod.GET)
    @Cacheable
    public DealzoResponseEntity getCategory() {
        List<Deal> deals = dealRepository.findAll();
        Set<String> productType =new HashSet<>();
        Set<String> categoryName =new HashSet<>();
        Set<String> subcategoryName =new HashSet<>();
        Set<String> brandName =new HashSet<>();
        Set<String> modelName =new HashSet<>();
        Set<String> dealType =new HashSet<>();
        for(Deal deal : deals){
            if(StringUtils.isNotEmpty(deal.getProductType())){
                productType.add(deal.getProductType());
            }
            if(StringUtils.isNotEmpty(deal.getCategoryName())){
                categoryName.add(deal.getCategoryName());
            }
            if(StringUtils.isNotEmpty(deal.getSubcategoryName())){
                subcategoryName.add(deal.getSubcategoryName());
            }
            if(StringUtils.isNotEmpty(deal.getBrandName())){
                brandName.add(deal.getBrandName());
            }
            if(StringUtils.isNotEmpty(deal.getModelName())){
                modelName.add(deal.getModelName());
            }
            if(StringUtils.isNotEmpty(deal.getDealType())){
                dealType.add(deal.getDealType());
            }
        }
        DealFilterResponse response = DealFilterResponse.builder()
                .brandName(brandName)
                .categoryName(categoryName)
                .subcategoryName(subcategoryName)
                .dealType(dealType)
                .modelName(modelName)
                .productType(productType)
                .build();
        return DealzoResponseEntity.buildSuccessResponse(response);
    }

}

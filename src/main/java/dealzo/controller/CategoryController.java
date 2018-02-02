package dealzo.controller;

import dealzo.response.DealzoResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping(value ="dealzo/v1")
public class CategoryController {

    private static Set<String> categories = new HashSet<>();

    static {
        categories.add("Home & Lifestyle");
        categories.add("Electronics & Appliances");
        categories.add("Mobiles & Tablets");
    }

    @RequestMapping(value ="category", method = RequestMethod.GET)
    public DealzoResponseEntity getCategory() {
        return DealzoResponseEntity.buildSuccessResponse(categories);
    }

}

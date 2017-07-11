package hello.Controller;

import hello.repositories.CatererDetailsRepository;
import hello.repositories.UserMenuDetailRepository;
import hello.repositories.UserRepositry;
import models.*;
import models.dto.UserFoodItemReport;
import models.dto.UserMenuReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.querydsl.core.types.dsl.BooleanExpression;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by anirudh on 08/07/17.
 */
@RestController
public class MenuFoodController {

    @Autowired
    private CatererDetailsRepository catererDetailsRepository;

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private UserMenuDetailRepository userMenuDetailRepository;

    @RequestMapping("/menuItems")
    public CatererDetailsAndMenu getMenuItems(@RequestParam(required = true) String date){

        if (date == null || date.isEmpty() || !date.contains("-")){
            return null;
        }

        List<CatererDetailsAndMenu> catererDetailsAndMenuList = catererDetailsRepository.findByDate(date);
        if (!catererDetailsAndMenuList.isEmpty()){
            return catererDetailsAndMenuList.get(0);
        } else {
            return null;
        }
    }

    @RequestMapping("/submit/menu")
    @PostMapping
    public boolean submitMenu(@RequestBody(required = true) UserMenuDetail userMenuDetail){

        QUserMenuDetail qUserMenuDetail = new QUserMenuDetail("userMenuDetail");
        BooleanExpression filterByEmpId = qUserMenuDetail.user.empId.eq(userMenuDetail.getUser().getEmpId());
        BooleanExpression filterByDate = qUserMenuDetail.date.eq(userMenuDetail.getDate());

        List<UserMenuDetail> userMenuDetailList = (List<UserMenuDetail>)
                userMenuDetailRepository.findAll(filterByEmpId.and(filterByDate));

        if (userMenuDetailList.size() == 0){
            userMenuDetailRepository.save(userMenuDetail);
        } else {
            userMenuDetail.setId(userMenuDetailList.get(0).getId());
            userMenuDetailRepository.save(userMenuDetail);
        }

        return true;
    }

    @RequestMapping("/getAllUsersMenu")
    public List<UserMenuDetail> getAllUsersMenu(){
        return userMenuDetailRepository.findAll();
    }

    @RequestMapping("/reportsByDate")
    public UserMenuReport reportsByDate(@RequestParam (required = true) String date){
        UserMenuReport userMenuReport = new UserMenuReport();

        QUserMenuDetail qUserMenuDetail = new QUserMenuDetail("userMenuDetail");
        BooleanExpression filterByDate = qUserMenuDetail.date.eq(date);
        BooleanExpression filterByIsTakingFood = qUserMenuDetail.takingFood.eq(true);
        List<UserMenuDetail> usersTakingFoodOnDate = (List<UserMenuDetail>) userMenuDetailRepository.
                findAll(filterByDate.and(filterByIsTakingFood));

        userMenuReport.setNoOfMembersTakingFood(usersTakingFoodOnDate.size());
        userMenuReport.setDate(date);
        userMenuReport.setTotalMembers(userRepositry.findAll().size());

        List<CatererDetailsAndMenu> catererDetailsAndMenuList = catererDetailsRepository.findByDate(date);

        if (catererDetailsAndMenuList.isEmpty()){
            return null;
        }

        CatererDetailsAndMenu catererDetailsAndMenu = catererDetailsAndMenuList.get(0);
        userMenuReport.setVendorName(catererDetailsAndMenu.getVendorName());
        List<FoodItem> totalFoodItemList = catererDetailsAndMenu.getFoodItems();
        List<UserFoodItemReport> userFoodItemReportList = new ArrayList<>();

        for (FoodItem foodItem : totalFoodItemList) {

            UserFoodItemReport userFoodItemReport = new UserFoodItemReport();
            userFoodItemReport.setId(foodItem.getId());
            userFoodItemReport.setName(foodItem.getName());
            userFoodItemReport.setImageUrl(foodItem.getImageUrl());
            userFoodItemReport.setItemType(foodItem.getItemType());
            userFoodItemReport.setRating(foodItem.getRating());

            int count = 0;
            for (UserMenuDetail singleUserTakingFood : usersTakingFoodOnDate){
                for (UserMenuItem userMenuItem : singleUserTakingFood.getUserMenuItems()) {
                    if (userMenuItem.getFoodId().equals(foodItem.getId())){
                        count++;
                    }
                }
            }
            userFoodItemReport.setCount(count);
            userFoodItemReportList.add(userFoodItemReport);
        }
        userMenuReport.setUserFoodItemReportList(userFoodItemReportList);
        return userMenuReport;
    }

}

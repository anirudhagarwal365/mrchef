package hello.Controller;

import hello.repositories.CatererDetailsRepository;
import models.CatererDetailsAndMenu;
import models.FoodItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * Created by anirudh on 08/07/17.
 */
@RestController
public class CatererController {

    @Autowired
    private CatererDetailsRepository catererDetailsRepository;

    @RequestMapping("/addItems")
    @PutMapping
    public boolean addFoodMenu(@RequestBody(required = true) CatererDetailsAndMenu catererDetailsAndMenu){
        for (FoodItem foodItem : catererDetailsAndMenu.getFoodItems()){
            foodItem.setId(UUID.randomUUID().toString());
        }
        List<CatererDetailsAndMenu> catererDetailsAndMenuList =
                catererDetailsRepository.findByDate(catererDetailsAndMenu.getDate());
        if (catererDetailsAndMenuList.size() == 0) {
            catererDetailsRepository.save(catererDetailsAndMenu);
        } else {
            catererDetailsAndMenu.setId(catererDetailsAndMenuList.get(0).getId());
            catererDetailsRepository.save(catererDetailsAndMenu);
        }
        return true;
    }

    @RequestMapping("displayAllMenus")
    private List<CatererDetailsAndMenu> displayAllMenus(){
        return catererDetailsRepository.findAll();
    }

    @RequestMapping("/clearAllCatererMenus")
    @PostMapping
    public boolean clearAllCatererMenus(){
        catererDetailsRepository.deleteAll();
        return true;
    }
}

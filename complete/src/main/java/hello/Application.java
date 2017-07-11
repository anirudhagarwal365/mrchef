package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import hello.repositories.CatererDetailsRepository;
import hello.repositories.UserMenuDetailRepository;
import hello.repositories.UserRepositry;
import models.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

    @Autowired
    private CatererDetailsRepository catererDetailsRepository;

    @Autowired
    private UserRepositry userRepositry;

    @Autowired
    private UserMenuDetailRepository userMenuDetailRepository;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
        return args -> {

            System.out.println("Let's inspect the beans provided by Spring Boot:");

            String[] beanNames = ctx.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName : beanNames) {
                System.out.println(beanName);
            }

//            CatererDetailsAndMenu catererDetailsAndMenu = new CatererDetailsAndMenu();
//            catererDetailsAndMenu.setDate("01-01-2017");
//            catererDetailsAndMenu.setVendorName("Anna Catering");
//
//            List<FoodItem> foodItems = new ArrayList<>();
//
//            FoodItem foodItem1 = new FoodItem();
//            foodItem1.setName("Roti");
//            foodItem1.setImageUrl("www.google.com");
//            foodItem1.setItemType("main_course");
//            foodItem1.setRating(9);
//            foodItems.add(foodItem1);
//
//            FoodItem foodItem2 = new FoodItem();
//            foodItem2.setName("Yellow dal");
//            foodItem2.setImageUrl("www.facebook.com");
//            foodItem2.setItemType("main_course");
//            foodItem2.setRating(6);
//            foodItems.add(foodItem2);
//            catererDetailsAndMenu.setFoodItems(foodItems);
//
//            catererDetailsRepository.save(catererDetailsAndMenu);
//
//            User user = new User();
//            user.setDateJoined("01-01-2017");
//            user.setEmpId("99999");
//            user.setName("abcd");
//            userRepositry.save(user);
//
//            UserMenuDetail userMenuDetail = new UserMenuDetail();
//            userMenuDetail.setUser(user);
//            userMenuDetail.setDate("03-01-2017");
//            userMenuDetail.setTakingFood(true);
//            List<UserMenuItem> userMenuItemList = new ArrayList<>();
//            UserMenuItem userMenuItem = new UserMenuItem("1234", true);
//            userMenuItemList.add(userMenuItem);
//            UserMenuItem userMenuItem1 = new UserMenuItem("2345", false);
//            userMenuItemList.add(userMenuItem1);
//            userMenuDetail.setUserMenuItems(userMenuItemList);
//            userMenuDetailRepository.save(userMenuDetail);

        };
    }

}

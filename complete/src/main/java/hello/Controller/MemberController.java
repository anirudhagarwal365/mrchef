package hello.Controller;

import hello.repositories.CatererDetailsRepository;
import hello.repositories.UserRepositry;
import models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by anirudh on 08/07/17.
 */
@RestController
public class MemberController {

    @Autowired
    private CatererDetailsRepository catererDetailsRepository;

    @Autowired
    private UserRepositry userRepositry;

    @RequestMapping("/addUser")
    @PutMapping
    private boolean addUser(@RequestBody(required = true) User user){
        List<User> userList = userRepositry.findByEmpId(user.getEmpId());
        if (userList.isEmpty()){
            userRepositry.save(user);
        } else {
            user.setId(userList.get(0).getId());
            userRepositry.save(user);
        }
        return true;
    }

    @RequestMapping("/getAllUsers")
    private List<User> getAllUsers(){
        return userRepositry.findAll();
    }

    @RequestMapping("/deleteAllUsers")
    @PostMapping
    private boolean deleteAllUsers(){
        userRepositry.deleteAll();
        return true;
    }

}

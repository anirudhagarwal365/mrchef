package hello.repositories;

import models.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by anirudh on 08/07/17.
 */
public interface UserRepositry extends MongoRepository<User, String>{
    List<User> findByEmpId(String empId);
}

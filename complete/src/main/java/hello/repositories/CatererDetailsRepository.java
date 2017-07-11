package hello.repositories;

import models.CatererDetailsAndMenu;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by anirudh on 08/07/17.
 */
public interface CatererDetailsRepository extends MongoRepository<CatererDetailsAndMenu, String> {

    List<CatererDetailsAndMenu> findByDate(String date);

}

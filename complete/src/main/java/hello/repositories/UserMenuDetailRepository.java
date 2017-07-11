package hello.repositories;

import models.UserMenuDetail;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;

import java.util.List;

/**
 * Created by anirudh on 08/07/17.
 */
public interface UserMenuDetailRepository extends MongoRepository<UserMenuDetail, String>,
        QueryDslPredicateExecutor<UserMenuDetail> {

    List<UserMenuDetail> findByTakingFoodIsTrue();
}

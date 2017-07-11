package models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import java.util.List;

/**
 * Created by anirudh on 08/07/17.
 */
@Document
public class UserMenuDetail {
    @Id
    @GeneratedValue
    public String id;

    public User user;
    public String date;
    public boolean takingFood;
    public List<UserMenuItem> userMenuItems;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isTakingFood() {
        return takingFood;
    }

    public void setTakingFood(boolean takingFood) {
        this.takingFood = takingFood;
    }

    public List<UserMenuItem> getUserMenuItems() {
        return userMenuItems;
    }

    public void setUserMenuItems(List<UserMenuItem> userMenuItems) {
        this.userMenuItems = userMenuItems;
    }
}

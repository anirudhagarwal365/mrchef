package models;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

/**
 * Created by anirudh on 08/07/17.
 */
public class UserMenuItem {
    @Id
    @GeneratedValue
    private String id;

    public String foodId;
    public boolean consuming;

    public UserMenuItem(String foodId, boolean consuming) {
        this.foodId = foodId;
        this.consuming = consuming;
    }

    public UserMenuItem() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodId() {
        return foodId;
    }

    public void setFoodId(String foodId) {
        this.foodId = foodId;
    }

    public boolean isConsuming() {
        return consuming;
    }

    public void setConsuming(boolean consuming) {
        this.consuming = consuming;
    }

}

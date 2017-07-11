package models;

import org.springframework.data.annotation.Id;

import javax.persistence.GeneratedValue;

/**
 * Created by anirudh on 08/07/17.
 */
public class FoodItem {
    @Id
    public String id;

    private String name;
    private String imageUrl;
    private String itemType;
    private int rating;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}

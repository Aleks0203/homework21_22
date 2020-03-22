package ru.mail.druk_aleksandr.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

public class ShopDTO {
    @Null
    private Long id;
    @NotNull
    @Size(min = 3, max = 40, message = "is required")
    private String shopName;
    @NotNull
    @Size(min = 3, max = 40, message = "is required")
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }
}

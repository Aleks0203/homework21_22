package ru.mail.druk_aleksandr.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemDTO {
    @Null
    private Long id;
    @NotNull
    @Size(min = 3, max = 40, message = "is required")
    private String name;
    @NotNull
    @Size(min = 3, max = 40, message = "is required")
    private String description;
    @DecimalMin(value = "1", message = "is required")
    private BigDecimal price;

    private List<ShopDTO> shops = new ArrayList<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<ShopDTO> getShops() {
        return shops;
    }

    public void setShops(List<ShopDTO> shops) {
        this.shops = shops;
    }
}

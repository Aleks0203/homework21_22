package ru.mail.druk_aleksandr.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @OneToOne(fetch = FetchType.LAZY,
            mappedBy = "item",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private ItemDetails itemDetails;

    @ManyToMany(cascade = CascadeType.MERGE)
    @JoinTable(
            name = "item_shop",
            joinColumns = {@JoinColumn(name = "items_id")},
            inverseJoinColumns = {@JoinColumn(name = "shops_id")})
    private List<Shop> shops = new ArrayList<>();

    public Item() {
    }

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

    public ItemDetails getItemDetails() {
        return itemDetails;
    }

    public void setItemDetails(ItemDetails itemDetails) {
        this.itemDetails = itemDetails;
    }

    public List<Shop> getShops() {
        return shops;
    }

    public void setShops(List<Shop> shops) {
        this.shops = shops;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(description, item.description) &&
                Objects.equals(itemDetails, item.itemDetails) &&
                Objects.equals(shops, item.shops);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, itemDetails, shops);
    }
}

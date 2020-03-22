package ru.mail.druk_aleksandr.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "itemdetails")
public class ItemDetails {
    @GenericGenerator(name = "generator",
            strategy = "foreign",
            parameters = @Parameter(name = "property", value = "item"))
    @Id
    @GeneratedValue(generator = "generator")
    @Column(name = "item_id", unique = true, nullable = false)
    private Long itemId;
    @Column(name = "price")
    private String price;
    @OneToOne(fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Item item;

    public ItemDetails() {
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemDetails that = (ItemDetails) o;
        return Objects.equals(itemId, that.itemId) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, price);
    }


}

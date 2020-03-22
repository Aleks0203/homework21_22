package ru.mail.druk_aleksandr.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.druk_aleksandr.ItemRepositiry;
import ru.mail.druk_aleksandr.ItemService;
import ru.mail.druk_aleksandr.model.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ItemServiceImpl implements ItemService {
    private final ItemRepositiry itemRepositiry;

    public ItemServiceImpl(ItemRepositiry itemRepositiry) {
        this.itemRepositiry = itemRepositiry;
    }

    @Override
    @Transactional
    public void add(ItemDTO itemDTO) {
        Item item = getObjectFromDTO(itemDTO);
        itemRepositiry.add(item);
    }

    @Override
    @Transactional
    public List<ItemDTO> findAll() {
        List<Item> items = itemRepositiry.findAll();
        List<ItemDTO> itemDTOS = items.stream()
                .map(this::getDTOFromObject)
                .collect(Collectors.toList());
        return itemDTOS;
    }

    @Override
    @Transactional
    public ItemDTO findById(long id) {
        Item item = itemRepositiry.findById(id);
        ItemDTO itemDTO = getDTOFromObject(item);
        return itemDTO;
    }

    @Override
    @Transactional
    public void removeById(long id) {
        Item item = itemRepositiry.findById(id);
        itemRepositiry.remove(item);
    }

    private Item getObjectFromDTO(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setDescription(itemDTO.getDescription());

        ItemDetails itemDetails = new ItemDetails();
        itemDetails.setPrice(String.valueOf(itemDTO.getPrice()));
        itemDetails.setItem(item);
        item.setItemDetails(itemDetails);

        List<Shop> shops = new ArrayList<>();
        for (ShopDTO shopDTO : itemDTO.getShops()) {
            shops.add(getShopFromDTO(shopDTO));
        }
        item.setShops(shops);
        return item;
    }

    private ItemDTO getDTOFromObject(Item item) {
        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setId(item.getId());
        itemDTO.setName(item.getName());
        itemDTO.setDescription(item.getDescription());
        String priceFromDatabase = item.getItemDetails().getPrice();
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(priceFromDatabase));
        itemDTO.setPrice(price);

        List<ShopDTO> shopDTOS = new ArrayList<>();
        for (Shop shop : item.getShops()) {
            shopDTOS.add(getDTOFromShop(shop));
        }
        itemDTO.setShops(shopDTOS);
        return itemDTO;
    }

    private ShopDTO getDTOFromShop(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setShopName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }

    private Shop getShopFromDTO(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setId(shopDTO.getId());
        shop.setName(shopDTO.getShopName());
        shop.setLocation(shopDTO.getLocation());
        return shop;
    }
}

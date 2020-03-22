package ru.mail.druk_aleksandr.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mail.druk_aleksandr.ShopRepository;
import ru.mail.druk_aleksandr.ShopService;
import ru.mail.druk_aleksandr.model.Shop;
import ru.mail.druk_aleksandr.model.ShopDTO;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShopServiceImpl implements ShopService {
    private final ShopRepository shopRepository;

    public ShopServiceImpl(ShopRepository shopRepository) {
        this.shopRepository = shopRepository;
    }

    @Override
    @Transactional
    public void add(ShopDTO shopDTO) {
        Shop shop = getObjectFromDTO(shopDTO);
        shopRepository.add(shop);
    }

    @Override
    @Transactional
    public List<ShopDTO> findAll() {
        List<Shop> shops = shopRepository.findAll();
        List<ShopDTO> shopDTOS = shops.stream()
                .map(this::getDTOFromObject)
                .collect(Collectors.toList());
        return shopDTOS;
    }

    private Shop getObjectFromDTO(ShopDTO shopDTO) {
        Shop shop = new Shop();
        shop.setId(shopDTO.getId());
        shop.setName(shopDTO.getShopName());
        shop.setLocation(shopDTO.getLocation());
        return shop;
    }

    private ShopDTO getDTOFromObject(Shop shop) {
        ShopDTO shopDTO = new ShopDTO();
        shopDTO.setId(shop.getId());
        shopDTO.setShopName(shop.getName());
        shopDTO.setLocation(shop.getLocation());
        return shopDTO;
    }
}

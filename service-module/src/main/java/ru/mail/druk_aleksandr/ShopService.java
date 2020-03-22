package ru.mail.druk_aleksandr;

import ru.mail.druk_aleksandr.model.ShopDTO;

import java.util.List;

public interface ShopService {
    void add(ShopDTO shopDTO);

    List<ShopDTO> findAll();
}

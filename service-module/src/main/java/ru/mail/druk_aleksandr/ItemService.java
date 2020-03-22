package ru.mail.druk_aleksandr;

import ru.mail.druk_aleksandr.model.ItemDTO;

import java.util.List;

public interface ItemService {
    void add(ItemDTO itemDTO);

    List<ItemDTO> findAll();

    ItemDTO findById(long id);

    void removeById(long id);
}

package ru.mail.druk_aleksandr.impl;

import org.springframework.stereotype.Repository;
import ru.mail.druk_aleksandr.ShopRepository;
import ru.mail.druk_aleksandr.model.Shop;

@Repository
public class ShopRepositoryImpl extends GenericRepositoryImpl<Long, Shop> implements ShopRepository {
}

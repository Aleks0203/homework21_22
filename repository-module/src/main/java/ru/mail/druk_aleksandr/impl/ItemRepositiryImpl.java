package ru.mail.druk_aleksandr.impl;

import org.springframework.stereotype.Repository;
import ru.mail.druk_aleksandr.ItemRepositiry;
import ru.mail.druk_aleksandr.model.Item;

@Repository
public class ItemRepositiryImpl extends GenericRepositoryImpl<Long, Item> implements ItemRepositiry {
}

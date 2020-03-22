package ru.mail.druk_aleksandr.springbootmodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mail.druk_aleksandr.ItemService;
import ru.mail.druk_aleksandr.ShopService;
import ru.mail.druk_aleksandr.model.ItemDTO;
import ru.mail.druk_aleksandr.model.ShopDTO;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
@Controller
@RequestMapping("/items")
public class ItemController {
    private final ItemService itemService;
    private final ShopService shopService;
    public ItemController(ItemService itemService, ShopService shopService) {
        this.itemService = itemService;
        this.shopService = shopService;
    }

    @GetMapping
    public String getItems(Model model) {
        List<ItemDTO> items = itemService.findAll();
        model.addAttribute("items", items);
        return "items";
    }

    @GetMapping("/add")
    public String addItemPage(Model model) {
        model.addAttribute("item", new ItemDTO());
        model.addAttribute("shop", new ShopDTO());
        return "item_add";
    }

    @PostMapping
    public String addItem(@Valid @ModelAttribute(name = "item") ItemDTO item,
                          BindingResult bindingResult,
                          Model model,
                          @ModelAttribute(name = "shop") ShopDTO shop) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("item", item);
            return "item_add";
        }
        List<ShopDTO> lists = shopService.findAll();
        List<ShopDTO> shops = new ArrayList<>();
        for (ShopDTO list : lists) {
            if (list.getShopName().matches(shop.getShopName())) {
                shops.add(list);
            }
        }
        if (shops.isEmpty()) {
            model.addAttribute("error", "Shop does not exist in the database");
            return "item_add";
        }
        item.setShops(shops);
        itemService.add(item);
        return "redirect:/items";
    }


    @GetMapping("/{id}")
    public String getItem(@PathVariable Long id, Model model) {
        ItemDTO item = itemService.findById(id);
        List<ShopDTO> shops = item.getShops();
        model.addAttribute("item", item);
        model.addAttribute("shops", shops);
        return "item";
    }

    @GetMapping("/{id}/delete")
    public String deleteItem(@PathVariable Long id, Model model) {
        itemService.removeById(id);
        return "redirect:/items";
    }
}

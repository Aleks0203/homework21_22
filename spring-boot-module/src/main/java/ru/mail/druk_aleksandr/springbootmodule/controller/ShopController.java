package ru.mail.druk_aleksandr.springbootmodule.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.mail.druk_aleksandr.ShopService;
import ru.mail.druk_aleksandr.model.ShopDTO;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/shops")
public class ShopController {
    private final ShopService shopService;

    public ShopController(ShopService shopService) {
        this.shopService = shopService;
    }


    @GetMapping
    public String getShops(Model model) {
        List<ShopDTO> shops = shopService.findAll();
        model.addAttribute("shops", shops);
        return "shops";
    }

    @GetMapping("/add")
    public String addShopPage(Model model) {
        model.addAttribute("shop", new ShopDTO());
        return "shop_add";
    }

    @PostMapping
    public String addShop(
            @Valid @ModelAttribute(name = "shop")ShopDTO shop,
            BindingResult bindingResult,
            Model model
    ) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("shop", shop);
            return "shop_add";
        }
        shopService.add(shop);
        return "redirect:/shops";
    }

}

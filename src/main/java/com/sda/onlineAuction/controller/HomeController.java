package com.sda.onlineAuction.controller;

import com.sda.onlineAuction.dto.ProductDto;
import com.sda.onlineAuction.service.ProductService;
import com.sda.onlineAuction.validator.ProductDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private ProductDtoValidator productDtoValidator;

    @GetMapping("/addItem")
    public String getAddItemPage(Model model) {
        System.out.println("Rulez get pe /addItem ");
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "addItem";
    }

    @PostMapping("/addItem")
    public String postAddItemPage(Model model, ProductDto productDto, BindingResult bindingResult) {
        System.out.println("Am primit: " + productDto);
        productDtoValidator.validate(productDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addItem";
        }
        productService.add(productDto);
        return "redirect:/addItem";
    }

}

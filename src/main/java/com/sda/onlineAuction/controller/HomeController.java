package com.sda.onlineAuction.controller;

import com.sda.onlineAuction.dto.ProductDto;
import com.sda.onlineAuction.dto.UserDto;
import com.sda.onlineAuction.service.ProductService;
import com.sda.onlineAuction.service.UserService;
import com.sda.onlineAuction.validator.ProductDtoValidator;
import com.sda.onlineAuction.validator.UserDtoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Controller
public class HomeController {
    @Autowired
    private ProductService productService;

    @Autowired
    private UserService userService;

    @Autowired
    private ProductDtoValidator productDtoValidator;

    @Autowired
    private UserDtoValidator userDtoValidator;


    @GetMapping("/addItem")
    public String getAddItemPage(Model model) {
        System.out.println("Rulez get pe /addItem ");
        ProductDto productDto = new ProductDto();
        model.addAttribute("productDto", productDto);
        return "addItem";
    }

    @PostMapping("/addItem")
    public String postAddItemPage(Model model, ProductDto productDto, BindingResult bindingResult, @RequestParam("productImage") MultipartFile multipartFile) {

        productDtoValidator.validate(productDto, bindingResult);
        if (bindingResult.hasErrors()) {
            return "addItem";
        }
        productService.add(productDto, multipartFile);
        return "redirect:/addItem";
    }

    @GetMapping({"/home","/"})
    public String getHomePage(Model model) {
        List<ProductDto> productDtoList = productService.getAllProductsDtos();
        model.addAttribute("products", productDtoList);

        return "home";
    }

    @GetMapping("/item/{productId}")
    public String getProductPage(@PathVariable(value = "productId") String productId, Model model) {
        Optional<ProductDto> optionalProductDto = productService.getProductById(productId);
        if (!optionalProductDto.isPresent()) {
            return "errorPage";
        }
        ProductDto productDto = optionalProductDto.get();
        model.addAttribute("product", productDto);
        return "viewItem";
    }

    @GetMapping("/registration")
    public String getRegistrationPage(Model model) {
        UserDto userDto = new UserDto();
        model.addAttribute("userDto", userDto);
        return "registration";
    }

    @PostMapping("/registration")
    public String postRegistrationPage(Model model, UserDto userDto,BindingResult bindingResult){
        userDtoValidator.validate(userDto, bindingResult);
        if(bindingResult.hasErrors()){
            return "registration";
        }
        userService.add(userDto);
        return "redirect:/home";
    }

    @GetMapping("/login")
    public String getLoginPage(Model model){
        return "login";
    }

    @GetMapping("/login-error")
    public String getLoginErrorPage(Model model){
        model.addAttribute("loginError", true);
        return "login";
    }

}

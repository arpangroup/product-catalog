package com.arpan.productcatalog.controller.ui;

import com.arpan.productcatalog.entity.product.ProductCategory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryUiController {

    @GetMapping("")
    public String getAllCategories (Model model, ProductCategory category) {
        //model.addAttribute("users", userRepository.findAll());
        return "add-category.html";
    }


    @PostMapping("")
    public String createNewCategory (Model model, ProductCategory category) {
        //model.addAttribute("users", userRepository.findAll());
        return "index.html";
    }


    @GetMapping("/{id}")
    public String showUpdateForm(@PathVariable("id") long id, Model model) {
        //User user = userRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        //model.addAttribute("user", user);
        return "index";
    }

//    @PostMapping("/update/{id}")
//    public String updateUser(@PathVariable("id") long id, @Valid User user,
//                             BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            user.setId(id);
//            return "update-user";
//        }
//
//        userRepository.save(user);
//        return "redirect:/index";
//    }

}

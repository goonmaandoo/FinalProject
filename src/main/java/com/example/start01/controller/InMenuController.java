package com.example.start01.controller;

import com.example.start01.dto.MenuDto;
import com.example.start01.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class InMenuController {
    @Autowired
    MenuService menuService;

    @PostMapping("/selectMenu")
    public List<MenuDto> selectMenu(@RequestBody MenuDto menuDto) {
        System.out.println("menudto,"+menuDto);
        return menuService.selectMenu(menuDto);
    }

    @GetMapping("/store/{storeId}")
    public List<MenuDto> getMenusByStore(@PathVariable Integer storeId) {
        System.out.println("store menu");
        return menuService.selectByStore(storeId);
    }

}

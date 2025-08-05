package com.example.start01.controller;

import com.example.start01.dao.MenuDao;
import com.example.start01.dto.MenuDto;
import com.example.start01.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    public MenuDao menuDao;

    @GetMapping("/storeMenu/{storeId}")
    public List<MenuDto> StoreMenuList(@PathVariable("storeId") int storeId){
        return menuDao.StoreMenuList(storeId);
    }
    @GetMapping ("/storeMenu/image/{storeId}")
    public List<MenuDto> StoreMenuImage(@PathVariable("storeId") int storeId){
        return menuDao.StoreMenuImage(storeId);
    }

    @GetMapping ("/keyword/{keyword}")
    public List<MenuDto> SelectByKeyword(@PathVariable("keyword") String keyword){
        return menuDao.SelectByKeyword(keyword);
    }
}

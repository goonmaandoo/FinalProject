package com.example.start01.controller;

import com.example.start01.dao.MenuDao;
import com.example.start01.dto.MenuDto;
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

    @GetMapping("/owner/{ownerId}")
    public List<MenuDto> getMenuByOwnerId(@PathVariable("ownerId") int ownerId) {
        return menuDao.findMenuByOwnerId(ownerId);
    }

    @GetMapping("/ownerWithImage/{ownerId}")
    public List<MenuDto> getMenuWithImage(@PathVariable int ownerId) {
        return menuDao.findMenuWithImageByOwnerId(ownerId);
    }


}

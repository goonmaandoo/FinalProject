package com.example.start01.controller;

import com.example.start01.dao.MenuDao;
import com.example.start01.dto.MenuDto;
import com.example.start01.dto.StoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/menuUpdateByOwner")
    public String MenuUpdateByOwner(@RequestBody MenuDto menuDto) {
        menuDao.MenuUpdateByOwner(menuDto);
        return "메뉴 수정 완료!";
    }

    @GetMapping("/menuDeleteByOwner/{id}")
    public int MenuDeleteByOwner(@PathVariable int id) {

        return menuDao.MenuDeleteByOwner(id);
    }

    @PostMapping("/menuInsertByOwner")
    public String MenuInsertByOwner(@RequestBody MenuDto menuDto) {
        menuDao.MenuInsertByOwner(menuDto);
        return "메뉴 등록 완료!";
    }

}

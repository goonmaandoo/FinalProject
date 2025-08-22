package com.example.start01.controller;

import com.example.start01.dao.ImageDao;
import com.example.start01.dao.MenuDao;
import com.example.start01.dto.ImageDto;
import com.example.start01.dto.MenuDto;
import com.example.start01.dto.StoreDto;
import com.example.start01.dto.UsersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/menu")
public class MenuController {
    @Autowired
    public MenuDao menuDao;

    @Autowired
    ImageDao imageDao;

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
    public String MenuUpdateByOwner(
            @RequestParam("id") Integer id,
            @RequestParam("storeId") Integer storeId,
            @RequestParam("menuName") String menuName,
            @RequestParam("menuPrice") int menuPrice,
            @RequestParam("status") String status,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        MenuDto menuDto = new MenuDto();
        menuDto.setId(id);
        menuDto.setMenuName(menuName);
        menuDto.setMenuPrice(menuPrice);
        menuDto.setStatus(status);

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

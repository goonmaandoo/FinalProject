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

        if (file != null && !file.isEmpty()) {
            try {
                String storeFolder = "store_" + storeId;
                String uploadDir = System.getProperty("user.dir") + "/src/main/resources/static/image/imgfile/" + storeFolder + "/";
                File directory = new File(uploadDir);
                if (!directory.exists()) {
                    directory.mkdirs();
                }

                String originalFilename = file.getOriginalFilename();
                String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
                String newFilename = "menu_" + id + extension;

                File saveFile = new File(uploadDir + newFilename);
                file.transferTo(saveFile);

                // 1. 이미지 정보를 image 테이블에 저장
                ImageDto imageDto = new ImageDto();
                imageDto.setFolder(storeFolder);
                imageDto.setFilename(newFilename);
                imageDao.MenuImageInsertByOwner(imageDto);

                // 2. 메뉴에 이미지 ID 연결
                menuDto.setImageId(imageDto.getId()); // 생성된 이미지 ID 설정

            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException("파일 저장 실패: " + e.getMessage());
            }
        }

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

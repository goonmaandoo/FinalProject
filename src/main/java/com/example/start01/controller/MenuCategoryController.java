package com.example.start01.controller;

import com.example.start01.dao.MenuCategoryDao;
import com.example.start01.dao.RoomDao;
import com.example.start01.dto.MenuCategoryDto;
import com.example.start01.dto.RoomDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/category")
public class MenuCategoryController {

    @Autowired
    private MenuCategoryDao menuCategoryDao;

    @GetMapping("/all")
    public List<MenuCategoryDto> MenuCategoryAll() {
        return menuCategoryDao.MenuCategoryAll();
    }
}

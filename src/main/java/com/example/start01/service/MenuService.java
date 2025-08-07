package com.example.start01.service;

import com.example.start01.dao.MenuDao;
import com.example.start01.dto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuService {
    @Autowired
    MenuDao menuDao;

    public List<MenuDto> selectMenu(MenuDto menuDto) {
        return menuDao.selectMenu(menuDto);
    }

    public List<MenuDto> selectByStore(Integer storeId) {
        return menuDao.selectByStore(storeId);
    }

}

package com.example.start01.controller;

import com.example.start01.dao.PopularDao;
import com.example.start01.dto.PopularDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/popular")
public class PopularController {

    @Autowired
    private PopularDao popluarDao;

    @GetMapping("/all")
    public List<PopularDto> PopularSelect() {
        return popluarDao.PopularSelect();
    }

}

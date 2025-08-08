package com.example.start01.dao;

import com.example.start01.dto.MenuDto;
import com.example.start01.dto.RoomDto;
import com.example.start01.dto.StoreDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao {
    List<MenuDto> StoreMenuList(@Param("storeId") int storeId);

    List<MenuDto> StoreMenuImage(@Param("storeId") int storeId);


    // 룸챗
    List<MenuDto> selectMenu(MenuDto menuDto);
    List<MenuDto> selectByStore(Integer storeId);

    List<MenuDto> SelectByKeyword(@Param("keyword") String keyword);

    List<MenuDto> findMenuByOwnerId(@Param("ownerId") int ownerId);

    List<MenuDto> findMenuWithImageByOwnerId(@Param("ownerId") int ownerId);

    void MenuUpdateByOwner(MenuDto menuDto);

    int MenuDeleteByOwner(int id);

    void MenuInsertByOwner(MenuDto menuDto);

}

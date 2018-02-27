package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.EasyUIResult;
import com.taotao.pojo.TbItem;
import com.taotao.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;


    @RequestMapping("/{itemId}")
    @ResponseBody
    public TbItem getItemById(@PathVariable Long itemId) {
        return itemService.getItemById(itemId);
    }

    @RequestMapping("/list")
    //设置相应的内容为json数据
    @ResponseBody
    public EasyUIResult getItemlist(@RequestParam(defaultValue = "1") Integer page,@RequestParam(defaultValue = "30") Integer rows) {
        //查询商品列表
        return itemService.getItemList(page, rows);
    }

    /**
     * 创建商品
     *
     * @param item
     * @return
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult createItem(TbItem item,String desc,String itemParams) throws Exception{
        return itemService.createItem(item,desc,itemParams);
    }
}

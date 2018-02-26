package com.taotao.controller;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.TbItemParam;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/item/param")
public class ItemParamController {

    @Autowired
    private ItemParamService itemParamService;

    @ResponseBody
    @RequestMapping("/query/itemcatid/{itemCatId}")
    public TaotaoResult getItemParamByCid(@PathVariable long itemCatId) {
        return itemParamService.getItemParamByCid(itemCatId);
    }

    @RequestMapping(value = "/save/{cid}", method = RequestMethod.POST)
    @ResponseBody
    public TaotaoResult insertItemParam(@PathVariable long cid, String paramData) {
        TbItemParam param = new TbItemParam();
        param.setItemCatId(cid);
        param.setParamData(paramData);
        TaotaoResult taotaoResult = itemParamService.insertItemParam(param);
        return taotaoResult;
    }
}

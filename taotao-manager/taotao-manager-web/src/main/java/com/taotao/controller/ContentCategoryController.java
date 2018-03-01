package com.taotao.controller;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/content/category")
public class ContentCategoryController {

    @Autowired
    private ContentCategoryService contentCategoryService;

    @RequestMapping("/list")
    @ResponseBody
    public List<EUTreeNode> getContentCatList(@RequestParam(value = "id", defaultValue = "0") Long parentId) {
        List<EUTreeNode> list = contentCategoryService.getCategoryList(parentId);
        return list;
    }

    @RequestMapping("/create")
    @ResponseBody
    public TaotaoResult create(Long parentId, String name) {
        return contentCategoryService.insertContentCategory(parentId, name);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public TaotaoResult delete(long id) {
        return contentCategoryService.deleteContentCategory(id);
    }

    @RequestMapping("/update")
    @ResponseBody
    public TaotaoResult update(long id, String name) {
        return contentCategoryService.updateContentCategory(id, name);
    }
}

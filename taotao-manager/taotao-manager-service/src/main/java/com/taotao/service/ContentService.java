package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TbContent;

import java.util.List;

public interface ContentService {

    EUDataGridResult list(Integer page, Integer rows, long categoryId);

    TaotaoResult insertContent(TbContent content);

    TaotaoResult editContent(TbContent content);

    List<TbContent> getContentList(long contentCid);

    TaotaoResult deleteContent(String ids);
}

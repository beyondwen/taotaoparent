package com.taotao.service;

import com.taotao.common.pojo.TaotaoResult;
import com.taotao.pojo.EasyUIResult;
import com.taotao.pojo.TbItemParam;

public interface ItemParamService {

    TaotaoResult getItemParamByCid(long cid);

    TaotaoResult insertItemParam(TbItemParam itemParam);

    EasyUIResult getItemParamList(Integer page, Integer rows);
}

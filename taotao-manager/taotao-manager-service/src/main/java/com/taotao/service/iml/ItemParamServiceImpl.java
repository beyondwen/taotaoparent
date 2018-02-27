package com.taotao.service.iml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbItemParamMapper;
import com.taotao.pojo.EasyUIResult;
import com.taotao.pojo.TbItem;
import com.taotao.pojo.TbItemParam;
import com.taotao.pojo.TbItemParamExample;
import com.taotao.pojo.TbItemParamExample.Criteria;
import com.taotao.service.ItemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 商品规格参数模板
 */
@Service
public class ItemParamServiceImpl implements ItemParamService {

    @Autowired
    private TbItemParamMapper itemParamMapper;

    public TaotaoResult getItemParamByCid(long cid) {
        TbItemParamExample example = new TbItemParamExample();
        Criteria criteria = example.createCriteria();
        criteria.andItemCatIdEqualTo(cid);
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        if (!list.isEmpty()) {
            return TaotaoResult.ok(list.get(0));
        }
        return TaotaoResult.ok();
    }

    public TaotaoResult insertItemParam(TbItemParam itemParam) {
        itemParam.setCreated(new Date());
        itemParam.setUpdated(new Date());
        itemParamMapper.insert(itemParam);
        return TaotaoResult.ok();
    }

    public EasyUIResult getItemParamList(Integer page, Integer rows) {
        TbItemParamExample example = new TbItemParamExample();
        //设置分页
        PageHelper.startPage(page, rows);
        Criteria criteria = example.createCriteria();
        List<TbItemParam> list = itemParamMapper.selectByExampleWithBLOBs(example);
        //取分页信息
        PageInfo<TbItemParam> pageInfo = new PageInfo<TbItemParam>(list);
        long total = pageInfo.getTotal();
        EasyUIResult result = new EasyUIResult(total, list);
        return result;
    }
}

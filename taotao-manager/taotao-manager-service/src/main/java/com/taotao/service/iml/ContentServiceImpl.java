package com.taotao.service.iml;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentMapper;
import com.taotao.pojo.EUDataGridResult;
import com.taotao.pojo.TbContent;
import com.taotao.pojo.TbContentExample;
import com.taotao.service.ContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ContentServiceImpl implements ContentService {

    @Autowired
    private TbContentMapper tbContentMapper;

    public EUDataGridResult list(Integer page, Integer rows, long categoryId) {
        TbContentExample example = new TbContentExample();
        //设置分页
        PageHelper.startPage(page, rows);
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<TbContent> contents = tbContentMapper.selectByExample(example);
        //取分页信息
        PageInfo<TbContent> pageInfo = new PageInfo<TbContent>(contents);
        long total = pageInfo.getTotal();
        return new EUDataGridResult(total, contents);
    }

    public TaotaoResult insertContent(TbContent content) {
        //补全pojo内容
        content.setCreated(new Date());
        content.setUpdated(new Date());
        tbContentMapper.insert(content);
        return TaotaoResult.ok();
    }

    public List<TbContent> getContentList(long contentCid) {
        //根据内容分类id查询内容列表
        TbContentExample example = new TbContentExample();
        TbContentExample.Criteria criteria = example.createCriteria();
        criteria.andCategoryIdEqualTo(contentCid);
        //执行查询
        List<TbContent> list = tbContentMapper.selectByExample(example);
        return list;
    }
}

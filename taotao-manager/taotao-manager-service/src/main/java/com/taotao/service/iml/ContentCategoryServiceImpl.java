package com.taotao.service.iml;

import com.taotao.common.pojo.EUTreeNode;
import com.taotao.common.pojo.TaotaoResult;
import com.taotao.mapper.TbContentCategoryMapper;
import com.taotao.pojo.TbContentCategory;
import com.taotao.pojo.TbContentCategoryExample;
import com.taotao.service.ContentCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ContentCategoryServiceImpl implements ContentCategoryService {

    @Autowired
    private TbContentCategoryMapper contentCategoryMapper;

    public List<EUTreeNode> getCategoryList(long parentId) {
        //根据parentid查询节点列表
        TbContentCategoryExample example = new TbContentCategoryExample();
        TbContentCategoryExample.Criteria criteria = example.createCriteria();
        criteria.andParentIdEqualTo(parentId);
        //执行查询
        List<TbContentCategory> list = contentCategoryMapper.selectByExample(example);
        List<EUTreeNode> resultList = new ArrayList<EUTreeNode>();
        for (TbContentCategory tbContentCategory : list) {
            //创建一个节点
            EUTreeNode node = new EUTreeNode();
            node.setId(tbContentCategory.getId());
            node.setText(tbContentCategory.getName());
            node.setState(tbContentCategory.getIsParent() ? "closed" : "open");

            resultList.add(node);
        }
        return resultList;
    }

    public TaotaoResult insertContentCategory(long parentId, String name) {
        //创建一个pojo
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setName(name);
        contentCategory.setIsParent(false);
        //'状态。可选值:1(正常),2(删除)',
        contentCategory.setStatus(1);
        contentCategory.setParentId(parentId);
        contentCategory.setSortOrder(1);
        contentCategory.setCreated(new Date());
        contentCategory.setUpdated(new Date());
        //添加记录
        contentCategoryMapper.insert(contentCategory);
        //查看父节点的isParent列是否为true，如果不是true改成true
        TbContentCategory parentCat = contentCategoryMapper.selectByPrimaryKey(parentId);
        //判断是否为true
        if (!parentCat.getIsParent()) {
            parentCat.setIsParent(true);
            //更新父节点
            contentCategoryMapper.updateByPrimaryKey(parentCat);
        }
        //返回结果
        return TaotaoResult.ok(contentCategory);
    }

    public TaotaoResult deleteContentCategory(long id) {
        //根据id查询TbContentCategory
        TbContentCategory contentCategory = contentCategoryMapper.selectByPrimaryKey(id);
        //判断是否是父节点
        if (contentCategory.getIsParent()) {
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(id);
            //通过父id查询子节点
            List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(example);
            for (TbContentCategory tbContentCategory : tbContentCategories) {
                //删除子节点
                contentCategoryMapper.deleteByPrimaryKey(tbContentCategory.getId());
            }
            //删除自己
            contentCategoryMapper.deleteByPrimaryKey(contentCategory.getId());
        } else {
            //如果不是父节点
            contentCategoryMapper.deleteByPrimaryKey(id);
            TbContentCategoryExample example = new TbContentCategoryExample();
            TbContentCategoryExample.Criteria criteria = example.createCriteria();
            criteria.andParentIdEqualTo(contentCategory.getParentId());
            //查询父id下是否有其他子节点
            List<TbContentCategory> tbContentCategories = contentCategoryMapper.selectByExample(example);
            //如果没有
            if (tbContentCategories.isEmpty()) {
                TbContentCategory tbContentCategory = contentCategoryMapper.selectByPrimaryKey(contentCategory.getParentId());
                //设置自己不是父节点
                tbContentCategory.setIsParent(false);
                //进行更新
                contentCategoryMapper.updateByPrimaryKey(tbContentCategory);
            }
        }
        return TaotaoResult.ok();
    }

    public TaotaoResult updateContentCategory(long id, String name) {
        TbContentCategory contentCategory = new TbContentCategory();
        contentCategory.setId(id);
        contentCategory.setName(name);
        contentCategoryMapper.updateByPrimaryKeySelective(contentCategory);
        return TaotaoResult.ok();
    }
}

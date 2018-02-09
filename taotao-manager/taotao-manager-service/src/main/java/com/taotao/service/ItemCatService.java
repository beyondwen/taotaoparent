package com.taotao.service;

import com.taotao.common.pojo.EUTreeNode;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemCatService {

    List<EUTreeNode> getCatList(long parentId);
}

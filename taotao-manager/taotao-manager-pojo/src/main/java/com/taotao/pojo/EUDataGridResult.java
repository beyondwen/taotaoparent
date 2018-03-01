package com.taotao.pojo;

import java.util.List;

public class EUDataGridResult {

    private Integer total;

    private List<TbContent> rows;

    public EUDataGridResult(Integer total, List<TbContent> rows) {
        this.total = total;
        this.rows = rows;
    }

    public EUDataGridResult(Long total, List<TbContent> rows) {
        this.total = total.intValue();
        this.rows = rows;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<TbContent> getRows() {
        return rows;
    }

    public void setRows(List<TbContent> rows) {
        this.rows = rows;
    }
}

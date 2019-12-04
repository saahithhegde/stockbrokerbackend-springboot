package com.example.stockbroker.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

public class stockbuysell {
   private Integer stocktableid;

    private Integer quantity;

    private Long accountno;


    public Integer getStocktableid() {
        return stocktableid;
    }

    public void setStocktableid(Integer stocktableid) {
        this.stocktableid = stocktableid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


    public Long getAccountno() {
        return accountno;
    }

    public void setAccountno(Long accountno) {
        this.accountno = accountno;
    }
}

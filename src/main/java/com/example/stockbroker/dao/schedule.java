package com.example.stockbroker.dao;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class schedule {
    @Id
    private Integer stocktableid;
    private String ticekrsymbol;
    private Integer Quantity;
    private String buyorsell;
    private String typeofschedule;

    public Long getAccountno() {
        return accountno;
    }

    public void setAccountno(Long accountno) {
        this.accountno = accountno;
    }

    private String recurringvalue;
    private Long accountno;

    public Integer getStocktableid() {
        return stocktableid;
    }

    public void setStocktableid(Integer stocktableid) {
        this.stocktableid = stocktableid;
    }

    public String getTicekrsymbol() {
        return ticekrsymbol;
    }

    public void setTicekrsymbol(String ticekrsymbol) {
        this.ticekrsymbol = ticekrsymbol;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public String getBuyorsell() {
        return buyorsell;
    }

    public void setBuyorsell(String buyorsell) {
        this.buyorsell = buyorsell;
    }

    public String getTypeofschedule() {
        return typeofschedule;
    }

    public void setTypeofschedule(String typeofschedule) {
        this.typeofschedule = typeofschedule;
    }

    public String getRecurringvalue() {
        return recurringvalue;
    }

    public void setRecurringvalue(String recurringvalue) {
        this.recurringvalue = recurringvalue;
    }
}

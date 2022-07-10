package com.fisproject.springlbd.dto;

import java.util.ArrayList;

public class NBPTableDto {

    private String table;
    private String no;
    private String effectiveDate;
    private String currency;
    private ArrayList<Rate> rates= new ArrayList<>();

    public void setTable(String table) { this.table=table; }
    public String getTable() { return table; }

    public void setNo(String no) { this.no = no; }
    public String getNo() { return no; }

    public void setEffectiveDate(String effectiveDate) { this.effectiveDate = effectiveDate; }
    public String getEffectiveDate() { return effectiveDate; }

    public ArrayList<Rate> getRates() { return rates; }
    public void setRates(ArrayList<Rate> rates) { this.rates = rates; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getPrintableFormatTable() {
        StringBuilder sb = new StringBuilder("Exchange rates for date: "+getEffectiveDate()+"\n");

        for (Rate rate : getRates())
            sb.append("\t").append(rate.getCurrency()).append("\t|\t").append(rate.getMid()).append("\n");

        return sb.toString();
    }

    public String getPrintableFormatSingle() {
        StringBuilder sb = new StringBuilder("Exchange rates for currency: "+getCurrency()+"\n");

        for (Rate rate : getRates())
            sb.append("\t").append(rate.getEffectiveDate()).append("\t|\t").append(rate.getMid()).append("\n");

        return sb.toString();
    }

}

class Rate {
    String currency;
    String code;
    Double mid;
    String effectiveDate;

    public void setMid(Double mid) { this.mid = mid; }
    public Double getMid() { return mid; }

    public String getCode() { return code; }
    public void setCode(String code) { this.code = code; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public String getEffectiveDate() { return effectiveDate; }
    public void setEffectiveDate(String effectiveDate) { this.effectiveDate = effectiveDate; }

}

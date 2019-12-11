package com.hdsx.lwgl.statanalysis.util.excel;

import java.io.Serializable;
import java.util.List;

public class ExcelData implements Serializable
{
    private static final long serialVersionUID = 6133772627258154184L;
    private List<String> titles;
    private List<List<Object>> rows;
    private String name;

    public List<String> getTitles()
    {
        return this.titles;
    }

    public void setTitles(List<String> titles) {
        this.titles = titles;
    }

    public List<List<Object>> getRows() {
        return this.rows;
    }

    public void setRows(List<List<Object>> rows) {
        this.rows = rows;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
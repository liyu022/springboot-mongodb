package com.jolin.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*
   <columnOverride column="ids" javaType="java.lang.Integer[]" typeHandler="JsonIntegerArrayTypeHandler"/>
 */
public class JsonBigDecimalArrayTypeHandler extends BaseTypeHandler<BigDecimal[]> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, BigDecimal[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,toJson(parameter));
    }

    @Override
    public BigDecimal[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getString(columnName));
    }

    @Override
    public BigDecimal[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.toObject(rs.getString(columnIndex));
    }

    @Override
    public BigDecimal[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.toObject(cs.getString(columnIndex));
    }

    private String toJson(BigDecimal[] params) {
        try {
            return mapper.writeValueAsString(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "[]";
    }

    private BigDecimal[] toObject(String content) {
        if (content != null && !content.isEmpty()) {
            try {
                return (BigDecimal[]) mapper.readValue(content, BigDecimal[].class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

}


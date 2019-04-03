package com.jolin.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 继承自BaseTypeHandler<Object> 使用Object是为了让JsonUtil可以处理任意类型
 */

public class JsonTypeHandler extends BaseTypeHandler<Object[]> {

    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Object[] parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i,toJson(parameter));
    }

    @Override
    public Object[] getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return this.toObject(rs.getString(columnName));
    }

    @Override
    public Object[] getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return this.toObject(rs.getString(columnIndex));
    }

    @Override
    public Object[] getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return this.toObject(cs.getString(columnIndex));
    }

    private String toJson(Object[] params) {
        try {
            return mapper.writeValueAsString(params);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "[]";
    }

    private Object[] toObject(String content) {
        if (content != null && !content.isEmpty()) {
            try {
                return (Object[]) mapper.readValue(content, Object[].class);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            return null;
        }
    }

}

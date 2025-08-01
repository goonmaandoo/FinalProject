// src/main/java/com/example/start01/config/JsonNodeTypeHandler.java
package com.example.start01.config;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.type.*;
import java.sql.*;

@MappedTypes(com.fasterxml.jackson.databind.JsonNode.class)
@MappedJdbcTypes(org.apache.ibatis.type.JdbcType.VARCHAR)
public class JsonNodeTypeHandler extends BaseTypeHandler<JsonNode> {
    private static final ObjectMapper OM = new ObjectMapper();
    @Override public void setNonNullParameter(PreparedStatement ps, int i, JsonNode parameter, JdbcType jdbcType) throws SQLException {
        ps.setString(i, parameter == null ? null : parameter.toString());
    }
    @Override public JsonNode getNullableResult(ResultSet rs, String columnName) throws SQLException {
        String s = rs.getString(columnName);
        try { return s == null ? null : OM.readTree(s); } catch (Exception e) { throw new SQLException(e); }
    }
    @Override public JsonNode getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        String s = rs.getString(columnIndex);
        try { return s == null ? null : OM.readTree(s); } catch (Exception e) { throw new SQLException(e); }
    }
    @Override public JsonNode getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        String s = cs.getString(columnIndex);
        try { return s == null ? null : OM.readTree(s); } catch (Exception e) { throw new SQLException(e); }
    }
}

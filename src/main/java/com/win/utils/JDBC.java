package com.win.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.win.vo.DataBaseVO;

/**
 * @ClassName JDBC
 * @Description TODO(数据库JDBC连接)
 * @author huiziqin
 * @Date 2018年4月17日 下午6:59:11
 * @version 1.0.0
 */
public class JDBC {

    /**
     * @Description (获取连接)
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection getConnection(DataBaseVO vo) throws SQLException, ClassNotFoundException {
        Connection connection = null;
        Class.forName(vo.getDriveName());
        connection = DriverManager.getConnection(vo.getUrl() + vo.getDataBaseName(), vo.getUserName(),
                vo.getPassword());
        
        return connection;
    }

    /**
     * @Description (关闭连接)
     * @param con
     * @throws SQLException
     */
    public static void closeConnection(Connection connection, Statement st, ResultSet set) throws SQLException {
        if (set != null) {
            set.close();
        }
        if (st != null) {
            st.close();
        }

        if (connection != null) {
            connection.close();
        }

    }
}

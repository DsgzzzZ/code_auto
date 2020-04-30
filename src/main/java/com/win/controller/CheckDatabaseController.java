package com.win.controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.win.common.CommonResultVO;
import com.win.utils.CreateUtils;
import com.win.utils.JDBC;
import com.win.utils.ResultUtil;
import com.win.vo.DataBaseVO;
import com.win.vo.TableColumnData;

@RestController
public class CheckDatabaseController {

    public static Map<String, List<TableColumnData>> map;

    /**
     * 首页
     */
    @PostMapping("/getTableData")
    public CommonResultVO<?> checkLink(DataBaseVO queryVO, HttpServletRequest request) {
        Connection connection = null;
        try {
            try {
                connection = JDBC.getConnection(queryVO);
            } catch (ClassNotFoundException e) {
                System.out.println("类加载不到");
            }
        } catch (SQLException e) {
            if (e.getErrorCode() == 1045) {
                return ResultUtil.fail("用户名或密码错误");
            } else if (e.getErrorCode() == 1049) {
                return ResultUtil.fail("指定数据库名不存在");
            } else if (e.getErrorCode() == 0) {
                return ResultUtil.fail("指定链接地址错误");
            } else {
                return ResultUtil.fail("校验失败,请重试");
            }

        }
        map = CreateUtils.setTableData(connection, queryVO);
        List<String> resultList = new ArrayList<>();
        for (String str : map.keySet()) {
            resultList.add(str);
        }
        if (connection != null) {
            return ResultUtil.success("success", resultList);
        }
        return ResultUtil.fail("系统错误 请重新运行");

    }
}

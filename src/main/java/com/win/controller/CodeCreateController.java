package com.win.controller;

import java.util.List;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.win.common.CommonResultVO;
import com.win.utils.GenerateFactory;
import com.win.utils.ResultUtil;
import com.win.vo.GeneratePath;
import com.win.vo.TableColumnData;

@RestController
public class CodeCreateController {

    /**
     * 构造数据
     */
    @PostMapping("/createData")
    public CommonResultVO<?> createData(String tableNames, String userName, String packageName, String savePath) {
        if (StringUtils.isBlank(tableNames)) {
            return ResultUtil.fail("未选择任何行表值");
        }

        if (StringUtils.isBlank(userName)) {
            return ResultUtil.fail("用户名不能为空");
        }
        if (StringUtils.isBlank(packageName)) {
            return ResultUtil.fail("包名不能为空");
        }

        if (StringUtils.isBlank(savePath)) {
            return ResultUtil.fail("保存路径不能为空");
        }
        GeneratePath pathSource = new GeneratePath();
        pathSource.setBussiPackage(packageName);
        pathSource.setDefaultUserName(userName);
        pathSource.setSavePathUrl(savePath);
        for (String str : tableNames.split("∝")) {
            String tableName = str.split("∞")[0].replace(" ", "").trim();
            String tableComment = str.split("∞")[1].replace(" ", "").trim();

            if (CheckDatabaseController.map == null || CheckDatabaseController.map.size() <= 0) {
                return ResultUtil.fail("请重新获取表数据");
            }
            List<TableColumnData> columnDataList = CheckDatabaseController.map.get(str);
            System.out.println(JSON.toJSONString(columnDataList));
            GenerateFactory.codeGenerate(tableName, tableComment, columnDataList, pathSource);
        }
        CheckDatabaseController.map = null;
        return ResultUtil.success("ok");
    }
}

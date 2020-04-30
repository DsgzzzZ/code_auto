package com.win.utils;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.StringUtils;
import org.apache.velocity.VelocityContext;

import com.win.vo.GeneratePath;
import com.win.vo.TableColumnData;

public class GenerateFactory {

	private static String projectPath;
	static {
		projectPath = CommonsUtils.getProjectPath();
	}

	public static void codeGenerate(String tableName, String tableComment, List<TableColumnData> columnDataList,
			GeneratePath pathSource) {
		String className = CommonsUtils.getTableNameToClassName(tableName);
		String lowerName = CommonsUtils.getClassNameToLowerName(className);

		// sourceFileName

		String voSourceFileName = className + "Vo.java";
		String poSourceFileName = className + ".java";
		String daoSourceFileName = className + "Dao.java";
		String serviceSourceFileName = className + "Service.java";
		String serviceImplSourceFileName = className + "ServiceImpl.java";
		String controllerSourceFileName = className + "Controller.java";
		String sqlMapperSourceFileName = className + "Mapper.xml";

		// filePath+name
		/*
		 * String voFilePath = "/vo/" + voSourceFileName; String queryVOFilePath =
		 * "/vo/queryvo/" + queryVOSourceFileName;
		 */
		String bussPackage = pathSource.getBussiPackage();
		System.out.println(bussPackage);
		System.out.println(bussPackage.replaceAll("\\.", "\\/"));
		String poFilePath = "/src/main/java/" + pathSource.getBussiPackage().replaceAll("\\.", "\\/") + "/model/po/"
				+ poSourceFileName;

		String voFilePath = "/src/main/java/" + pathSource.getBussiPackage().replaceAll("\\.", "\\/") + "/model/vo/"
				+ voSourceFileName;

		String daoFilePath = "/src/main/java/" + pathSource.getBussiPackage().replaceAll("\\.", "\\/") + "/dao/"
				+ daoSourceFileName;
		String servicePath = "/src/main/java/" + pathSource.getBussiPackage().replaceAll("\\.", "\\/") + "/service/"
				+ "/" + serviceSourceFileName;
		String serviceImplPath = "/src/main/java/" + pathSource.getBussiPackage().replaceAll("\\.", "\\/")
				+ "/service/impl/" + serviceImplSourceFileName;
		String controllerPath = "/src/main/java/" + pathSource.getBussiPackage().replaceAll("\\.", "\\/")
				+ "/controller/" + controllerSourceFileName;
		String sqlMapperPath = "/src/main/resources/mapper/" + sqlMapperSourceFileName;

		VelocityContext context = new VelocityContext();
		context.put("className", className);
		context.put("lowerName", lowerName);
		context.put("tableComment", tableComment);
		context.put("tableName", tableName);
		context.put("bussPackage", pathSource.getBussiPackage());
		context.put("createTime", CommonsUtils.dateFormatToDate(new Date()));
		context.put("userName", StringUtils.isBlank(pathSource.getDefaultUserName()) ? System.getenv().get("USERNAME")
				: pathSource.getDefaultUserName());

		try {
			context.put("feilds", CreateUtils.getBeanFeilds(columnDataList, false));
			context.put("feildsVO", CreateUtils.getBeanFeilds(columnDataList, true));

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("转换表字段时出现错误：" + e.getMessage());
		}

		try {
			for (TableColumnData data : columnDataList) {
				//System.out.println(JSON.toJSONString(data));
				if ("PRI".equals(data.key)) {
					context.put("key", data);
				}
				if ("version".equals(data.columnName)) {
					context.put("version", data);
				}
			}
			context.put("columnDatas", columnDataList);
			context.put("fristFeildName", columnDataList.get(0).getFieldName());
			context.put("fristFeildNameSet", CommonsUtils.toGetSetMethod(columnDataList.get(0).getFieldName()));

			Map<?, ?> sqlMap = AutoCreateSql.getAutoCreateSql(tableName, columnDataList);

			context.put("SQL", sqlMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Object contexttemp : context.getKeys()) {
			System.out.println(contexttemp + ":" + context.get(contexttemp + ""));
		}

		System.out.println("准备生成文件");
		PageParserUtils.WriterPage(context, "PoTemplate.ftl", pathSource.getSavePathUrl(), poFilePath,
				poSourceFileName);

		PageParserUtils.WriterPage(context, "VoTemplate.ftl", pathSource.getSavePathUrl(), voFilePath,
				voSourceFileName);

		/*
		 * PageParserUtils.WriterPage(context, "QueryPageTemplate.ftl",
		 * pathSource.getSavePathUrl(), queryVOFilePath, queryVOSourceFileName);
		 * PageParserUtils.WriterPage(context, "PageTemplate.ftl",
		 * pathSource.getSavePathUrl(), voFilePath, voSourceFileName);
		 */
		PageParserUtils.WriterPage(context, "DaoTemplate2.ftl", pathSource.getSavePathUrl(), daoFilePath,
				daoSourceFileName);
		PageParserUtils.WriterPage(context, "ServiceTemplate2.ftl", pathSource.getSavePathUrl(), servicePath,
				serviceSourceFileName);
		PageParserUtils.WriterPage(context, "ServiceTemplateImpl2.ftl", pathSource.getSavePathUrl(), serviceImplPath,
				serviceImplSourceFileName);
		PageParserUtils.WriterPage(context, "MapperTemplate2.xml", pathSource.getSavePathUrl(), sqlMapperPath,
				sqlMapperSourceFileName);
		PageParserUtils.WriterPage(context, "ControllerTemplate2.ftl", pathSource.getSavePathUrl(), controllerPath,
				controllerSourceFileName);


	}

	public static void main(String[] args) {
		GeneratePath pathSource = new GeneratePath();
		pathSource.setBussiPackage("com.win");
		String className = "APPVersion";
		// path
		String srcPath = projectPath + "src/main/java/" + pathSource.getBussiPackageUrl() + "/";
		String voPath = srcPath + pathSource.getVoPackageUrl() + "/";
		String entityPath = srcPath + pathSource.getEntityPackage();
		String entityPathUrl = srcPath + pathSource.getEntityPackageUrl() + "/";

		// filePath+name
		String voFilePath = voPath + className + "VO.java";
		String queryVOFilePath = voPath + className + "QueryVO.java";
		String entityFilePath = entityPathUrl + className + ".java";
		String daoFilePath = "mapper/" + "/" + className + "Mapper.java";
		String servicePath = "service/" + "/I" + className + "Service.java";
		String serviceImplPath = "service/impl/" + className + "ServiceImpl.java";
		String controllerPath = "controller/" + className + "Controller.java";
		String sqlMapperPath = "mapper/xml/" + className + "Mapper.xml";

		System.out.println("srcPath" + srcPath);
		System.out.println("voPath" + voPath);
		System.out.println("entityPath" + entityPath);
		System.out.println("entityPathUrl" + entityPathUrl);
		System.out.println("voFilePath" + voFilePath);
		System.out.println("queryVOFilePath" + queryVOFilePath);
		System.out.println("entityFilePath" + entityFilePath);
		System.out.println("daoFilePath" + daoFilePath);
		System.out.println("servicePath" + servicePath);
		System.out.println("serviceImplPath" + serviceImplPath);
		System.out.println("controllerPath" + controllerPath);
		System.out.println("sqlMapperPath\r\t" + sqlMapperPath);
	}
}

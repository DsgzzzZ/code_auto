package ${bussPackage}.service;

import ${bussPackage}.model.po.${className};
import ${bussPackage}.model.vo.${className}Vo;
import java.util.List;
import java.util.LinkedHashMap;

/**
 * ${tableComment} 服务层
 * 
 * @author ${userName}
 * @date ${createTime}
 */
public interface ${className}Service
{
	/**
     * 查询${tableComment}信息
     * 
     * @param ${key.fieldName} ${key.fieldType}
     * @return ${tableComment}信息
     */
	public ${className}Vo getById(${key.fieldType} ${key.fieldName});

    /**
    * 查询${tableComment}一个信息
    *
    * @return ${tableComment}信息
    */
    public ${className}Vo getOne(${className}Vo ${lowerName}Vo);
	
	/**
     * 查询${tableComment}列表
     * 
     * @param ${lowerName}Vo ${tableComment}信息
     * @return ${tableComment}集合
     */
	public List<${className}Vo> getList(${className}Vo ${lowerName}Vo);
	
	/**
     * 新增${tableComment}
     * 
     * @param ${lowerName} ${tableComment}信息
     * @return 结果
     */
	public int insert(${className} ${lowerName});
	
	/**
     * 修改${tableComment}
     * 
     * @param ${lowerName} ${tableComment}信息
     * @return 结果
     */
	public int update(${className} ${lowerName});
		
	/**
     * 删除${tableComment}信息
     * 
     * @param ${key.fieldName}s 需要删除的数据ID
     * @return 结果
     */
	public int deleteByIds(Long[] ${key.fieldName}s);

    //********************************** 使用LinkedHashMap作参数 *************************************

    public int insertByBatch(List<${className}> list);

    int updateBatch(LinkedHashMap<String, String> keyvalue, LinkedHashMap<String, String> condition);

    ${className}Vo getOneByCondition(LinkedHashMap<String, String> condition, String field);

    int getCountByCondition(LinkedHashMap<String, String> condition);

    List<${className}Vo> getAllByCondition(LinkedHashMap<String, String> condition, String field);

    List<${className}Vo> getListByCondition(LinkedHashMap<String, String> condition, int pageNo, int pageSize, String order);

    int delBySign(String id);

    String groupConcatByField(String field, LinkedHashMap<String, String> condition);

    void deleteByCondition(LinkedHashMap<String, String> condition);

}

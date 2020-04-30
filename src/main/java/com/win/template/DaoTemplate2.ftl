package ${bussPackage}.dao;

import ${bussPackage}.model.po.${className};
import ${bussPackage}.model.vo.${className}Vo;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.LinkedHashMap;
import org.apache.ibatis.annotations.Param;

/**
 * ${tableComment} 数据层
 * 
 * @author ${userName}
 * @date ${createTime}
 */
@Mapper
@Repository
public interface ${className}Dao
{
	/**
     * 查询${tableComment}信息
     * 
     * @param ${key.fieldName} ${className} ID
     * @return ${tableComment}信息
     */
	public ${className}Vo getById(${key.fieldType} ${key.fieldName});

    /**
    * 查询${tableComment}一个信息
    *
    * @param ${key.fieldName} ${key.fieldType}
    * @return ${tableComment}信息
    */
    public ${className}Vo getOne(${className}Vo ${lowerName}Vo);

	/**
     * 查询${tableComment}列表
     * 
     * @param ${lowerName}Vo ${className}信息
     * @return ${tableComment}集合
     */
	public List<${className}Vo> getList(${className}Vo ${lowerName}Vo);
	
	/**
     * 新增${tableComment}
     * 
     * @param ${lowerName} ${className} 信息
     * @return 结果
     */
	public int insert(${className} ${lowerName});
	
	/**
     * 修改${tableComment}
     * 
     * @param  ${lowerName}  ${tableComment}信息
     * @return 结果
     */
	public int update(${className} ${lowerName});
	
	/**
     * 删除${tableComment}
     * 
     * @param ${key.fieldName} ${tableComment}ID
     * @return 结果
     */
	public int deleteById(${key.fieldType} ${key.fieldName});
	
	/**
     * 批量删除${tableComment}
     * 
     * @param ${key.fieldName}s 需要删除的数据ID
     * @return 结果
     */
	public int deleteByIds(Long[] ${key.fieldName}s);


    //********************************** 使用LinkedHashMap作参数 *************************************

    int insertByBatch(@Param("list") List<${className}> list);

    int updateBatch(@Param("keyvalue") LinkedHashMap<String, String> keyvalue,@Param("condition") LinkedHashMap<String, String> condition);

    ${className}Vo getOneByCondition(@Param("condition") LinkedHashMap<String, String> condition, @Param("field") String field);

    int getCountByCondition(@Param("condition") LinkedHashMap<String, String> condition);

    List<${className}Vo> getAllByCondition(@Param("condition") LinkedHashMap<String, String> condition, @Param("field") String field);

    List<${className}Vo> getListByCondition(@Param("condition") LinkedHashMap<String, String> condition, @Param("offset") int offset, @Param("limit") int limit, @Param("order") String order);

    int delBySign(@Param("id") String id);

    String groupConcatByField(@Param("field") String field, @Param("condition") LinkedHashMap<String, String> condition);

    void deleteByCondition(@Param("condition") LinkedHashMap<String, String> condition);
	
}
package ${bussPackage}.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${bussPackage}.dao.${className}Dao;
import ${bussPackage}.model.po.${className};
import ${bussPackage}.model.vo.${className}Vo;
import ${bussPackage}.service.${className}Service;
import java.util.LinkedHashMap;


/**
 * ${tableComment} 服务层实现
 * 
 * @author ${userName}
 * @date ${createTime}
 */
@Service
public class ${className}ServiceImpl implements ${className}Service
{
	@Autowired
	private ${className}Dao ${lowerName}Dao;

	/**
     * 查询${tableComment}信息
     * 
     * @param ${key.fieldName} ${key.fieldType}
     * @return ${tableComment}信息
     */
    @Override
	public ${className}Vo getById(${key.fieldType} ${key.fieldName})
	{
	    return ${lowerName}Dao.getById(${key.fieldName});
	}

    /**
    * 查询${tableComment}一个信息
    *
    * @return ${tableComment}信息
    */
    @Override
    public ${className}Vo getOne(${className}Vo ${lowerName}Vo)
    {
        return ${lowerName}Dao.getOne(${lowerName}Vo);
    }
	
	/**
     * 查询${tableComment}列表
     * 
     * @param ${lowerName}Vo ${className}Vo 信息
     * @return ${tableComment}集合
     */
	@Override
	public List<${className}Vo> getList(${className}Vo ${lowerName}Vo)
	{
	    return ${lowerName}Dao.getList(${lowerName}Vo);
	}
	
    /**
     * 新增${tableComment}
     * 
     * @param ${lowerName} ${classname} ${tableComment}信息
     * @return 结果
     */
	@Override
	public int insert(${className}  ${lowerName})
	{
	    return ${lowerName}Dao.insert(${lowerName});
	}
	
	/**
     * 修改${tableComment}
     * 
     * @param ${lowerName} ${className} ${tableComment}信息
     * @return 结果
     */
	@Override
	public int update(${className} ${lowerName})
	{
	    return ${lowerName}Dao.update(${lowerName});
	}

	/**
     * 删除${tableComment}对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteByIds(Long[] ids)
	{
		return ${lowerName}Dao.deleteByIds(ids);
	}

    //********************************** 使用LinkedHashMap作参数 *************************************

    @Override
    public int insertByBatch(List<${className}> list) {
        return ${lowerName}Dao.insertByBatch(list);
    }

    public int updateBatch(LinkedHashMap<String, String> keyvalue,LinkedHashMap<String, String> condition) {
        return ${lowerName}Dao.updateBatch(keyvalue,condition);
    }

    @Override
    public ${className}Vo getOneByCondition(LinkedHashMap<String, String> condition, String field) {
        return ${lowerName}Dao.getOneByCondition(condition, field);
    }

    @Override
    public int getCountByCondition(LinkedHashMap<String, String> condition) {
        return ${lowerName}Dao.getCountByCondition(condition);
    }

    @Override
    public List<${className}Vo> getAllByCondition(LinkedHashMap<String, String> condition, String field) {
        return ${lowerName}Dao.getAllByCondition(condition, field);
    }

    @Override
    public List<${className}Vo> getListByCondition(LinkedHashMap<String, String> condition, int pageNo, int pageSize, String order) {
        int offset = (pageNo - 1) * pageSize;
        int limit = pageSize;
        return ${lowerName}Dao.getListByCondition(condition, offset, limit, order);
    }

    @Override
    public int delBySign(String id) {
        return ${lowerName}Dao.delBySign(id);
    }

    @Override
    public String groupConcatByField(String field, LinkedHashMap<String, String> condition) {
        return this.${lowerName}Dao.groupConcatByField(field, condition);
    }

    @Override
    public void deleteByCondition(LinkedHashMap<String, String> condition) {
        this.${lowerName}Dao.deleteByCondition(condition);
    }

}

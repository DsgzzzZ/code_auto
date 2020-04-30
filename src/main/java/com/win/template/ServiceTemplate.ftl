package ${bussPackage}.service;

import com.baomidou.mybatisplus.service.IService;
import com.baomidou.mybatisplus.plugins.Page;
import ${bussPackage}.vo.${className}VO;
import ${bussPackage}.queryvo.${className}QueryVO;
import ${bussPackage}.entity.${className};



/**
 * @ClassName I${className}Service
 * @Description TODO(${tableComment}-接口类)
 * @Date ${createTime}
 * @author ${userName}
 */
public interface  I${className}Service extends IService<${className}>{
	
  
  
    /**
     * @Description (分页查询列表)
     * @param query
     * @return
     */
    Page<${className}VO> getListData(${className}QueryVO query);
}

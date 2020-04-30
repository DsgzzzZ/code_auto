package ${bussPackage}.controller;


import ${bussPackage}.entity.${className};
import ${bussPackage}.common.CommonResultVO;
import ${bussPackage}.utils.ResultUtil;
import ${bussPackage}.vo.${className}VO;
import ${bussPackage}.service.I${className}Service;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ${bussPackage}.queryvo.${className}QueryVO;
import org.springframework.web.bind.annotation.RestController;
import com.baomidou.mybatisplus.plugins.Page;
 

/**
 * @ClassName ${className}Controller
 * @Description TODO(${tableComment}-Controller 调用接口)
 * @Date ${createTime}
 * @author ${userName}
 */
@RestController
@RequestMapping("/${lowerName}") 
public class ${className}Controller{
	@SuppressWarnings("unused")
	 private static final Logger logger = LoggerFactory.getLogger(${className}Controller.class);
	
	@Autowired
	private I${className}Service  ${lowerName}Service; 
	
	
    /**
     * @Description (条件查询数据列表)
     * @param query
     * @param size
     * @param currentPage 页码
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/getListData", method=RequestMethod.POST)
    public CommonResultVO<?> getListData(${className}QueryVO query, HttpServletRequest request) {
        Page<${className}VO> result = ${lowerName}Service.getListData(query);
        return ResultUtil.success("ok", result);
    }
	
	
	
	  /**
     * @Description (更新)
     * @param entity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/update", method=RequestMethod.POST)
    public ResponseBean update${className}(${className} entity) {
        try {
            ${lowerName}Service.update(json);
            return new ResponseBean("success", AppRpcCode.SUCCESS_CODE, "");            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean("faild", AppRpcCode.ERROR_CODE, "");    
        }
    }

    /**
     * @Description (插入)
     * @param entity
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public ResponseBean add${className}(${className} entity) {
      
         try {
            ${lowerName}Service.insert(entity);
            return new ResponseBean("success", AppRpcCode.SUCCESS_CODE, "");            
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean("faild", AppRpcCode.ERROR_CODE, "");    
        }
    }

	
	
    /**
     * @Description (删除)
     * @param ${fristFeildName}
     * @return
     */
    @ResponseBody
    @RequestMapping(value="/delete", method=RequestMethod.POST)
    public ResponseBean delete${className}(Integer ${fristFeildName}) {
        if (${fristFeildName} == null) {
        	return new ResponseBean("faild", AppRpcCode.ERROR_CODE, "ID不能为空");  
        }
        ${className} entity = new ${className}();
        entity.set${fristFeildNameSet}(${fristFeildName});
         try {
            ${lowerName}Service.updateById(entity);           
            return new ResponseBean("success", AppRpcCode.SUCCESS_CODE, "");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseBean("faild", AppRpcCode.ERROR_CODE, "");
        }
    }
	

}

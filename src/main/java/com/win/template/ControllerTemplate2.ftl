package ${bussPackage}.controller;



import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.htgx.res.model.params.Param;
import com.htgx.databank.core.logging.Log;
import com.htgx.res.object.ResponseVo;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.GetMapping;

import com.htgx.databank.cotent.RestApi;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import ${bussPackage}.model.po.${className};
import ${bussPackage}.model.vo.${className}Vo;
import ${bussPackage}.service.${className}Service;
import com.htgx.res.base.controller.BaseController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.htgx.res.enums.ResponseStatusEnum;
import com.htgx.res.utils.ResultResponseUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htgx.res.object.PageResultVo;
import org.springframework.validation.BindingResult;
import javax.validation.Valid;

/**
 *  @className ${className}Controller
 * @Description TODO(${tableComment}-Controller 调用接口)
 * 
 * @Date ${createTime}
 * @author ${userName}
 */
@RestController
@RequestMapping("/${lowerName}")
public class ${className}Controller extends BaseController {
    private static final Logger logger = Log.get();
    private String prefix = "${lowerName}/${className}";

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private HttpServletResponse response;

	@Autowired
	private ${className}Service ${lowerName}Service;

	/**
	 * 查询${tableComment}列表
	 */
	@ResponseBody
	@RequestMapping(value="/getList")
	public ResponseVo getList(@Valid @RequestBody ${className}Vo ${lowerName}Vo, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.INVALID_PARAMS.getCode(),bindingResult.getFieldError().getDefaultMessage(),null);
		}
		PageHelper.startPage(${lowerName}Vo.getPageNo(),${lowerName}Vo.getPageSize());
		${lowerName}Vo.setTbStatus(1);
		List<${className}Vo> list = ${lowerName}Service.getList(${lowerName}Vo);
		PageInfo<${className}Vo> pageInfo = new PageInfo<${className}Vo>(list);
		PageResultVo pageResultVo = new PageResultVo();
		pageResultVo.setRows(pageInfo.getList());
		pageResultVo.setTotal(pageInfo.getTotal());
		return ResultResponseUtil.restRespose(ResponseStatusEnum.SUCCESS.getCode(),ResponseStatusEnum.SUCCESS.getMessage(),pageResultVo);
	}
	

	/**
	 * 新增${tableComment}
	 */
	@ResponseBody
	@RequestMapping(value="/add${className}")
	public ResponseVo add${className}(@Valid @RequestBody ${className} ${lowerName}, BindingResult bindingResult){
		if(bindingResult.hasErrors()){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.INVALID_PARAMS.getCode(),bindingResult.getFieldError().getDefaultMessage(),null);
		}
		int handleFlag = ${lowerName}Service.insert(${lowerName});
		if(handleFlag<=0){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.REQUEST_RETRY.getCode(),ResponseStatusEnum.REQUEST_RETRY.getMessage(),null);
		}
		return ResultResponseUtil.restRespose(ResponseStatusEnum.SUCCESS.getCode(),ResponseStatusEnum.SUCCESS.getMessage(),null);
	}

	/**
	 * 获取 ${tableComment}依据ID
	 */
	@ResponseBody
	@RequestMapping("/getById")
	public ResponseVo get${className}(@RequestBody Param param){
		if(param.get${fristFeildNameSet}()==null){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.INVALID_PARAMS.getCode(),"id不能为空",null);
		}
		${className}Vo ${lowerName}Vo = ${lowerName}Service.getById(param.get${fristFeildNameSet}());
		if(${lowerName}Vo==null){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.NOT_EXIST_DATA.getCode(),ResponseStatusEnum.NOT_EXIST_DATA.getMessage(),null);
		}
		return ResultResponseUtil.restRespose(ResponseStatusEnum.SUCCESS.getCode(),ResponseStatusEnum.SUCCESS.getMessage(),${lowerName}Vo);
	}
	
	/**
	 * 修改${tableComment}
	 */
	@ResponseBody
	@RequestMapping(value="/edit${className}")
	public ResponseVo edit${className}(@Valid @RequestBody ${className} ${lowerName}, BindingResult bindingResult){
		if(${lowerName}.get${fristFeildNameSet}()==null){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.INVALID_PARAMS.getCode(),"id不能为空",null);
		}
		if(bindingResult.hasErrors()){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.INVALID_PARAMS.getCode(),bindingResult.getFieldError().getDefaultMessage(),null);
		}
		${className}Vo ${lowerName}Vo = ${lowerName}Service.getById(${lowerName}.get${fristFeildNameSet}());
		if(${lowerName}Vo==null || ${lowerName}Vo.getTbStatus()==0){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.NOT_EXIST_DATA.getCode(),ResponseStatusEnum.NOT_EXIST_DATA.getMessage(),null);
		}
		${lowerName}.setVersion(${lowerName}Vo.getVersion());

		int handleFlag = ${lowerName}Service.update(${lowerName});
		if(handleFlag<=0){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.REQUEST_RETRY.getCode(),ResponseStatusEnum.REQUEST_RETRY.getMessage(),null);
		}
		return ResultResponseUtil.restRespose(ResponseStatusEnum.SUCCESS.getCode(),ResponseStatusEnum.SUCCESS.getMessage(),null);
	}
	
	/**
	 * 删除${tableComment}
	 */
	@ResponseBody
	@RequestMapping(value="/delByIds")
	public ResponseVo del${className}(@RequestBody Param param){

		if(param.getIds()==null || param.getIds().length<=0){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.INVALID_PARAMS.getCode(),"至少选择一条数据",null);
		}
		${className}Vo ${lowerName}Vo = null;
		for(int i=0;i<param.getIds().length;i++){
			${lowerName}Vo = ${lowerName}Service.getById(param.getIds()[i]);
			if(${lowerName}Vo==null || ${lowerName}Vo.getTbStatus()==0){
				return ResultResponseUtil.restRespose(ResponseStatusEnum.NOT_EXIST_DATA.getCode(),ResponseStatusEnum.NOT_EXIST_DATA.getMessage(),null);
			}
		}

		int handleFlag = ${lowerName}Service.deleteByIds(param.getIds());
		if(handleFlag<=0){
			return ResultResponseUtil.restRespose(ResponseStatusEnum.REQUEST_RETRY.getCode(),ResponseStatusEnum.REQUEST_RETRY.getMessage(),null);
		}
		return ResultResponseUtil.restRespose(ResponseStatusEnum.SUCCESS.getCode(),ResponseStatusEnum.SUCCESS.getMessage(),null);
	}
	
}

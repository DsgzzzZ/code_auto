package ${bussPackage}.mapper;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import ${bussPackage}.entity.${className};
import ${bussPackage}.vo.${className}VO;
import java.util.List;

/**
 * @ClassName ${className}Mapper
 * @Description TODO(${tableComment}-Mapper类)
 * @Date ${createTime}
 * @author ${userName}
 */
public interface ${className}Mapper extends BaseMapper<${className}> {

     /**
     * @Description (分页查询信息)
     * @param map
     * @return
     */
    List<${className}VO> getListData(Page<${className}VO> page,EntityWrapper<${className}VO> ew);

}

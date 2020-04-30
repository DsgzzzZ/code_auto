package ${bussPackage}.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import ${bussPackage}.entity.${className};
import ${bussPackage}.service.I${className}Service;
import ${bussPackage}.mapper.${className}Mapper;
import com.baomidou.mybatisplus.plugins.Page;
import ${bussPackage}.vo.${className}VO;
import ${bussPackage}.queryvo.${className}QueryVO;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import java.util.List;

/**
 * @ClassName ${className}ServiceImpl
 * @Description TODO(${tableComment}-接口实现类)
 * @Date ${createTime}
 * @author ${userName}
 */
@Service
public class ${className}ServiceImpl  extends ServiceImpl<${className}Mapper, ${className}> implements I${className}Service {

    @Override
    public Page<${className}VO> getListData(${className}QueryVO query) {
        Page<${className}VO> page = new Page<>(query.getCurrentPage(), query.getSize());
        EntityWrapper<${className}VO> ew = new EntityWrapper<>();
        List<${className}VO> dataList = baseMapper.getListData(page, ew);
        return page.setRecords(dataList);
    }
}

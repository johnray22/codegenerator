package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import java.util.List;

/**
* <p>
* ${table.comment!} 服务实现类
* </p>
*
* @author ${author}
* @since ${date}
*/
@Slf4j
@Service
<#if kotlin>
    open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

    }
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

    @Override
    public List<${entity}> getAll() {
        log.info("正在查询所有数据");
        return list();
    }


    @Override
    public ${entity} get${entity}ById(int id) {
        ${entity} ${entity?uncap_first} = getById(id);
        log.info("查询id为{}的${entity?uncap_first}{}",id,(null == ${entity?uncap_first}?"无":"成功"));
        return ${entity?uncap_first};
    }

    @Override
    public boolean insert${entity}(${entity} ${entity?uncap_first}) {
        if (save(${entity?uncap_first})) {
            log.info("插入${entity?uncap_first}成功");
            return true;
        } else {
            log.error("插入${entity?uncap_first}失败");
            throw new ViewException("添加失败");
        }
    }

    @Override
    public boolean delete${entity}ById(int id) {
        if (removeById(id)) {
            log.info("删除${entity?uncap_first}成功");
            return true;
        } else {
            log.error("删除${entity?uncap_first}失败");
            throw new ViewException("删除${entity?uncap_first}失败");
        }
    }

    @Override
    public boolean update${entity}(${entity} ${entity?uncap_first}) {
        if (updateById(${entity?uncap_first})) {
            log.info("更新${entity?uncap_first}成功");
            return true;
        } else {
            log.error("更新${entity?uncap_first}失败");
            throw new ViewException("更新${entity?uncap_first}失败");
        }
    }

}
</#if>

package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import java.util.List;

/**
* <p>
* ${table.comment!} 服务接口
* </p>
*
* @author ${author}
* @since ${date}
*/
<#if kotlin>
    interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} {

    /**
    * 查询所有
    */
    List<${entity}> getAll();

    /**
    * 根据id查询${entity}
    */
    ${entity} get${entity}ById(int id);

    /**
    * 插入${entity}
    */
    boolean insert${entity}(${entity} ${entity?uncap_first});

    /**
    * 根据id删除${entity}
    */
    boolean delete${entity}ById(int id);

    /**
    * 根据id更新${entity}
    */
    boolean update${entity}(${entity} ${entity?uncap_first});

}
</#if>

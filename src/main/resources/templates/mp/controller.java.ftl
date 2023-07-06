package ${package.Controller};


import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import ${package.Entity}.dto.ResEntity;
import ${package.Service}.${table.serviceName};
import ${package.Entity}.${entity};
<#if restControllerStyle>
import org.springframework.web.bind.annotation.RestController;
<#else>
import org.springframework.stereotype.Controller;
</#if>
<#if superControllerClassPackage??>
import ${superControllerClassPackage};
</#if>
import javax.annotation.Resource;


/**
 * <p>
 * ${table.comment!} Controller
 * </p>
 *
 * @author ${author}
 * @date ${date}
 */
<#if restControllerStyle>
@RestController
<#else>
@Controller
</#if>
@RequestMapping("<#if package.ModuleName??>/${package.ModuleName}</#if>/api/v1/<#if controllerMappingHyphenStyle??>${controllerMappingHyphen}<#else>${table.entityPath}</#if>")
<#if kotlin>
class ${table.controllerName}<#if superControllerClass??> : ${superControllerClass}()</#if>
<#else>
    <#if superControllerClass??>
public class ${table.controllerName} extends ${superControllerClass} {
    <#else>
public class ${table.controllerName} {
    </#if>

    @Resource
    private ${table.serviceName} ${table.serviceName?uncap_first};

    /**
    * 查询所有数据
    */
    @GetMapping("/all")
    public ResEntity<?> getAll() {
        return new ResEntity<>(${table.serviceName?uncap_first}.getAll());
    }

    /**
    * 根据id查询
    */
    @GetMapping("/byid/{id}")
    public ResEntity<?> getById(@PathVariable("id") Integer id) {
        return new ResEntity<>(${table.serviceName?uncap_first}.get${entity}ById(id));
    }

    /**
    * 新增
    */
    @PostMapping("/new")
    public ResEntity<?> insert(@RequestBody ${entity} ${entity?uncap_first}) {
        return new ResEntity<>(${table.serviceName?uncap_first}.insert${entity}(${entity?uncap_first}));
    }

    /**
    * 删除
    */
    @DeleteMapping("/byid/{id}")
    public ResEntity<?> deleteById(@PathVariable("id") Integer id) {
        return new ResEntity<>(${table.serviceName?uncap_first}.delete${entity}ById(id));
    }

    /**
    * 修改
    */
    @PutMapping("/byid/{id}")
    public ResEntity<?> updateById(@RequestBody ${entity} ${entity?uncap_first}) {
        return new ResEntity<>(${table.serviceName?uncap_first}.update${entity}(${entity?uncap_first}));
    }
}
</#if>
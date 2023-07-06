package ${package.Mapper};

import ${package.Entity}.${entity};
import ${superMapperClassPackage};
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


/**
* <p>
* ${table.comment!} Mapper
* </p>
*
* @author ${author}
* @since ${date}
*/
@Mapper
@Repository
<#if kotlin>
    interface ${table.mapperName} : ${superMapperClass}<${entity}>
<#else>
public interface ${table.mapperName} extends ${superMapperClass}<${entity}> {

}
</#if>

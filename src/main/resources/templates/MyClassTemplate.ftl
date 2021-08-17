package com.morningcake.codegen.template;

import com.morningcake.codegen.annotations.MyClass;
import com.morningcake.codegen.annotations.MyField;
import lombok.*;

@MyClass(tag = "TagName")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class ${className} {

<#list fields as field>
    @MyField(tag = "TagName")
    @Builder.Default
    private ${field.type} ${field.name} = <#if field.type == "String">"${field.value}"<#else>${field.value?c}</#if>;

</#list>
}
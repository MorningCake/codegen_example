package com.morningcake.codegen.template;

import com.morningcake.codegen.annotations.MyClass;
import com.morningcake.codegen.annotations.MyField;
import lombok.*;

@MyClass(tag = "TagName")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyClassExample {

    @MyField(tag = "TagName1")
    @Builder.Default
    private String fieldName1 = "Str";

    @MyField(tag = "TagName1")
    @Builder.Default
    private Double fieldName2 = 2.6;

    @MyField(tag = "TagName1")
    @Builder.Default
    private Integer fieldName3 = 5;
}

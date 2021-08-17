package com.morningcake.codegen.template;

import com.morningcake.codegen.annotations.MyClass;
import com.morningcake.codegen.annotations.MyField;
import lombok.*;

@MyClass(tag = "TagName")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Animal {

    @MyField(tag = "TagName")
    @Builder.Default
    private String name = "Tom";

    @MyField(tag = "TagName")
    @Builder.Default
    private String class = "cat";

}
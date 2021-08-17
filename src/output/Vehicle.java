package com.morningcake.codegen.template;

import com.morningcake.codegen.annotations.MyClass;
import com.morningcake.codegen.annotations.MyField;
import lombok.*;

@MyClass(tag = "TagName")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Vehicle {

    @MyField(tag = "TagName")
    @Builder.Default
    private String brand = "Toyota";

    @MyField(tag = "TagName")
    @Builder.Default
    private String model = "Corolla";

    @MyField(tag = "TagName")
    @Builder.Default
    private Integer gen = 10;

    @MyField(tag = "TagName")
    @Builder.Default
    private String color = "white";

    @MyField(tag = "TagName")
    @Builder.Default
    private Double volume = 1.8;

}
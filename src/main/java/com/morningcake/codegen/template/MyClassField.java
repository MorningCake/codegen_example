package com.morningcake.codegen.template;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyClassField {
    private String name;
    private String type;
    private Object value;
}

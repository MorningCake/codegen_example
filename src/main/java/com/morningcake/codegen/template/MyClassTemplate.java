package com.morningcake.codegen.template;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MyClassTemplate {

    private String className;
    private List<MyClassField> fields;
}


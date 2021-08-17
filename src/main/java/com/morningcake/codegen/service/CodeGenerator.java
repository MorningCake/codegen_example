package com.morningcake.codegen.service;

import freemarker.template.TemplateException;

import java.io.IOException;

/**
 *
 */
public interface CodeGenerator {

    /**
     *
     */
    void generateAll() throws IOException, TemplateException;
}

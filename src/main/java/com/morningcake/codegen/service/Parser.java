package com.morningcake.codegen.service;

import com.morningcake.codegen.template.MyClassTemplate;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

/**
 *
 */
public interface Parser {

    /**
     *
     * @param fullPath
     * @return
     */
    List<MyClassTemplate> parse(Path fullPath) throws IOException;
}

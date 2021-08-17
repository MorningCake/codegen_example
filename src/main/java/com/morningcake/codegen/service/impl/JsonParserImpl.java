package com.morningcake.codegen.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.morningcake.codegen.service.Parser;
import com.morningcake.codegen.template.MyClassField;
import com.morningcake.codegen.template.MyClassTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Slf4j
public class JsonParserImpl implements Parser {

    @Override
    public List<MyClassTemplate> parse(Path jsonPath) throws IOException, IllegalStateException {

        if (!Files.exists(jsonPath) || !Files.isReadable(jsonPath)) {
            throw new IllegalStateException("Файл " + jsonPath + "не найден!");
        }
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Map<String, Object>> jsonObjects = mapper.readValue(jsonPath.toFile(), new TypeReference<>() {});

        if (jsonObjects == null) {
            throw new IllegalStateException("Файл " + jsonPath + "не распарсен!");
        }
        if (jsonObjects.size() == 0) {
            throw new IllegalStateException("Файл " + jsonPath + "пуст!");
        }

        return jsonObjects.entrySet().stream().map(entry -> {
            MyClassTemplate myClass = new MyClassTemplate();
            myClass.setClassName(entry.getKey());
            List<MyClassField> fields =
                    entry.getValue().entrySet().stream().map(this::getTypeOfObject).collect(Collectors.toList());
            myClass.setFields(fields);
            return myClass;
        }).collect(Collectors.toList());
    }

    private MyClassField getTypeOfObject(Map.Entry<String, Object> field) {

        try {
            Integer integer = Integer.valueOf(field.getValue().toString());
            return new MyClassField(field.getKey(), Integer.class.getSimpleName(), integer);
        } catch (Exception ex) {}

        try {
            Double dbl = Double.valueOf(field.getValue().toString());
            return new MyClassField(field.getKey(), Double.class.getSimpleName(), dbl);
        } catch (Exception ex) {}

        try {
            String str = String.valueOf(field.getValue().toString());
            return new MyClassField(field.getKey(), String.class.getSimpleName(), str);
        } catch (Exception ex) {}

        return new MyClassField(field.getKey(), Object.class.getSimpleName(), field.getValue());
    }
}

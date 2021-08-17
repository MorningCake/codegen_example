package com.morningcake.codegen.service.impl;

import com.morningcake.codegen.service.CodeGenerator;
import com.morningcake.codegen.service.Parser;
import com.morningcake.codegen.template.MyClassTemplate;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
public class MyClassCodeGeneratorImpl implements CodeGenerator {

    @Value(value = "${description.json.path}")
    private String jsonPath;

    @Value(value = "${output.folder}")
    private String outputCodegenFolder;

    @Value(value = "${templates.folder}")
    private String templatesFolder;

    @Value(value = "${templates.myclass}")
    private String myclassTemplate;

    private final Parser jsonParser;

    @Override
    @EventListener(ContextRefreshedEvent.class)
    public void generateAll() throws IOException, TemplateException {

        // прочитать и спарсить json файл
        Path path = Paths.get(jsonPath).toAbsolutePath().normalize();
        List<MyClassTemplate> objects = jsonParser.parse(path);

        // осуществить кодогенерацию
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
        cfg.setDirectoryForTemplateLoading(Paths.get(templatesFolder).toFile());
        Template template = cfg.getTemplate(myclassTemplate, "utf-8");

        StringBuilder messageBuilder = new StringBuilder();

        for (MyClassTemplate object : objects) {
            Path outputPath = Paths.get(outputCodegenFolder + object.getClassName() + ".java").toAbsolutePath().normalize();
            template.process(object, Files.newBufferedWriter(outputPath));
            messageBuilder.append(object.getClassName()).append(", ");
        }

        String message = messageBuilder.toString();
        log.info("Были сгенерированы классы: " + message.substring(0, message.length()-2));
    }
}

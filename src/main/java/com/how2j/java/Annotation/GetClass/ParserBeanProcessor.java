package com.how2j.java.Annotation.GetClass;

import com.how2j.java.Annotation.JDBCConfig;

import javax.annotation.processing.*;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import java.util.ArrayList;
import java.util.Set;

/**
 * 注解处理器。用于在编译期处理注解生成一个类扫描器。
 *
 * @author UnscientificJsZhai
 */
public class ParserBeanProcessor extends AbstractProcessor {
    @Override
    public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
        return false;
    }


    private final ArrayList<Element> elements = new ArrayList<>();
    private final JDBCConfig annotationClass;

    public ParserBeanProcessor() throws ClassNotFoundException {
        Class<?> annotationClass = Class.forName("com.how2j.java.Annotation.JDBCConfig");
        this.annotationClass = annotationClass.getAnnotation(JDBCConfig.class);
    }

    public static void main(String[] args) throws ClassNotFoundException {
        ParserBeanProcessor parserBeanProcessor = new ParserBeanProcessor();
    }
}
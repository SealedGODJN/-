package com.NPU.json.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Person {
    @ExcelProperty("字符串标题")
    @JSONField
    String _id;

    @ExcelProperty("字符串标题")
    @JSONField
    String _index;

    @ExcelProperty("字符串标题")
    @JSONField
    String _score;

    @ExcelProperty("字符串标题")
    @JSONField
    PersonInfo _source;

    @ExcelProperty("字符串标题")
    @JSONField
    String _type;

    @ExcelProperty("字符串标题")
    @JSONField
    List<String> sort;
}

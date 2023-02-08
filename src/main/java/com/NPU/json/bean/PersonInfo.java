package com.NPU.json.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class PersonInfo {
    @ExcelProperty("年龄")
    @JSONField
    String AGE;

    @ExcelProperty("出生年份")
    @JSONField
    String BIRTHDAY;

    @ExcelProperty("出生地")
    @JSONField
    String BPLACE;

    @ExcelProperty("学历")
    @JSONField
    String EDEGREE;

    @ExcelProperty("是否服兵役")
    @JSONField
    String ESCU;

    @ExcelProperty("身高")
    @JSONField
    String HEIGHT;

    @ExcelProperty("家庭住址")
    @JSONField
    String HHPLACE;

    @ExcelProperty("身份证号码")
    @JSONField
    String IDNO;

    @ExcelProperty("身份证类型")
    @JSONField
    String IDTYPE;

    @ExcelProperty("人口类型")
    @JSONField
    String LABELNAMES;

    @ExcelProperty("LABS")
    @JSONField
    String LABS;

    @ExcelProperty("婚姻")
    @JSONField
    String MARR;

    @ExcelProperty("民族")
    @JSONField
    String NATION;

    @ExcelProperty("籍贯")
    @JSONField
    String NPLACE;

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    // 思考一下结构
    @JSONField
    List<Credentials> PHOTO = new ArrayList<>();

    @ExcelProperty("职业")
    @JSONField
    String PROF;

    @ExcelProperty("搜索字符串")
    @JSONField
    String QUERY_STRING;

    @ExcelProperty("宗教信仰")
    @JSONField
    String RELI;

    @ExcelProperty("姓名")
    @JSONField
    String RNAME;

    @ExcelProperty("性别")
    @JSONField
    String SEX;

    @ExcelProperty("曾居址")
    @JSONField
    String STD_ADDRESS;
}

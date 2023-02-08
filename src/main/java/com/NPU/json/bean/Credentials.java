package com.NPU.json.bean;

import com.alibaba.excel.annotation.ExcelIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class Credentials {
    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    List<String> 居住证 = new ArrayList<>();

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    List<String> 边检出境照片 = new ArrayList<>();

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    List<String> 身份证 = new ArrayList<>();

    /**
     * 忽略这个字段
     */
    @ExcelIgnore
    List<String> 驾驶证 = new ArrayList<>();

    List<String> 护照 = new ArrayList<>();
}

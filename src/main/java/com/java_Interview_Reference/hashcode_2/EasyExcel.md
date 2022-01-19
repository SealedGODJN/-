# EasyExcel



## doWrite()函数的调用栈分析

### 1、doWrite()

【类】com.alibaba.excel.write.builder.ExcelWriterSheetBuilder

```java
public void doWrite(List data) {
        if (excelWriter == null) {
            throw new ExcelGenerateException("Must use 'EasyExcelFactory.write().sheet()' to call this method");
        }
        excelWriter.write(data, build());
        excelWriter.finish();
    }
```

excelWriter是什么？





### 2、write()

【类】com.alibaba.excel.ExcelWriter

```java

    /**
     * Write data to a sheet
     *
     * @param data
     *            Data to be written
     * @param writeSheet
     *            Write to this sheet
     * @return this current writer
     */
    public ExcelWriter write(List data, WriteSheet writeSheet) {
        return write(data, writeSheet, null);
    }
```



```java

    /**
     * Write value to a sheet
     *
     * @param data
     *            Data to be written
     * @param writeSheet
     *            Write to this sheet
     * @param writeTable
     *            Write to this table
     * @return this
     */
    public ExcelWriter write(List data, WriteSheet writeSheet, WriteTable writeTable) {
        excelBuilder.addContent(data, writeSheet, writeTable);
        return this;
    }
```





### 3、addContent()

【接口】com.alibaba.excel.write.ExcelBuilder

```java

    /**
     * WorkBook increase value
     *
     * @param data
     *            java basic type or java model extend BaseModel
     * @param writeSheet
     *            Write the sheet
     * @param writeTable
     *            Write the table
     */
    void addContent(List data, WriteSheet writeSheet, WriteTable writeTable);
```



【实现类】com.alibaba.excel.write.ExcelBuilderImpl

```java
    @Override
    public void addContent(List data, WriteSheet writeSheet, WriteTable writeTable) {
        try {
            context.currentSheet(writeSheet, WriteTypeEnum.ADD);
            context.currentTable(writeTable);
            if (excelWriteAddExecutor == null) {
                excelWriteAddExecutor = new ExcelWriteAddExecutor(context);
            }
            excelWriteAddExecutor.add(data);
        } catch (RuntimeException e) {
            finishOnException();
            throw e;
        } catch (Throwable e) {
            finishOnException();
            throw new ExcelGenerateException(e);
        }
    }
```





### 4、add()

【类】com.alibaba.excel.write.executor.ExcelWriteAddExecutor

```java
public void add(List data) {
        if (CollectionUtils.isEmpty(data)) {
            data = new ArrayList();
        }
        WriteSheetHolder writeSheetHolder = writeContext.writeSheetHolder();
        int newRowIndex = writeSheetHolder.getNewRowIndexAndStartDoWrite();
        if (writeSheetHolder.isNew() && !writeSheetHolder.getExcelWriteHeadProperty().hasHead()) {
            newRowIndex += writeContext.currentWriteHolder().relativeHeadRowIndex();
        }
        // BeanMap is out of order,so use fieldList
        List<Field> fieldList = new ArrayList<Field>();
        int relativeRowIndex=0;
        for(Object oneRowData : data){
            int n = relativeRowIndex + newRowIndex;
            addOneRowOfDataToExcel(oneRowData, n, relativeRowIndex, fieldList);
            relativeRowIndex++;
        }
    }
```







### 5、addOneRowOfDataToExcel

```java
private void addOneRowOfDataToExcel(Object oneRowData, int n, int relativeRowIndex, List<Field> fieldList) {
        if (oneRowData == null) {
            return;
        }
        WriteHandlerUtils.beforeRowCreate(writeContext, n, relativeRowIndex, Boolean.FALSE);
        Row row = WorkBookUtil.createRow(writeContext.writeSheetHolder().getSheet(), n);
        WriteHandlerUtils.afterRowCreate(writeContext, row, relativeRowIndex, Boolean.FALSE);
        if (oneRowData instanceof List) {
            addBasicTypeToExcel((List)oneRowData, row, relativeRowIndex);
        } else {
            addJavaObjectToExcel(oneRowData, row, relativeRowIndex, fieldList);
        }
        WriteHandlerUtils.afterRowDispose(writeContext, row, relativeRowIndex, Boolean.FALSE);
    }
```





### 6-1 addBasicTypeToExcel



```java
    private void addBasicTypeToExcel(List<Object> oneRowData, Row row, int relativeRowIndex) {
        if (CollectionUtils.isEmpty(oneRowData)) {
            return;
        }
        Map<Integer, Head> headMap = writeContext.currentWriteHolder().excelWriteHeadProperty().getHeadMap();
        int dataIndex = 0;
        int cellIndex = 0;
        for (Map.Entry<Integer, Head> entry : headMap.entrySet()) {
            if (dataIndex >= oneRowData.size()) {
                return;
            }
            cellIndex = entry.getKey();
            Head head = entry.getValue();
            doAddBasicTypeToExcel(oneRowData, head, row, relativeRowIndex, dataIndex++, cellIndex);
        }
        // Finish
        if (dataIndex >= oneRowData.size()) {
            return;
        }
        if (cellIndex != 0) {
            cellIndex++;
        }
        int size = oneRowData.size() - dataIndex;
        for (int i = 0; i < size; i++) {
            doAddBasicTypeToExcel(oneRowData, null, row, relativeRowIndex, dataIndex++, cellIndex++);
        }
    }
```



### 6-2 addJavaObjectToExcel

```java
private void addJavaObjectToExcel(Object oneRowData, Row row, int relativeRowIndex, List<Field> fieldList) {
        WriteHolder currentWriteHolder = writeContext.currentWriteHolder();
  			// 利用BeanMap进行对象与Map的相互转换
        BeanMap beanMap = BeanMap.create(oneRowData);
        Set<String> beanMapHandledSet = new HashSet<String>();
        int cellIndex = 0;
        // If it's a class it needs to be cast by type
        if (HeadKindEnum.CLASS.equals(writeContext.currentWriteHolder().excelWriteHeadProperty().getHeadKind())) {
            Map<Integer, Head> headMap = writeContext.currentWriteHolder().excelWriteHeadProperty().getHeadMap();
            Map<Integer, ExcelContentProperty> contentPropertyMap =
                writeContext.currentWriteHolder().excelWriteHeadProperty().getContentPropertyMap();
            for (Map.Entry<Integer, ExcelContentProperty> entry : contentPropertyMap.entrySet()) {
                cellIndex = entry.getKey();
                ExcelContentProperty excelContentProperty = entry.getValue();
                String name = excelContentProperty.getField().getName();
                if (writeContext.currentWriteHolder().ignore(name, cellIndex)) {
                    continue;
                }
                if (!beanMap.containsKey(name)) {
                    continue;
                }
                Head head = headMap.get(cellIndex);
                WriteHandlerUtils.beforeCellCreate(writeContext, row, head, cellIndex, relativeRowIndex, Boolean.FALSE);
                Cell cell = WorkBookUtil.createCell(row, cellIndex);
                WriteHandlerUtils.afterCellCreate(writeContext, cell, head, relativeRowIndex, Boolean.FALSE);
                Object value = beanMap.get(name);
                CellData cellData = converterAndSet(currentWriteHolder, excelContentProperty.getField().getType(), cell,
                    value, excelContentProperty, head, relativeRowIndex);
                WriteHandlerUtils.afterCellDispose(writeContext, cellData, cell, head, relativeRowIndex, Boolean.FALSE);
                beanMapHandledSet.add(name);
            }
        }
        // Finish
        if (beanMapHandledSet.size() == beanMap.size()) {
            return;
        }
        if (cellIndex != 0) {
            cellIndex++;
        }
        Map<String, Field> ignoreMap = writeContext.currentWriteHolder().excelWriteHeadProperty().getIgnoreMap();
        initFieldList(oneRowData.getClass(), fieldList);
        for (Field field : fieldList) {
            String filedName = field.getName();
            boolean uselessData = !beanMap.containsKey(filedName) || beanMapHandledSet.contains(filedName)
                || ignoreMap.containsKey(filedName) || writeContext.currentWriteHolder().ignore(filedName, cellIndex);
            if (uselessData) {
                cellIndex++;
                continue;
            }
            Object value = beanMap.get(filedName);
            WriteHandlerUtils.beforeCellCreate(writeContext, row, null, cellIndex, relativeRowIndex, Boolean.FALSE);
            Cell cell = WorkBookUtil.createCell(row, cellIndex++);
            WriteHandlerUtils.afterCellCreate(writeContext, cell, null, relativeRowIndex, Boolean.FALSE);
            CellData cellData = converterAndSet(currentWriteHolder, value == null ? null : value.getClass(), cell,
                value, null, null, relativeRowIndex);
            WriteHandlerUtils.afterCellDispose(writeContext, cellData, cell, null, relativeRowIndex, Boolean.FALSE);
        }
    }
```



#### BeanMap

> [javabean](https://so.csdn.net/so/search?q=javabean)与map的转换有很多种方式，比如:
>
> 1、通过ObjectMapper先将bean转换为json，再将json转换为map，但是这种方法比较绕，且效率很低，经[测试](http://lib.csdn.net/base/softwaretest)，循环转换10000个bean，就需要12秒！！！不推荐使用
>
> 2、通过[Java](http://lib.csdn.net/base/java)反射，获取bean类的属性和值，再转换到map对应的键值对中，这种方法次之，但稍微有点麻烦
>
> 3、通过net.sf.[cglib](https://so.csdn.net/so/search?q=cglib).beans.BeanMap类中的方法，这种方式效率极高，它跟第二种方式的区别就是因为使用了缓存，初次创建bean时需要初始化，之后就使用缓存，所以速度极快，经测试，循环bean和map的转换10000次，仅需要300毫秒左右。
>
>  
>
> 所以，推荐第3种方式。  以下是相关代码：

```java
package com.java_Interview_Reference.hashcode_2;

import net.sf.cglib.beans.BeanMap;

import java.math.BigInteger;

/**
 * 避免每次进行BeanMap map = BeanMap.create();创建对象，不同于BeanCopier对象，
 * BeanMap主要针对对象实例进行处理，所以一般建议是map.setBean(pojo);进行动态替换持有的对象实例。
 * 应用场景：针对put,putAll操作会直接修改pojo对象里的属性，所以可以通过beanMap.putAll(map)进行map<->pojo属性的拷贝。
 */
public class BeanMapTest {

    public static void main(String[] args) {
        // 初始化
        BeanMap map = BeanMap.create(new Pojo());
        // 构造
        Pojo pojo = new Pojo();
        pojo.setIntValue(1);
        pojo.setBigInteger(new BigInteger("2"));
        // 赋值
        map.setBean(pojo);
        // 验证
        System.out.println(map.get("intValue"));
        System.out.println(map.keySet());
        System.out.println(map.values());
    }
}

class Pojo {

    private int        intValue;
    private BigInteger bigInteger;

    public void setIntValue(int i) {
        this.intValue = i;
    }

    public void setBigInteger(BigInteger bigInteger) {
        this.bigInteger = bigInteger;
    }
}
```



```java
   /** 
 * 将对象装换为map 
 * @param bean 
 * @return 
 */  
public static <T> Map<String, Object> beanToMap(T bean) {  
    Map<String, Object> map = Maps.newHashMap();  
    if (bean != null) {  
        BeanMap beanMap = BeanMap.create(bean);  
        for (Object key : beanMap.keySet()) {  
            map.put(key+"", beanMap.get(key));  
        }             
    }  
    return map;  
}  
  
/** 
 * 将map装换为javabean对象 
 * @param map 
 * @param bean 
 * @return 
 */  
public static <T> T mapToBean(Map<String, Object> map,T bean) {  
    BeanMap beanMap = BeanMap.create(bean);  
    beanMap.putAll(map);  
    return bean;  
}  
  
/** 
 * 将List<T>转换为List<Map<String, Object>> 
 * @param objList 
 * @return 
 * @throws JsonGenerationException 
 * @throws JsonMappingException 
 * @throws IOException 
 */  
public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {  
    List<Map<String, Object>> list = Lists.newArrayList();  
    if (objList != null && objList.size() > 0) {  
        Map<String, Object> map = null;  
        T bean = null;  
        for (int i = 0,size = objList.size(); i < size; i++) {  
            bean = objList.get(i);  
            map = beanToMap(bean);  
            list.add(map);  
        }  
    }  
    return list;  
}  
  
/** 
 * 将List<Map<String,Object>>转换为List<T> 
 * @param maps 
 * @param clazz 
 * @return 
 * @throws InstantiationException 
 * @throws IllegalAccessException 
 */  
public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps,Class<T> clazz) throws InstantiationException, IllegalAccessException {  
    List<T> list = Lists.newArrayList();  
    if (maps != null && maps.size() > 0) {  
        Map<String, Object> map = null;  
        T bean = null;  
        for (int i = 0,size = maps.size(); i < size; i++) {  
            map = maps.get(i);  
            bean = clazz.newInstance();  
            mapToBean(map, bean);  
            list.add(bean);  
        }  
    }  
    return list;  
}  
```





### 7、doAddBasicTypeToExcel

```java
/**
* 1）函数功能：<br>
* 根据dataIndex取出oneRowData中第dataIndex个数据，添加到（row,cellIndex）的格子中去<br>
* 
* @param oneRowData 一行数据
*/
private void doAddBasicTypeToExcel(List<Object> oneRowData, Head head, Row row, int relativeRowIndex, int dataIndex,
        int cellIndex) {
        if (writeContext.currentWriteHolder().ignore(null, cellIndex)) {
            return;
        }
        WriteHandlerUtils.beforeCellCreate(writeContext, row, head, cellIndex, relativeRowIndex, Boolean.FALSE);
        Cell cell = WorkBookUtil.createCell(row, cellIndex);
        WriteHandlerUtils.afterCellCreate(writeContext, cell, head, relativeRowIndex, Boolean.FALSE);
        Object value = oneRowData.get(dataIndex);
        CellData cellData = converterAndSet(writeContext.currentWriteHolder(), value == null ? null : value.getClass(),
            cell, value, null, head, relativeRowIndex);
        WriteHandlerUtils.afterCellDispose(writeContext, cellData, cell, head, relativeRowIndex, Boolean.FALSE);
    }
```
















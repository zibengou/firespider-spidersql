# SpiderSQL-网络即存储（IAAS）

## 简介

## 快速开始

### 1. 安装

### 2. 网络嗅探

### 3. 接口嗅探(Continue...)

### 4. 数据抓取

### 5. 数据打印

### 6.数据存储(Continue...)



## 使用说明

### 1.语法

熟悉Antlr的同学可以参考SpiderSQL.g4

#### 1.1 属性

属性作为动作与变量的描述

所有属性均为类json格式

```
a:{ host : "192.168.0.0" , port : 8080 ,list:[{a:true,b:null}]}
b:scan{ "host" : "192.168.0.0" , "port" : 8080 }
c:scan{ "host" : a.ip , "port" : a.port }
```



#### 1.2 变量

变量均通过冒号赋值

变量的内容即为属性

变量可以获取子属性并使用

```python
对变量a直接赋值：a:{name:"zibengou",content:"internet as a storage"}
将百度页面的返回结果存入变量b：b:get{host:"http://www.baidu.com"}
将变量a的name属性赋值给b的aName属性：a:{name:"zibengou"} | b:{aName:a.name}
```

#### 1.3 动作

1. get

   数据获取动作

   | 字段     | 描述           | 类型               | 是否必须     |
   | ------ | ------------ | ---------------- | -------- |
   | url    | 链接地址         | 符合URL规范          | 是        |
   | method | 请求类型         | GET/POST         | 否，默认GET  |
   | type   | POST模式下的数据类型 | form/json/binary | 否，默认json |
   | data   | POST模式下的请求数据 | 属性               | 否，默认为{}  |

   ```
   get{url:"https://www.baidu.com"}
   get{url:"localhost:8080/api/v1/sum",method:"POST",type:"json",data:{value:[1,2,3]}}
   ```

   ​

2. scan

   嗅探

3. save

   存储

4. desc

   接口结构化解析

5. print

   数据打印动作

   ```java
   print{name:"zibengou",list:[1,2,3,4]}
   --------------------------------------------
   output: {"name":"zibengou","list":[1,2,3,4]}

   a:{value:{in:"in"}}
   print a 
   print a.value
   --------------------------------------------
   output: {"value":{"in":"in"}}
   output: {"in":"in"}
   ```

   ​

#### 1.5 队列

#### 1.4 并行与串行

1. 串行标志 **|**

   SpiderSQL会将|左边的命令全部执行完毕后再执行右边的命令，并保留左边的变量。

   ```java
   a:{name:"aaa"} | b:{name:a.name,value:12} | print a,b
   -------------------------------------------
   output: {"name":"aaa"} {"name":"aaa","value":12}
   ```

2. 并行标志 **;**

   在串行标志间使用；分割的语句均为并行执行

   ```java
   a:{name:"aaa"} | b:{name:a.name,value:12} | print a; print b
   -------------------------------------------
   // print a 与 print b 并行执行，无法保证线程先后顺序
   output: {"name":"aaa","value":12} {"name":"aaa"} 
   ```

### 2.参数

### 3.配置

### 4.注意事项

## Feature


# SpiderSQL

## 简介

## 快速开始

### 1. 安装

1. 安装JDK1.8

2. [SpiderSQL下载地址](https://github.com/zibengou/firespider-spidersql/releases/tag/0.0.1)  

3. 执行测试

   ```shell
   java -jar spidersql.jar
   #若出现如下内容则安装成功
   ******************* Welcome to SpiderSQL *******************
   *******************                      *******************
   SpiderSQL>
   ```

### 2. 网络嗅探

- [x] 搜索局域网内可用TCP
- [ ] 搜索局域网内可用API

* 属性说明

  | 属性名称 | 是否必须 | 示例                                       | 描述   |
  | ---- | ---- | ---------------------------------------- | ---- |
  | host | 是    | "127.0.0.1","www.baidu.com","192.168.0-122.2-255" | 主机地址 |
  | port | 是    | 8080,"8080","8080-8090"                  | 地址端口 |


* 示例

  ```shell
  # 检测本地8070端口是否可用
  SpiderSQL> a:scan{host:"127.0.0.1",port:"8070"} | print a
  [
  {"ip":"127.0.0.1","host":"127.0.0.1","port":"8070","result":true}
  ]
  Total Time : 0.0 sec
  # 检测本地 8060-8080范围内可用端口
  SpiderSQL> a:scan{host:"127.0.0.1",port:"8060-8080"} | print a
  [
  {"ip":"127.0.0.1","host":"127.0.0.1","port":"8070","result":true}
  ]
  Total Time : 1.014 sec
  # 检测172.22.22.0-255 80,8070,8080范围内可用端口
  SpiderSQL> a:scan{host:"172.22.22.0-255",port:"80,8080,8070"} | print a
  [
  {"ip":"172.22.22.59","host":"172.22.22.59","port":"80","result":true},
  {"ip":"172.22.22.190","host":"172.22.22.190","port":"8070","result":true}
  ]
  Total Time : 8.016 sec
  # 检测百度，知乎80，8080范围内端口
  SpiderSQL> a:scan{host:"www.baidu.com,www.zhihu.com",port:"80,8080"} | print a
  [
  {"ip":"115.239.210.27","host":"www.baidu.com","port":"80","result":true},
  {"ip":"118.178.213.186","host":"www.zhihu.com","port":"80","result":true}
  ]
  Total Time : 1.005 sec
  # 多网段 8000-8100 端口检索
  SpiderSQL> a:scan{host:"172.22.22-255.0-255",port:"8000-8100"} | print a
  ```

  ​

### 3. 接口嗅探(Continue...)

### 4. 数据抓取

- [x] GET模式
- [ ] POST模式
- [ ] 多媒体数据

* 属性说明

  | 属性名称    | 是否必须      | 示例                                       | 描述   |
  | ------- | --------- | :--------------------------------------- | :--- |
  | url     | 是         | "http://localhost:(8080,80)/page/(100-200).html" | 地址   |
  | filter  | 否         | {title:"//title[1]/text()"}(注：支持[xpath](http://www.w3school.com.cn/xpath/),[json_path](https://github.com/json-path/JsonPath)语法) | 过滤项  |
  | charset | 否         | "UTF-8","GBK"                            | 编码格式 |
  | timeout | 否(5000ms) | 10000                                    | 超时时间 |


* 示例

  ```shell
  # 抓取知乎某问题标题
  SpiderSQL> a:get{url:"https://www.zhihu.com/question/36701164",filter:{title:"//
  title[@data-react-helmet='true']/text()"}} | print a.title
  [
  "璺虫Ы鐨勬椂鍊欙紝浣犳槸鎬庝箞鍜屽墠鑰佹澘璇寸殑锛? - 鐭ヤ箮"
  ]
  Total Time : 0.645 sec
  # 改用UTF-8编码格式请求
  SpiderSQL> a:get{url:"https://www.zhihu.com/question/36701164",filter:{title:"//
  title[@data-react-helmet='true']/text()"},charset:"UTF-8"} | print a.title
  [
  "跳槽的时候，你是怎么和前老板说的？ - 知乎"
  ]
  Total Time : 0.459 sec
  # 批量请求
  SpiderSQL> a:get{url:"https://www.zhihu.com/question/3670116(0-9)",filter:{title
  :"//title[@data-react-helmet='true']/text()"},charset:"UTF-8"}| print a.title
  [
  null,
  "跳槽的时候，你是怎么和前老板说的？ - 知乎",
  "脑袋里感觉装着一坨屎是什么体验？ - 知乎",
  "信用卡催款公司有人了解吗？ - 知乎",
  null,
  null,
  null,
  "CS这个专业是否正在走下坡路? - 知乎",
  "遇到盲目自大的人的室友怎么处理？ - 知乎",
  null
  ]
  Total Time : 3.136 sec
  # 队列模式(详情见队列)
  SpiderSQL> a:get{timeout:10000,url:"https://www.zhihu.com/question/3670116(7-9)",filter:{title:"//title[@data-react-helmet='true']/text()"},charset:"UTF-8"}->b:{url:a.url,title:a.title}|print b
  [
  {"url":"https://www.zhihu.com/question/36701169","title":"遇到盲目自大的人的室友怎么处理？ - 知乎"},
  {"url":"https://www.zhihu.com/question/36701167","title":"信用卡催款公司有人了解吗？ - 知乎"},
  {"url":"https://www.zhihu.com/question/36701168","title":null}
  ]
  Total Time : 0.69 sec
  ```

### 5. 数据打印

- [x] 打印变量内容
- [x] 打印结果格式化
- [ ] 打印复杂预执行变量

* 示例

  ```shell
  # 直接打印属性
  SpiderSQL> print{a:"aaa"}
  {"a":"aaa"}
  Total Time : 0.001 sec
  # 打印普通变量
  SpiderSQL> a:{name:"cancan"}
  Total Time : 0.002 sec
  SpiderSQL> print a
  {"name":"cancan"}
  Total Time : 0.0 sec
  # 打印预执行变量
  SpiderSQL> print{sum:1+10}
  {"sum":11}
  Total Time : 0.003 sec
  ```

  ​

### 6.数据存储

- [x] 本地文件存储
- [ ] Redis存储

* 属性说明

  | 属性名称 | 是否必须       | 示例                | 描述   |
  | ---- | ---------- | ----------------- | ---- |
  | path | 是          | "aaa.json"        | 存储地址 |
  | data | 是          | "this is content" | 存储内容 |
  | type | 否（默认Local） | "local","redis"   | 存储模式 |

* 示例

  ```shell
  # 存储属性内容
  SpiderSQL> a:save{path:"aaa",data:"aaaaa"}|print a
  true
  Total Time : 0.229 sec
  # 存储对象内容
  SpiderSQL> a:{name:"cancan",sex:1} | save{path:"people",data:a}
  Total Time : 0.012 sec
  # 队列模式
  a:get{timeout:10000,url:"https://www.zhihu.com/question/3670116(7-9)",filter:{title:"//title[@data-react-helmet='true']/text()"},charset:"UTF-8"}->b:save{path:"zhihu_spider",data:{url:a.url,title:a.title}}
  ```

  ​



## 内容详解

### 1.语法

参考EBNF(扩展巴斯科范式)，参考Cypher

熟悉Antlr的同学可以直接看SpiderSQL.g4

#### 1.1 属性

属性为动作与变量的描述，属性的语法解析如下

```sh
# 对象词法
obj
   : '{' map (',' map)* '}'
   | '{' '}'
   ;
# 字典词法
map
   : (C_VAR | STRING) ':' value
   ;
# 数组词法
array
   : '[' value (',' value)* ']'
   | '[' ']'
   ;
# 内容递归
value
   : STRING
   | INT
   | DOUBLE
   | C_VAR
   | value (SYMBOL value)+
   | obj
   | array
   | 'true'
   | 'false'
   | 'null'
   ;
# 基本词法
C_VAR           :   ID+ ('.' ID+)*;
STRING          :   '"' (ESC | ~ ["\\])* '"';
DOUBLE          :   '-'? DIGIT+ ( '.' DIGIT+ )+;
INT             :   '-'? DIGIT+;
fragment ID              :   [a-zA-Z]+ [a-zA-Z0-9]*;
```

#### 1.2 变量

变量均通过冒号赋值，变量的内容即为属性，变量可以递归获取子属性并使用

变量的语法解析如下：

```sh
# 多变量
c_mul_var       :   C_VAR (',' C_VAR)+;
# 单变量，通过.进行子属性递归
C_VAR           :   ID+ ('.' ID+)*;
# 基本字符串首字符为字母，其后只能跟随字母或数字
fragment ID     :   [a-zA-Z]+ [a-zA-Z0-9]*;
```

简单举例说明：

```python
对变量a直接赋值：a:{name:"zibengou",content:"internet as a storage"}
将百度页面的返回结果存入变量b：b:get{host:"http://www.baidu.com"}
将变量a的name属性赋值给b的aName属性：a:{name:"zibengou"} | b:{aName:a.name}
```

#### 1.3 动作

动作的操作对象为变量的属性，动作的操作结果为属性，动作分为scan,get,save,desc,print五大类，

动作的语法解析如下：

```shell
get                 :   GET_STR obj;
save                :   SAVE_STR obj;
desc                :   DESC_STR obj;
scan                :   SCAN_STR obj;
print               :   PRINT_STR (obj | C_VAR |c_mul_var);
```

#### 1.4 赋值

变量必须通过赋值操作才具有属性，赋值是变量与属性之间交互的桥梁，赋值的关键字为冒号(:)

赋值的语法文件解析如下：

```shell
assign_statement    :   C_VAR ASSIGN value          # assignValue
                    |   C_VAR ASSIGN get            # assignGet
                    |   C_VAR ASSIGN save           # assignSave
                    |   C_VAR ASSIGN scan           # assignScan
                    |   C_VAR ASSIGN desc           # assignDesc
# 赋值字符
ASSIGN          	:   ':';
```

#### 1.5 队列模式

在批量爬取的场景中，爬虫需要考虑如何维护种子队列（去重，代理，多线程，过滤）。在SpiderSQL默认的队列模式中封装了这些处理方案，使用者只需专注于处理数据获取逻辑即可。

队列模式的关键字为(->)，队列的语法解析如下：

```sh
push_statement      
# 默认队列模式，前一项操作中获取的数组结果集将被拆分并逐个消费，同一个结果可以同时被多个后续操作消费
:   var '->' (var | mul_var)           # defaultPush
# 计数队列模式，指定消费前一项操作结果集的N个结果
|   var '-[' INT ']>' (var | mul_var)  # countingPush
```

#### 1.6 并行与串行

1. 串行标志(|)

   SpiderSQL会将|左边的命令全部执行完毕后再执行右边的命令，并保留左边的变量。

   ```shell
   a:{name:"aaa"} | b:{name:a.name,value:12} | print a,b
   -------------------------------------------
   output: {"name":"aaa"} {"name":"aaa","value":12}
   ```

2. 并行标志 (;)

   分割的语句均为并行执行，建议不要在并行操作中共享变量属性

   ```shell
   a:{name:"aaa"} | b:{name:a.name,value:12} | print a; print b
   -------------------------------------------
   # print a 与 print b 并行执行，无法保证线程先后顺序
   output: {"name":"aaa","value":12} {"name":"aaa"} 
   ```

### 2.配置

### 3.使用技巧

* SpiderSQL本质上是一种语言，通用的编程技巧同样适用。例如在请求中有大量可复用属性时，建议在前置语句中设置全局静态变量。

  ```
  SpiderSQL> setting:{header:{Cookie:"aaaaaaa"},filter:{title:"//title[1]/text"},path:"zhihu"+a.title,url:"https://https://www.zhihu.com/question/3670116(7-9)"}
  Total Time : 0.001 sec
  SpiderSQL> a:get{url.setting.url,filter:setting.filter}​
  ```



* SpiderSQL支持文件执行，可以将大量执行语句写入文件，并执行

  ```
  java -jar spidersql.jar execute.sql
  ```

  ​

## Feature

* 本地服务化
* 多媒体文件抓取与解析
* 接口扫描
* 接口数据格式化
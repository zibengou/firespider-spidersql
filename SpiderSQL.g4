 grammar SpiderSQL;
/** 初始任务 **/
spidersql           :   multiple_statement      # executeMultiple
                    |   combine_statement       # executeSimple
                    ;

/** 嵌套查询 **/
multiple_statement  :   combine_statement (SERIAL combine_statement)+ ;

combine_statement   :   simple_statement (PARALLEL simple_statement)* ;

/** 基本语句 语法**/
simple_statement    :   assign_statement
                    |   push_statement
                    |   print
                    ;

/** 队列动作 语法**/
push_statement      :   var '->' (var | mul_var)                            # defaultPush
                    |   var '-[' INT ']>' (var | mul_var)                   # countingPush
                    |   mul_var '-[' INT ',' var ']>' (var | mul_var)       # multiplePush
                    ;

/** 高级变量 语法**/
mul_var             :   '(' var (',' var)* ')';
var                 :   C_VAR
                    |   assign_statement
                    ;

/** 赋值动作 语法 **/
assign_statement    :   C_VAR ASSIGN value          # assignValue
                    |   C_VAR ASSIGN get            # assignGet
                    |   C_VAR ASSIGN save           # assignSave
                    ;


/** 基本动作 语法 **/
get                 :   GET_STR obj;
save                :   SAVE_STR obj;
desc                :   DESC_STR obj;
scan                :   SCAN_STR obj;
print               :   PRINT_STR (obj | C_VAR |c_mul_var);

/** 对象 词法 **/
obj
   : '{' map (',' map)* '}'
   | '{' '}'
   ;

map
   : (C_VAR | STRING) ':' value
   ;

array
   : '[' value (',' value)* ']'
   | '[' ']'
   ;

value
   : STRING
   | INT
   | DOUBLE
   | C_VAR
   | obj
   | array
   | 'true'
   | 'false'
   | 'null'
   ;

c_mul_var       :   C_VAR+ (',' C_VAR)+;

/** 基本词法 **/
GET_STR         :   G E T;
SAVE_STR        :   S A V E;
SCAN_STR        :   S C A N;
DESC_STR        :   D E S C;
PRINT_STR       :   P R I N T;
PARALLEL        :   ';';
SERIAL          :   '|';
ASSIGN          :   ':';
C_VAR           :   ID+ ('.' ID+)*;

STRING          :   '"' (ESC | ~ ["\\])* '"';
DOUBLE          :   '-'? DIGIT+ ( '.' DIGIT+ )+;
INT             :   '-'? DIGIT+;
fragment ID              :   [a-zA-Z]+ [a-zA-Z0-9]*;
fragment ESC    :   '\\' (["\\/bfnrt] | UNICODE);
fragment UNICODE:   'u' HEX HEX HEX HEX;
fragment HEX    :   [0-9a-fA-F];
fragment DIGIT  :   [0-9];

fragment A : [aA];
fragment B : [bB];
fragment C : [cC];
fragment D : [dD];
fragment E : [eE];
fragment F : [fF];
fragment G : [gG];
fragment H : [hH];
fragment I : [iI];
fragment J : [jJ];
fragment K : [kK];
fragment L : [lL];
fragment M : [mM];
fragment N : [nN];
fragment O : [oO];
fragment P : [pP];
fragment Q : [qQ];
fragment R : [rR];
fragment S : [sS];
fragment T : [tT];
fragment U : [uU];
fragment V : [vV];
fragment W : [wW];
fragment X : [xX];
fragment Y : [yY];
fragment Z : [zZ];
WS         : [ \t\n\r] + -> skip;
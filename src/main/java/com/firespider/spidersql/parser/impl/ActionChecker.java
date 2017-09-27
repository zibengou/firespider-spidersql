package com.firespider.spidersql.parser.impl;

import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonObject;

/**
 * 所有动作语法的合法性检测均在该类中进行，需要打详细的错误日志
 * todo 增加细节过滤功能
 */
public class ActionChecker {

    public boolean check(GenJsonElement element, String type) {
        boolean res = false;
        switch (type) {
            case "get":
                res = checkGet(element);
                break;
            case "scan":
                break;
            case "desc":
                break;
            case "print":
                break;
            case "save":
                break;
        }
        return res;
    }

    private boolean checkGet(GenJsonElement element) {
        if (element instanceof GenJsonObject) {
            if (((GenJsonObject) element).has("url")) {
                return true;
            }
        }
        return false;
    }
}

package com.firespider.spidersql.action;

import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonObject;

/**
 * 所有动作语法的合法性检测均在该类中进行，需要打详细的错误日志
 * todo 增加细节过滤功能
 */
public class ActionChecker {

    public boolean check(GenJsonElement element, ActionManager.TYPE type) {
        boolean res = false;
        switch (type) {
            case GET:
                res = checkGet(element);
                break;
            case SCAN:
                break;
            case DESC:
                break;
            case PRINT:
                res = true;
                break;
            case SAVE:
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

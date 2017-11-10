package com.firespider.spidersql.action;

import com.firespider.spidersql.lang.GenElement;
import com.firespider.spidersql.lang.GenObject;
import com.firespider.spidersql.lang.GenPrimitive;

/**
 * 所有动作语法的合法性检测均在该类中进行，需要打详细的错误日志
 * todo 增加细节过滤功能
 */
public class ActionChecker {

    public boolean check(GenElement element, ActionManager.TYPE type) {
        boolean res = false;
        switch (type) {
            case GET:
                res = checkGet(element);
                break;
            case SCAN:
                res = checkScan(element);
                break;
            case DESC:
                break;
            case PRINT:
                res = true;
                break;
            case SAVE:
                res = checkSave(element);
                break;
            case VALUE:
                res = true;
        }
        return res;
    }

    private boolean checkGet(GenElement element) {
        boolean res = false;
        if (element instanceof GenObject) {
            if (((GenObject) element).has("url")) {
                res = true;
            }
            if (((GenObject) element).has("filter")) {
                if (((GenObject) element).get("filter") instanceof GenPrimitive) {
                    res = true;
                }
            }
        }
        return res;
    }

    private boolean checkScan(GenElement element) {
        if (element instanceof GenObject) {
            if (((GenObject) element).has("host") && ((GenObject) element).has("port")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkSave(GenElement element) {
        if (element instanceof GenObject) {
            if (((GenObject) element).has("path") && ((GenObject) element).has("data")) {
                if (!((GenObject) element).has("type")) {
                    ((GenObject) element).add("type", new GenPrimitive<>("local"));
                }
                return true;
            }
        }
        return false;
    }
}

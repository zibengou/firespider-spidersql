package com.firespider.spidersql.action;

import com.firespider.spidersql.lang.json.GenJsonElement;
import com.firespider.spidersql.lang.json.GenJsonObject;
import com.firespider.spidersql.lang.json.GenJsonPrimitive;

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

    private boolean checkGet(GenJsonElement element) {
        boolean res = false;
        if (element instanceof GenJsonObject) {
            if (((GenJsonObject) element).has("url")) {
                res = true;
            }
            if (((GenJsonObject) element).has("filter")) {
                if (((GenJsonObject) element).get("filter") instanceof GenJsonPrimitive) {
                    res = true;
                }
            }
        }
        return res;
    }

    private boolean checkScan(GenJsonElement element) {
        if (element instanceof GenJsonObject) {
            if (((GenJsonObject) element).has("host") && ((GenJsonObject) element).has("port")) {
                return true;
            }
        }
        return false;
    }

    private boolean checkSave(GenJsonElement element) {
        if (element instanceof GenJsonObject) {
            if (((GenJsonObject) element).has("path") && ((GenJsonObject) element).has("data")) {
                if (!((GenJsonObject) element).has("type")) {
                    ((GenJsonObject) element).add("type", new GenJsonPrimitive<>("local"));
                }
                return true;
            }
        }
        return false;
    }
}

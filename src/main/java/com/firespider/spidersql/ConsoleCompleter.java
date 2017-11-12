package com.firespider.spidersql;

import com.firespider.spidersql.utils.Utils;
import jline.console.completer.Completer;

import java.util.List;

/**
 * Created by stone on 2017/11/11.
 */
public class ConsoleCompleter implements Completer {
    @Override
    public int complete(String s, int i, List<CharSequence> list) {
        if (Utils.isEmpty(s)) {
            return -1;
        }
        String out = s;
        if (s.endsWith("print")) {
            out += "{}";
        } else if (s.endsWith("get")) {
            out += "{url:}";
        } else if (s.endsWith("save")) {
            out += "{path:,data:}";
        } else if (s.endsWith("scan")) {
            out += "{host:,port:}";
        } else {
            return -1;
        }
        list.add(out);
        return 0;
    }
}

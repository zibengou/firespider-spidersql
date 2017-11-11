package com.firespider.spidersql;

import jline.console.completer.Completer;

import java.util.List;

/**
 * Created by stone on 2017/11/11.
 */
public class ConsoleCompleter implements Completer {
    @Override
    public int complete(String s, int i, List<CharSequence> list) {
        System.out.println(s + ":" + i);
        String out = s;
        switch (s) {
            case "print":
                System.out.println("print outtttt");
                out += "{}";
                break;
            case "get":
                out += "{url:}";
                break;
        }
        list.add(out);
        return 0;
    }
}

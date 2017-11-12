package com.firespider.spidersql.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by xiaotong.shi on 2017/11/8.
 */
public class Utils {
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    public static Path getPath(String fullPath) {
        Path path = Paths.get(fullPath);
        try {
            if (path.getParent() != null && Files.notExists(path.getParent())) {
                Files.createDirectories(path.getParent());
            }
            if (Files.notExists(path)) {
                Files.createFile(path);
            }
        } catch (Exception e) {
            System.err.println(e.getMessage() + " create file error");
        }
        return path;
    }

    public static String join(Collection var0, String var1) {
        StringBuffer var2 = new StringBuffer();

        for (Iterator var3 = var0.iterator(); var3.hasNext(); var2.append((String) var3.next())) {
            if (var2.length() != 0) {
                var2.append(var1);
            }
        }

        return var2.toString();
    }
}

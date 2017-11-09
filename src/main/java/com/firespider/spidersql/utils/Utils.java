package com.firespider.spidersql.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
            if (Files.notExists(path.getParent())) {
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
}

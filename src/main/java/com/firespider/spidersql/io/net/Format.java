package com.firespider.spidersql.io.net;

import java.util.*;

/**
 * Created by xiaotong.shi on 2017/10/18.
 */
public class Format {

    private static Map<int[], List<String>> findPosMap(String path) {
        Map<int[], List<String>> resMap = new LinkedHashMap<>();
        char[] p = path.toCharArray();
        boolean regexing = false;
        StringBuilder sb = new StringBuilder();
        int startPos = 0, endPos = 0;
        for (int i = 0; i < p.length; i++) {
            if (regexing) {
                sb.append(p[i]);
            }
            if (p[i] == '(') {
                startPos = i;
                regexing = true;
            }
            if (p[i] == ')') {
                endPos = i;
                int[] res = {startPos, endPos};
                resMap.put(res, parseRegrex(sb.deleteCharAt(sb.length() - 1).toString()));
                regexing = false;
                sb.delete(0, sb.length());
            }
        }
        return resMap;
    }

    public static List<String> parseSingleUrl(String url) {
        Map posMap = findPosMap(url);
        if (posMap.size() < 1) {
            return Collections.singletonList(url);
        } else {
            return parsePosMap(url, posMap);
        }
    }

    private static List<String> parseRegrex(String regex) {
        List<String> res = new ArrayList<>();
        if (regex.contains(",")) {
            for (String commaRegrex : regex.split(",")) {
                res.addAll(parseRegrex(commaRegrex));
            }
        } else if (regex.contains("-")) {
            String[] nums = regex.split("-");
            int st, et;
            try {
                st = Integer.parseInt(nums[0]);
                et = Integer.parseInt(nums[1]);
            } catch (NumberFormatException e) {
                System.err.println("url regex parttern error : " + regex);
                return res;
            }
            if (et < st) {
                int m = et;
                et = st;
                st = m;
            }
            for (; st <= et; st++) {
                res.add(String.valueOf(st));
            }
        } else {
            res.add(regex);
        }
        return res;
    }

    private static List<String> parsePosMap(String url, Map<int[], List<String>> posMap) {
        List<String> res = new ArrayList<>();
        final int[] positions = new int[posMap.size()];
        int pos = 0;
        int nums = 1;
        for (List l : posMap.values()) {
            nums *= l.size() == 0 ? 1 : l.size();
            positions[pos++] = l.size();
        }
        for (; nums > 0; nums--) {
            int[] exepos = remainderPosition(nums, positions);
            int rPos = 0;
            int subPos = -1;
            String sUrl = "";
            for (Map.Entry<int[], List<String>> entry : posMap.entrySet()) {
                int[] subNums = entry.getKey();
                List<String> values = entry.getValue();
                sUrl += url.substring(subPos + 1, subNums[0]) + (values.size() > 0 ? values.get(exepos[rPos]) : "");
                subPos = subNums[1];
                rPos++;
            }
            res.add(sUrl);
        }
        return res;
    }

    private static int[] remainderPosition(int oNums, int[] sizes) {
        int[] res = new int[sizes.length];
        int nums = oNums;
        for (int i = 0; i < sizes.length; i++) {
            if (sizes[i] < 1) {
                continue;
            }
            int pos = nums % sizes[i];
            res[i] = pos;
            nums = nums / sizes[i];
        }
        return res;

    }
}

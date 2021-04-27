package com.github.interfaces.assembler;


import java.util.HashMap;
import java.util.Map;

/**
 * @author EalenXie on 2021/4/27 8:59
 */
public class Test {

    public static void main(String[] args) {
        String t = "ssss111casskkkkk"; // -> s k
        Map<Character, Integer> countMap = new HashMap<>();
        int skip; // 当前索引位 每次跳过一个则永远增加 默认跳过1 当判断连续时跳过连续值
        for (int current = 0; current < t.length(); current = current + skip) {
            skip = 1;
            char c = t.charAt(current);
            int next = current + skip;
            int count = 1;// 局部计数值 统计每个字符连续出现的次数
            while (next < t.length()) {
                char n = t.charAt(next);
                if (c != n) {
                    if (countMap.containsKey(c)) {
                        int exits = countMap.get(c);
                        if (exits < count) {
                            countMap.put(c, count);
                        }
                    } else {
                        countMap.put(c, count);
                    }
                    skip = count;
                    break;
                }
                next++;
                count++;
                // 当是最后一个值是 则写入Map并退出
                if (next == t.length()) {
                    if (countMap.containsKey(c)) {
                        int exits = countMap.get(c);
                        if (exits < count) {
                            countMap.put(c, count);
                        }
                    } else {
                        countMap.put(c, count);
                    }
                }
            }
        }
        System.out.println(countMap);


    }


}



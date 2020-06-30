package com.jianli.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTest {
    public static void main(String args[]) {
        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("type", i);
            list.add(map);
        }
        System.out.println("list过滤前的数据:" + list);
        System.out.println("list过滤前的数量:" + list.size());
        //过滤获取 type=2的数据
        List<Map<String, Object>> list2 = list.stream().filter((Map a) -> ("2".equals(a.get("type").toString()))).collect(Collectors.toList());
        //只获取数量也可以这样写
        Long list2Count = list.stream().filter((Map a) -> ("2".equals(a.get("type").toString()))).count();
        System.out.println("list过滤后的数据:" + list2);
        System.out.println("list过滤后的数量:" + list2Count);
        System.out.println("list过滤后的数量:" + list2.size());
    }
}

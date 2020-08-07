package com.jianli.util.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class TestTransaction {
    List<Transaction> transactions = null;

    @Before
    public void before(){
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    @Test
    public void test01(){
        //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
        transactions.stream()
                .filter(e ->e.getYear() == 2011)
                .sorted((e1,e2) ->  Integer.compare(e1.getValue(),e2.getValue()))
                .forEach(System.out::println);
    }

    @Test
    public void test02(){
        //2. 交易员都在哪些不同的城市工作过？
        transactions.stream()
                .map(e -> e.getTrader().getCity())
                .distinct()
                .forEach(System.out::println);
    }

    @Test
    public void test03(){
        //3. 查找所有来自剑桥的交易员，并按姓名排序
        transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge")).map(e -> e.getTrader()).sorted((e1,e2) -> {
                   return  e1.getName().compareTo(e2.getName());
                }).distinct().forEach(System.out::println);
    }

    @Test
    public void test04(){
        //4. 返回所有交易员的姓名字符串，按字母顺序排序
        transactions.stream()
                .map(e -> e.getTrader().getName()).sorted()
                .forEach(System.out::println);

        System.out.println("------");

        String str = transactions
                .stream()
                .map(e -> e.getTrader().getName())
                .reduce("",String::concat);

        System.out.println(str);

        System.out.println("------");

        transactions.stream()
                .map(e -> e.getTrader().getName())
                .flatMap(TestTransaction::filterCharacter)
                .sorted((e1,e2) -> e1.compareToIgnoreCase(e2))
                .forEach(System.out::println);
    }

    public static Stream<String> filterCharacter(String str){
        List<String> list = new ArrayList<>();

        for(Character ch : str.toCharArray()){
            list.add(ch.toString());
        }

        return list.stream();
    }

    @Test
    public void test05(){
        //5. 有没有交易员是在米兰工作的？
        boolean br = transactions.stream().anyMatch(e -> e.getTrader().getCity().equals("Milan"));
        System.out.println(br);
    }

    @Test
    public void test06(){
        //6. 打印生活在剑桥的交易员的所有交易额
        Optional<Integer> sum = transactions.stream()
                .filter(e -> e.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .reduce(Integer::sum);

        System.out.println(sum.get());
    }

    @Test
    public void test07(){
        //7. 所有交易中，最高的交易额是多少
        Optional<Integer> max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compare);

        System.out.println(max.get());
    }

    @Test
    public void test08(){
        //8. 找到交易额最小的交易
        Optional<Transaction> op = transactions.stream().min((e1,e2) -> Integer.compare(e1.getValue(),e2.getValue()));

        System.out.println(op.get());
    }
}

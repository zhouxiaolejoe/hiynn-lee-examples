package com.hiynn.spring.mybatis.plus;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HiynnSpringMybatisPlusApplicationTests {

    @Test
    public void contextLoads1() {
        Map<String, Object> person1 = new HashMap<String, Object>() {
            {
                put("name", "joe");
                put("age", "18");
            }
        };
        Map<String, Object> person2 = new HashMap<String, Object>() {
            {
                put("name", "jerry");
                put("age", "17");
            }
        };
        Map<String, Object> person3 = new HashMap<String, Object>() {
            {
                put("name", "tom");
                put("age", "16");
            }
        };
        Map<String, Object> person4= new HashMap<String, Object>() {
            {
                put("name", "dog");
                put("age", "17");
            }
        };
        List<Map<String, Object>> list = Arrays.asList(person1, person2, person3,person4);

        list.stream().sorted((Comparator.comparingInt(o -> -Integer.valueOf(o.get("age").toString())))).collect(Collectors.toList()).forEach(x-> System.out.println(x));


        List<String> collect = Arrays.asList("5", "2", "23", "41", "123", "25", "43", "64").stream().sorted(Comparator.comparingInt(o -> Integer.valueOf(o))).collect(Collectors.toList());
        System.err.println(collect);

        String num1= "23.00002";
        String num2= "1.00008";
        BigDecimal bigDecimal = new BigDecimal(num1);
        BigDecimal bigDecima2 = bigDecimal.add(new BigDecimal(num2));
        System.err.println(bigDecima2);
    }

    @Test
    public void contextLoads() {
       String str ="sadad123";



    }





}
class A{
    @Override
    public String toString() {
        return "A{" +
                "content='" + content + '\'' +
                ", headImg='" + headImg + '\'' +
                '}';
    }

    private String content;
    private String headImg;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }
}
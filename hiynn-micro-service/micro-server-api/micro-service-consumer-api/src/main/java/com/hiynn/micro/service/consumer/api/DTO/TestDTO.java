package com.hiynn.micro.service.consumer.api.DTO;

/**
 * @ClassName TestDTO
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/25 14:07
 * @Version 1.0.0
 */
public class TestDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

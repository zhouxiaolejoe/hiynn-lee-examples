package com.hiynn.micro.service.api.DTO;

/**
 * @ClassName TestDTO
 * @Description TODO
 * @Author ZhouXiaoLe
 * @Date 2019/10/25 14:07
 * @Version 1.0.0
 */
public class TestProducerDTO {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "TestProducerDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

package com.github.interfaces.assembler;


import org.junit.Assert;
import org.junit.Test;

public class AssemblerTest {

    @Test
    public void convert() {
        AssemblerFactory factory = new AssemblerFactory();
        // PersonDTO 转成 Account
        PersonDTO dto = new PersonDTO("ealenxie", 23, "男");
        Account account = factory.convert(
                (source, target) -> {
                    target.setAge(source.getAge());
                    target.setUsername(source.getName());
                    target.setSex(source.getGender());
                }, dto, Account.class
        );
        Assert.assertEquals(account.getUsername(), dto.getName());
        // Account 转成 PersonDTO
        PersonDTO dto1 = factory.convert(
                (source, target) -> {
                    target.setAge(source.getAge());
                    target.setGender(source.getSex());
                    target.setName(source.getUsername());
                }, account, PersonDTO.class);
        Assert.assertEquals(dto.getName(), dto1.getName());


    }

}
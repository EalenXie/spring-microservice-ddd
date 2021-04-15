关于装配(Assembler)
=====

<b>Assembler</b> : 实现`DTO`与`领域对象`之间的相互转换,数据交换,因此`Assembler`几乎总是同`DTO`一起出现。笔者认为装配的意义并不止限于`DTO`与`领域对象`，任何数据对象转换处理我觉得都可以称为装配。


Assembler装配接口设计,实现任意两个对象的装配。
```java

package com.github.interfaces.assembler;

import org.springframework.beans.BeanUtils;

/**
 * Created by EalenXie on 2018/8/30 18:10.
 * 装配(Assembler): 实现DTO与领域对象之间的相互转换,数据交换,因此Assembler几乎总是同DTO一起出现。
 * 可通过实现此接口完成多种自定义装配转换方式
 */
@FunctionalInterface
public interface Assembler<S, T> {

    /**
     * 装配 请实现装配过程
     */
    void assemble(S source, T target);
    

}
```

AssemblerFactory装配工厂设计，指定任何装配过程，实现各种各样的装配。
```java

package com.github.interfaces.assembler;

/**
 * @author EalenXie create on 2021/2/20 11:18
 * 装配工厂
 */
public class AssemblerFactory {
    /**
     * 装配工厂 指定任何装配过程 执行装配
     *
     * @param assembler 装配
     * @param source    S对象
     * @param target    T对象
     */
    public <S, T> void assemble(Assembler<S, T> assembler, S source, T target) {
        assembler.assemble(source, target);
    }

    /**
     * 装配工厂 指定任何装配过程 执行转换 S -> T
     *
     * @param assembler 装配
     * @param source    S对象
     * @param type      T Class
     * @return 转换出 T新对象
     */
    public <S, T> T convert(Assembler<S, T> assembler, S source, Class<T> type) {
        T target = BeanUtils.instantiateClass(type);
        assemble(assembler, source, target);
        return target;
    }
}
```

比如下面例子中，我们要实现一个名为`PersonDTO`与`Account`领域对象的互相转换。


```java
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
                    // 这里只是装配示例,可以使用BeanUtils等工具类实现相同属性的拷贝
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
```

package com.github.interfaces.assembler;

/**
 * Created by EalenXie on 2018/8/30 18:10.
 * 装配(Assembler): 实现DTO与领域对象之间的相互转换,数据交换,因此Assembler几乎总是同DTO一起出现。
 * 可通过实现此接口完成多种自定义装配转换方式
 */
@FunctionalInterface
public interface Assembler<S, T> {

    /**
     * 装配 请实现装配过程
     *
     * @param source 源
     * @param target 目标
     */
    void assemble(S source, T target);

}

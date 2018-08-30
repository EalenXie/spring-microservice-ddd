/**
 * DDD: domain 领域层
 * 领域层主要负责表达业务概念,业务状态信息和业务规则。
 * Domain层是整个系统的核心层,几乎全部的业务逻辑会在该层实现。
 * 领域模型层主要包含以下的内容:
 * 实体(Entities):具有唯一标识的对象
 * 值对象(Value Objects): 无需唯一标识的对象
 * 领域服务(Domain Services): 一些行为无法归类到实体对象或值对象上,本质是一些操作,而非事物
 * 聚合/聚合根(Aggregates,Aggregate Roots):
        聚合是指一组具有内聚关系的相关对象的集合,每个聚合都有一个root和boundary
 * 工厂(Factories): 创建复杂对象,隐藏创建细节
 * 仓储(Repository): 提供查找和持久化对象的方法
 */
package name.ealen.domain;
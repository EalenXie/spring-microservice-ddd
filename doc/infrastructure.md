关于Infrastructure
===

##### 向其他层提供 通用的 技术能力(比如工具类,第三方库类支持,常用基本配置,数据访问底层实现)

##### 为领域层 提供持久化机制,基础组件支持
比如JDBC底层实现,Hibernate的sessionFactory配置,Mybatis配置等等。

##### 为用户界面层 提供组件配置

比如 统一Facade层响应出参结构,为界面层自定义参数拦截,参数验证等等功能提供组件配置。

##### 支持四个层次间的交互模式等等

为所有层提供各种功能的组件，提供全局的自定义异常,自定义业务码等等。


#### 本例子中的Infrastructure

主要封装了对统一出参，提供了基础运行时异常，公共业务码，全局异常处理拦截基本配置，常用工具类一些内容。

<b>统一出参</b> : 参考业内较为常见的做法,包含自定义业务码。

另外,接口返回是否需要自定义业务码返回,本人一直觉得有所争议，当逻辑发生异常时，以`HTTP Status Code`做返回，还是自定义标准的业务码。

本例中RespBody的设计。

```java
package com.github.infrastructure.base.resp;

/**
 * @author EalenXie Created on 2020/2/28 15:07.
 * 为interfaces(用户界面层) 提供统一出参配置
 */
public class RespBody<T> {
    /**
     * 自定义业务码
     */
    private String code;
    /**
     * 自定义业务提示说明
     */
    private String message;
    /**
     * 自定义返回 数据结果集
     */
    private T body;
    
    // ....省略getter,setter,及其构建方法等等 。
}

```
|参数名|类型|说明|
|:-----  |:-----|-----                           |
|code | String   | 接口返回业务码  |
|message | String   |接口返回业务提示说明  |
|data | Object   |业务数据结果集  |

 **返回示例**

``` 
  {
    "code": "200",
    "message": "默认成功",
	"data":{
		"name":"张三",
		"age" : 123
	}
  }
```

<b>自定义业务运行时异常</b> : 通常用此异常标识一个已知且明确的错误操作(非未知的运行时异常)，此类异常通常不爬取堆栈信息。


<b>本例中的公共响应码</b> : 都是一些在Java中常见的异常，很多返回码值基本参考`HTTP Status Code`

|返回码|描述|说明|常见原因|
|:----|:---|:---|:---|
|<font color='red'>200</font> |OK  |默认成功|接口正常返回|
|<font color='red'>330</font> |Fail|默认失败|失败原因不在其他业务码范围内<br>未指定明确的自定义失败业务码<br>级别不属于异常|
|<font color='red'>400</font> |Bad Request|参数错误|1. 入参没有按照接口要求传递<br>2. 未通过JSR303验证<br>3. 参数转换过程中发生了异常|
|<font color='red'>401</font> |Unauthorized|未经授权的访问,由于凭据无效被拒绝|1. 未登录访问被保护的接口<br>2. 登录账号未被授权<br>3. 令牌或者其他凭据无效等等。|
|403 |Forbidden|请求资源的访问被服务器拒绝|访问的资源是不被服务允许的|
|<font color='red'>405</font> |Method Not Allowed|请求的HTTP方法不允许|Http Method不正确|
|415 |Unsupported Media Type|不支持的媒体类型|Content-Type 或 Content-Encoding 不正确|
|<font color='red'>445</font> |Missing Request Header|缺失必要的请求头(Headers)|接口要求的必传header未传递|
|<font color='red'>500</font> |Internal Server Error|服务器内部系统未知异常|异常不在其他响应码及业务码范围内<br>影响系统正常运行结果的异常|
|502 |Bad Gateway|网关错误|网关发生异常|
|503 |Service Unavailable|服务不可用|网关访问的服务不可用|
|504 |Gateway Timeout|网关超时|网关超时|
|530|Service Connect Exception|服务连接异常|服务器内部连接异常|
|<font color='red'>550 </font>|NullPointerException|服务器内部空指针异常|通常由于服务器代码中未做非空校验<br>代码缺陷异常|
|551 |Database Exception|服务器内部数据库发生异常|数据库服务异常|
|<font color='red'>552 </font>|Sql Exception|服务器内部数据库SQL执行异常|通常是SQL语法不正确所导致|

<b>配置自定义业务码</b>


需要自定义业务码的枚举，并且实现 `ResultCode` ，示例如下 : 

``` java

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 订单响应业务码
 */
@AllArgsConstructor
@Getter
public enum OrderErrorCode implements ResultCode {

    ORDER_NOT_EXIST("1000", "订单不存在"),
    ORDER_EXPIRE("1001", "订单已超时");
  
    private String code;

    private String message;
}

```

在其他层中抛出自定义业务异常，只要抛出 `BusinessException`，本例中的`GlobalFacadeAdvice`会自动捕获并处理。

```
	throw new BusinessException(OrderErrorCode.ORDER_EXPIRE);
```
package name.ealen.infrastructure.util;

import java.util.UUID;

/**
 * (枚举单例常用工具类) 通用UUID生成器
 */
public enum UUIDGenerator {
    getUUIDGenerator;

    /**
     * @return 获取不带'-'的UUID
     */
    public static String generate() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String random() {
        return UUID.randomUUID().toString();
    }
}

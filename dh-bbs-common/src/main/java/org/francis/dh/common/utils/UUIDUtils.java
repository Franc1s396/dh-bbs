package org.francis.dh.common.utils;

import java.util.UUID;

/**
 * @author Franc1s
 * @date 2022/6/5
 * @apiNote
 */
public class UUIDUtils {
    /**
     * 获取32位UUID
     */
    public static String get32UUID() {
        return get36UUID().replace("-", "");
    }

    /**
     * 获取完整的36位UUID
     */
    public static String get36UUID() {
        return UUID.randomUUID().toString();
    }
}

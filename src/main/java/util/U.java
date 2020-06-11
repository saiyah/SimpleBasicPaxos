package util;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author cy
 * @date 2020/6/4 4:31 下午
 */
public class U {

    private static AtomicLong atomicLong = new AtomicLong(1);

    public static long getNext() {
        return atomicLong.getAndIncrement();
    }
}

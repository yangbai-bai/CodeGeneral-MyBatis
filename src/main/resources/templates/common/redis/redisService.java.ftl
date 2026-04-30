package ${pkg}.redis;


import jakarta.annotation.Resource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.concurrent.TimeUnit;

@Service
public class RedisService<K, V> {

    @Resource
    private RedisTemplate<K, V> redisTemplate;
    /**
     * 向Redis中设置键值对，并设置过期时间
     * @param key 键
     * @param value 值
     * @param timeout 过期时间
     * @param timeUnit 时间单位
     */
    public void setValue(K key, V value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }


    /**
     * 从 Redis 中获取键对应的值
     * @param key 键
     * @return 值
     */
    public V getValue(K key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除 Redis 中的键值对
     * @param key 键
     * @return 是否删除成功
     */
    public boolean deleteValue(K key) {
        return redisTemplate.delete(key);
    }

    public boolean hasKey(K k) {
        return getValue(k) != null;
    }
}

package com.example.demo.common.redis;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

@Service
public class RedisClient {
	
	@Autowired
	private RedisTemplate redisTemplate;

	/**
	 * 写入缓存
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 在value的基础上更改value的内容
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean increment(final String key, Long value) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.increment(key, value);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 写入缓存设置时效时间
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(final String key, Object value, Long expireTime) {
		boolean result = false;
		try {
			ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
			operations.set(key, value);
			redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
			result = true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 批量删除对应的value
	 * 
	 * @param keys
	 */
	public void remove(final String... keys) {
		for (String key : keys) {
			remove(key);
		}
	}

	/**
	 * 批量删除key
	 * 
	 * @param pattern
	 */
	public void removePattern(final String pattern) {
		Set<Serializable> keys = redisTemplate.keys(pattern);
		if (keys.size() > 0)
			redisTemplate.delete(keys);
	}

	/**
	 * 删除对应的value
	 * 
	 * @param key
	 */
	public void remove(final String key) {
		if (exists(key)) {
			redisTemplate.delete(key);
		}
	}

	/**
	 * 判断缓存中是否有对应的value
	 * 
	 * @param key
	 * @return
	 */
	public boolean exists(final String key) {
		return redisTemplate.hasKey(key);
	}

	/**
	 * 读取缓存
	 * 
	 * @param key
	 * @return
	 */
	public Object get(final String key) {
		Object result = null;
		ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
		result = operations.get(key);
		return result;
	}

	/**
	 * 设置key的过期时间
	 * 
	 * @param key
	 * @param hashKey
	 * @param value
	 */
	
	public void SetKeyExpireTime(String key,Long expireTime ) {
		redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
	}
	
	
	/**
	 * 哈希添加数据
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public void hmSet(String key, Object hashKey, Object value) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		hash.put(key, hashKey, value);
	}
	
	/**
	 * 哈希获取key数据大小
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Long hmSize(String key) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return hash.size(key);
	}

	/**
	 * 哈希获取数据
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object hmGet(String key, Object hashKey) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return hash.get(key, hashKey);
	}
	
	public List hmGet(String key, Collection<Object> hashKeys) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return hash.multiGet(key, hashKeys);
	}
	
	public Set<Object> hmGetHshKeys(String key) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return 	hash.keys(key);
	}
	/**
	 * 哈希删除数据
	 * 
	 * @param key
	 * @param hashKey
	 * @return
	 */
	public Object hmDelete(String key, Object... hashKeys) {
		HashOperations<String, Object, Object> hash = redisTemplate.opsForHash();
		return hash.delete(key, hashKeys);
	}

	/**
	 * 列表添加
	 * 
	 * @param k
	 * @param v
	 */
	public void lPush(String k, Object v) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		list.rightPush(k, v);
	}

	/**
	 * 列表获取
	 * 
	 * @param k
	 * @param l
	 * @param l1
	 * @return
	 */
	public <T> List<T> lRange(String k, long l, long l1) {
		ListOperations<String, Object> list = redisTemplate.opsForList();
		return (List<T>)list.range(k, l, l1);
	}

	/**
	 * 集合添加
	 * 
	 * @param key
	 * @param value
	 */
	public void add(String key, Object value) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		set.add(key, value);
	}

	/**
	 * 集合获取
	 * 
	 * @param key
	 * @return
	 */
	public Set<Object> setMembers(String key) {
		SetOperations<String, Object> set = redisTemplate.opsForSet();
		return set.members(key);
	}

	/**
	 * 有序集合添加
	 * 
	 * @param key
	 * @param value
	 * @param scoure
	 */
	public void zAdd(String key, Object value, double scoure) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.add(key, value, scoure);
	}
	
	/**
	 * 一次性添加set集合
	 * @param key
	 * @param setValue
	 * @param scoure
	 */
	public void zAddAll(String key, Set<Object> setValue, double scoure) {
		for(Object object : setValue) {
			zAdd(key, object, scoure);
		}
	}

	/**
	 * 有序集合获取
	 * 
	 * @param key
	 * @param scoure
	 * @param scoure1
	 * @return
	 */
	public Set<Object> rangeByScore(String key, double scoure, double scoure1) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		return zset.rangeByScore(key, scoure, scoure1);
	}
	
	/**
	 * 根据分数范围删除ZSet集合中的元素（minScoure和maxSource相同，则是根据分数精确删除）
	 * @param key
	 * @param minScoure
	 * @param maxSource
	 * @return
	 */
	public long removeRangeByScore(String key, double minScoure, double maxSource) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		return zset.removeRangeByScore(key, minScoure, maxSource);
	}

	public void zRemove(String key,Object... values) {
		ZSetOperations<String, Object> zset = redisTemplate.opsForZSet();
		zset.remove(key, values);
	}
}
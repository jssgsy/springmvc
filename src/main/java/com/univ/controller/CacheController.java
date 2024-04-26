package com.univ.controller;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author univ
 * date 2024/4/25
 */
@RestController
@RequestMapping("/cache")
public class CacheController {

    @RequestMapping("/cacheable")
    // 仅以入参id作为缓存项的key
    @Cacheable(value = "cacheOne", key = "#id")
    public String cacheable(String id, String city) {
        System.out.println("cacheable被调用了");
        return id + city;
    }

    @RequestMapping("/cacheput")
    // 当缓存cacheOne中的缓存项#id被操作更新时，更新此缓存项的值
    @CachePut(value = "cacheOne", key = "#id")
    public String update(String id, String city) {
        System.out.println("cacheput被调用了");
        return id + city + "_new";
    }

    @RequestMapping("/cacheevict")
    // 当缓存cacheOne中的缓存项#id被操作删除时，删除此缓存项的值
    @CacheEvict(value = "cacheOne", key = "#id")
    public String evict(String id, String city) {
        System.out.println("cacheevict被调用了");
        return id + city + "_evict";
    }
}

package fr.formation.masterpiece.api.services.impl;

import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import fr.formation.masterpiece.api.services.CacheService;

@Service
public class CacheServiceImpl implements CacheService {

    private final CacheManager cacheManager;

    public CacheServiceImpl(CacheManager cacheManager) {
	this.cacheManager = cacheManager;
    }

    @Override
    public void clearCache(String cache) {
	cacheManager.getCache(cache).clear();
    }
}

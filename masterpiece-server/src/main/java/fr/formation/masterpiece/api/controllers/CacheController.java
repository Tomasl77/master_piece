package fr.formation.masterpiece.api.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.masterpiece.api.services.CacheService;

@RestController
@RequestMapping("/caches")
public class CacheController {

    private final CacheService cacheService;

    public CacheController(CacheService cacheService) {
	this.cacheService = cacheService;
    }

    @GetMapping("/{cache}")
    public void clearCache(@PathVariable("cache") String cache) {
	cacheService.clearCache(cache);
    }
}

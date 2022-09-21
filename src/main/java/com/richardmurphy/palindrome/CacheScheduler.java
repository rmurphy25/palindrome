package com.richardmurphy.palindrome;

import com.richardmurphy.palindrome.dao.PalindromeEntity;
import com.richardmurphy.palindrome.dao.PalindromeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class CacheScheduler {
    private static Logger LOG = LoggerFactory.getLogger(CacheScheduler.class);

    final PalindromeRepository palindromeRepository;
    final CacheManager cacheManager;

    public CacheScheduler(final PalindromeRepository palindromeRepository, final CacheManager cacheManager) {
        this.palindromeRepository = palindromeRepository;
        this.cacheManager = cacheManager;
    }

    @PostConstruct
    public void init() {
        update();
    }

    public void update() {
        LOG.info("Populating cache");
        for (PalindromeEntity palindromeEntity : palindromeRepository.findAll()) {
            cacheManager.getCache("palindromes").put(palindromeEntity.getPhrase(), palindromeEntity);
        }
    }
}

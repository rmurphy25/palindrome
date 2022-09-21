package com.richardmurphy.palindrome.service;

import com.richardmurphy.palindrome.dao.PalindromeEntity;
import com.richardmurphy.palindrome.dao.PalindromeRepository;
import com.richardmurphy.palindrome.dao.converter.PalindromeDaoMapper;
import com.richardmurphy.palindrome.dto.RequestPalindromeDto;
import com.richardmurphy.palindrome.dto.ResponsePalindromeDto;
import com.richardmurphy.palindrome.exception.BadRequestException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.UUID;

@Service
@Transactional
public class PalindromeService {
    private static final Logger LOG = LoggerFactory.getLogger(PalindromeService.class);

    private final PalindromeRepository palindromeRepository;

    public PalindromeService(final PalindromeRepository palindromeRepository) {
        this.palindromeRepository = palindromeRepository;
    }

    @Cacheable(value = "palindromes")
    public ResponsePalindromeDto palindromeChecker(final RequestPalindromeDto palindromeRequest) {
        LOG.info("Checking palindrome without cache hit");

        validatePalindromeRequest(palindromeRequest.phrase());

        final UUID palindromeId = UUID.randomUUID();

        final PalindromeEntity palindromeEntity = PalindromeDaoMapper.INSTANCE.toDao(palindromeRequest);
        palindromeEntity.setResourceId(palindromeId);
        palindromeEntity.setIsPalindrome(isPalindrome(palindromeRequest.phrase()));

        palindromeRepository.save(palindromeEntity);

        return PalindromeDaoMapper.INSTANCE.fromDao(palindromeEntity);
    }

    private boolean isPalindrome(String palindrome) {
        final String toCheck = new StringBuilder(palindrome.toLowerCase()).reverse().toString();
        return palindrome.toLowerCase().equals(toCheck);
    }

    private void validatePalindromeRequest(String phrase) {
        if (!phrase.chars().allMatch(Character::isLetter) || phrase.isBlank()) {
            throw new BadRequestException();
        }
    }
}

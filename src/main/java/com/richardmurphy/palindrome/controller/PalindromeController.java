package com.richardmurphy.palindrome.controller;

import com.richardmurphy.palindrome.dto.RequestPalindromeDto;
import com.richardmurphy.palindrome.dto.ResponsePalindromeDto;
import com.richardmurphy.palindrome.service.PalindromeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/palindrome")
public class PalindromeController {

    private static final Logger LOG = LoggerFactory.getLogger(PalindromeController.class);
    private final PalindromeService palindromeService;

    public PalindromeController(final PalindromeService palindromeService) {
        this.palindromeService = palindromeService;
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponsePalindromeDto isPalindrome(@RequestBody RequestPalindromeDto request) {
        LOG.debug("Palindrome check request: {}", request);

        return palindromeService.palindromeChecker(request);
    }

}

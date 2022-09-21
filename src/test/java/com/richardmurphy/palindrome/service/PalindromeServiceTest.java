package com.richardmurphy.palindrome.service;

import com.richardmurphy.palindrome.dao.PalindromeEntity;
import com.richardmurphy.palindrome.dao.PalindromeRepository;
import com.richardmurphy.palindrome.dto.RequestPalindromeDto;
import com.richardmurphy.palindrome.dto.ResponsePalindromeDto;
import com.richardmurphy.palindrome.exception.BadRequestException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PalindromeServiceTest {

    @InjectMocks
    private PalindromeService palindromeService;

    @Mock
    private PalindromeRepository palindromeRepository;

    @Test
    void givenValidPalindrome_whenPalindromeChecker_thenSucceed() {
        //
        // Given
        //
        final var createPalindromeRequestDto = new RequestPalindromeDto("UserName", "kayak");

        //
        // When
        //
        ResponsePalindromeDto responsePalindrome = palindromeService.palindromeChecker(createPalindromeRequestDto);

        //
        // Then
        //
        final var entityCaptor = ArgumentCaptor.forClass(PalindromeEntity.class);
        verify(palindromeRepository).save(entityCaptor.capture());

        assertNotNull(responsePalindrome);

        final PalindromeEntity savedPalindromeEntity = entityCaptor.getValue();
        assertThat(savedPalindromeEntity.getResourceId(), notNullValue());
        assertThat(savedPalindromeEntity.getIsPalindrome(), equalTo(true));
    }

    @Test
    void givenNonPalindrome_whenPalindromeChecker_thenReturnFalse() {
        //
        // Given
        //
        final var createPalindromeRequestDto = new RequestPalindromeDto("UserName", "hello");

        //
        // When
        //
        ResponsePalindromeDto responsePalindrome = palindromeService.palindromeChecker(createPalindromeRequestDto);

        //
        // Then
        //
        final var entityCaptor = ArgumentCaptor.forClass(PalindromeEntity.class);
        verify(palindromeRepository).save(entityCaptor.capture());

        assertNotNull(responsePalindrome);

        final PalindromeEntity savedPalindromeEntity = entityCaptor.getValue();
        assertThat(savedPalindromeEntity.getResourceId(), notNullValue());
        assertThat(savedPalindromeEntity.getIsPalindrome(), equalTo(false));
    }


    @Test
    void verify_whenPalindromeChecker_givenInvalidRequest_throwsIllegalArgumentException() {
        final var palindromeRequestDto = new RequestPalindromeDto("UserName", "ka yak");

        assertThrows(BadRequestException.class, () ->
                palindromeService.palindromeChecker(palindromeRequestDto));
    }

    @Test
    void verify_whenPalindromeChecker_givenRequestContainingNumbers_throwsIllegalArgumentException() {
        final var palindromeRequestDto = new RequestPalindromeDto("UserName", "123321");

        assertThrows(BadRequestException.class, () ->
                palindromeService.palindromeChecker(palindromeRequestDto));
    }

    @Test
    void verify_whenPalindromeChecker_givenBlankPhrase_throwsIllegalArgumentException() {
        final var palindromeRequestDto = new RequestPalindromeDto("UserName", "");

        assertThrows(BadRequestException.class, () ->
                palindromeService.palindromeChecker(palindromeRequestDto));
    }
}

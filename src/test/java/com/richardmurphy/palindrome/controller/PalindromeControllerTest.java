package com.richardmurphy.palindrome.controller;

import com.richardmurphy.palindrome.dto.RequestPalindromeDto;
import com.richardmurphy.palindrome.dto.ResponsePalindromeDto;
import com.richardmurphy.palindrome.service.PalindromeService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PalindromeControllerTest {

    @InjectMocks
    private PalindromeController controller;

    @Mock
    private PalindromeService palindromeService;

    @Test
    void givenPalindrome_whenCheckPalindrome_thenSucceed() {
        //
        // Given
        //
        final var createPalindromeRequestDto = new RequestPalindromeDto("UserName", "kayak");
        final var expectedPalindromeDto = new ResponsePalindromeDto("kayak", true);

        when(palindromeService.palindromeChecker(any(RequestPalindromeDto.class))).thenReturn(expectedPalindromeDto);

        //
        // When
        //
        ResponsePalindromeDto actualPalindrome = controller.isPalindrome(createPalindromeRequestDto);

        //
        // Then
        //
        verify(palindromeService).palindromeChecker(any(RequestPalindromeDto.class));

        assertNotNull(actualPalindrome);
        assertEquals(actualPalindrome, expectedPalindromeDto);
    }

}

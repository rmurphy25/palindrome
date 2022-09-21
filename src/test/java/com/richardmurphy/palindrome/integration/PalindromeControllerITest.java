package com.richardmurphy.palindrome.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.richardmurphy.palindrome.PalindromeApplication;
import com.richardmurphy.palindrome.dto.RequestPalindromeDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest(
        webEnvironment = WebEnvironment.MOCK,
        classes = PalindromeApplication.class)
public class PalindromeControllerITest {

    @Autowired
    protected ObjectMapper objectMapper;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void checkPalindrome_givenValidRequest_thenSucceed() throws Exception {
        final var request = new RequestPalindromeDto("UserName", "madam");

        mockMvc.perform(post("/palindrome")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.isPalindrome", equalTo(true)));
    }

    @Test
    void checkPalindrome_givenInvalidRequest_throwsIllegalArgumentException() throws Exception {
        final var request = new RequestPalindromeDto("UserName", "ma dam");

        mockMvc.perform(
                        post("/palindrome")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }
}

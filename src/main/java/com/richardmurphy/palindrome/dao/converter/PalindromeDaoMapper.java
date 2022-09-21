package com.richardmurphy.palindrome.dao.converter;

import com.richardmurphy.palindrome.dao.PalindromeEntity;
import com.richardmurphy.palindrome.dto.RequestPalindromeDto;
import com.richardmurphy.palindrome.dto.ResponsePalindromeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PalindromeDaoMapper {

    PalindromeDaoMapper INSTANCE = Mappers.getMapper(PalindromeDaoMapper.class);

    @Mapping(target = "resourceId", ignore = true)
    @Mapping(target = "isPalindrome", ignore = true)
    PalindromeEntity toDao(final RequestPalindromeDto palindrome);

    ResponsePalindromeDto fromDao(final PalindromeEntity entity);
}

package com.richardmurphy.palindrome.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PalindromeRepository extends CrudRepository<PalindromeEntity, UUID> {
}

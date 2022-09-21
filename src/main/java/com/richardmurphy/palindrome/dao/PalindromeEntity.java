package com.richardmurphy.palindrome.dao;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "PALINDROME")
@Data
@ToString
public class PalindromeEntity implements Serializable {

    @Id
    @Column(name = "RESOURCE_ID")
    private UUID resourceId;

    @Column(name = "USER_NAME")
    private String userName;

    @Column(name = "PHRASE")
    private String phrase;

    @Column(name = "IS_PALINDROME")
    private Boolean isPalindrome;

}

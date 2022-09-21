# Palindrome Checker

This application accepts a username and text value and returns an indicator whether that value is a palindrome.

This requires jdk 17 and gradle.

Currently, you can do a POST request to [http://localhost:8080/](http://localhost:8080/)
In the following format. At this stage the phrase must contain only letters and no whitespaces.
```JSON
{
	"userName": "user1",
	"phrase": "kayak"
}
```

This will return the follow http response.
```JSON
{
	"phrase": "kayak",
	"isPalindrome": true
}
```


// Warmup

fun isDuplicatePresent(nums: IntArray): Boolean {
    // Sort it (nlogn) and O(n) to search
    // O(n) - Keep track of duplicates with space of O(n)
    val visited = mutableSetOf<Int>()
    for(num in nums){
        if(visited.contains(num))
        	return true
        visited.add(num)
    }
    
    return false
}

/* A pangram is a sentence where every letter of the English alphabet appears at least once.
 * Given a string sentence containing English letters (lower or upper-case), return true if 
 * sentence is a pangram, or false otherwise. 
 */
private fun isAlphabetic(letter: Char): Boolean {
    if(letter >= 'a' && letter <= 'z' || letter >= 'A' && letter <= 'Z')
    	return true
    
    return false
}

fun isPangram(sentence: String): Boolean {
    val alphabets = mutableSetOf<Char>()
    
    for(letter in sentence){
        if(isAlphabetic(letter))
        	alphabets.add(letter.toLowerCase())
            
        if(alphabets.size == 26)
        	return true
    }
    
    println("alphabets: $alphabets ${alphabets.size}")
    return false
}

/* Given a string s, reverse only all the vowels in the string and return it.
 */ 
fun reverseVowels(s: String): String {
    val vowels = setOf('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U')
    
    var left = 0
    var right = s.length - 1
    var input = s.toMutableList()
    
    while(left < right){
        if(vowels.contains(input[left]) && vowels.contains(input[right])){
            // reverse it
            val temp = input[left]
            input[left] = input[right]
            input[right] = temp
            
            ++left
            --right
        } else {
            if(!vowels.contains(input[left]))
            	++left
            if(!vowels.contains(input[right]))
            	--right
        }
    }
    
    return input.joinToString("")
} 

/** A phrase is a palindrome if, after converting all uppercase letters into lowercase letters and 
 * removing all non-alphanumeric characters, it reads the same forward and backward. Alphanumeric 
 * characters include letters and numbers.
 */
fun removeNonAlphaNumeric(input: String): String {
    val result = StringBuilder()
    
    for(char in input){
        if(char.isLetter())
        	result.append(char)
    }
    
    return result.toString()
}
fun isPalindrome(input: String): Boolean {
    val phrase = removeNonAlphaNumeric(input)
    
    var left = 0; var right = phrase.length - 1
    while(left < right){
        if(phrase[left].toLowerCase() != phrase[right].toLowerCase())
        	return false
        
        ++left
    	--right
    }
    
    return true
} 

/** Given two strings s and t, return true if t is an anagram of s, and false otherwise.
 *  An Anagram is a word or phrase formed by rearranging the letters of a different word 
 *  or phrase, typically using all the original letters exactly once.
 */ 
fun isAnagram(s1: String, s2: String): Boolean {
    var letterFreq = mutableMapOf<Char, Int>()
    for(letter in s1){
        if(letterFreq.contains(letter))
        	letterFreq[letter] = letterFreq[letter]?.plus(1) ?: 0
        else
        	letterFreq[letter] = 1
    }
    
    //println(letterFreq)
    
    for(letter in s2){
        if(!letterFreq.contains(letter))
        	return false
        else if(letterFreq.get(letter) == 0)
        	return false
        else
        	letterFreq[letter] = letterFreq[letter]?.minus(1) ?: 0
    }
    
    return true
} 

/* Given an array of strings words and two different strings that already exist in the array word1 
 * and word2, return the shortest distance between these two words in the list.
 * 8:53pm
 */ 
fun shortestDist(words: List<String>, word1: String, word2: String): Int {
    var dist = Int.MAX_VALUE
    
    var start = -1; var end = -1
    for((i, word) in words.withIndex()){
        //println("start=$start, end=$end, dist=$dist")
        if(word == word1 || word == word2) {
            if(start == -1 && end == -1){
            	start = i
            	end = i
            } else {
                // calculate the distance
                val newDist = end - start
                if(newDist < dist)
                	dist = newDist
                
                start = end
            }
            
        } else if(start != -1 && end != -1)
        	++end     
    }
    
    //println("start=$start, end=$end, dist=$dist")
    return dist + 1
} 

fun goodPairs(nums: List<Int>): Int {
    var pairCount = 0
    val numFreq = mutableMapOf<Int, Int>()
    
    for(num in nums){
        if(numFreq.contains(num))
        	numFreq[num] = numFreq[num]?.plus(1) ?: 0
        else
        	numFreq[num] = 1
        
        pairCount += numFreq.get(num)?.minus(1) ?: 0
        //println("pairCount=$pairCount")
    }
    
    println("numFreq = $numFreq")
    
   
    
    return pairCount
}
 
fun main() {
    val input = intArrayOf(1, 2, 3, 1)
    println("isDuplicatePresent: ${isDuplicatePresent(input)}")
    
    val input2 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
    println("isPangram: ${isPangram(input2)}")
    
    val input3 = "DesignGUrus"
    println("reverseVowels: ${reverseVowels(input3)}")
    
    val input4 = "Was it a car or a cat I saw?"
    println("isPalindrome: ${isPalindrome(input4)}")
    
    val input5 = "hello"; val input6 = "world"
    println("isAnagram: ${isAnagram(input5, input6)}")
    
    val words = listOf("a", "b", "c", "d", "e")
    val word1 = "a"; val word2 = "e"
    println("shortestDist = ${shortestDist(words, word1, word2)}")
    
    val nums = listOf(1,2,3,1,1,3)
    println("goodPairs = ${goodPairs(nums)}")
    
}

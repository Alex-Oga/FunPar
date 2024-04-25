use std::collections::HashSet;

#[allow(dead_code)]
pub fn is_palindrome(s: &str) -> bool {
    let ori = s.chars();
    let oriRev = s.chars().rev();
    ori.eq(oriRev)
}

#[allow(dead_code)]
pub fn is_pangram(s: &str) -> bool {
    let mut x = HashSet::new();
    let itr = s.chars();
    for chars in itr {
        if chars.is_alphabetic() {
            x.insert(chars.to_string().to_lowercase());
        }
    }
    if x.len() == 26 {
        return true;
    }
    else {
        return false;
    }
}

#[cfg(test)]
mod tests {
    use crate::pangrindrome::{is_palindrome, is_pangram};

    #[test]
    fn basic_is_palindrome() {
        assert_eq!(true, is_palindrome("r"));
        assert_eq!(true, is_palindrome("abba"));
        assert_eq!(true, is_palindrome("abcba"));
        assert_eq!(false, is_palindrome("abc"));
    }

    #[test]
    fn basic_pangram() {
        let quick_brown_fox = "The quick brown fox jumps over the lazy Dog";
        assert_eq!(true, is_pangram(quick_brown_fox));
        let quick_prairie_dog = "The quick prairie dog jumps over the lazy fox";
        assert_eq!(false, is_pangram(quick_prairie_dog));
    }
}

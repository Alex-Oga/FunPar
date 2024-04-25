use std::{cmp::Ordering, ops::Range};

#[allow(dead_code)]
#[derive(Debug, Eq, PartialEq)] // make this enum type support equality test (i.e., ==)
pub enum Classification {
    Perfect,
    Deficient,
    Excessive,
}

#[allow(dead_code)]
pub fn classify_perfect(n: u64) -> Classification {
    let mut sum = 0;
    for p in 1..n {
        if p != n {
            if n%p == 0 {sum+=p}
        }
    }
    if sum > n {
        return Classification::Excessive;
    } else if sum < n {
        return Classification::Deficient;
    } else {
        return Classification::Perfect;
    }
}

#[allow(dead_code)]
pub fn select_perfect(range: Range<u64>, kind: Classification) -> Vec<u64> {
    let mut x = vec![];
    for p in range {
        if classify_perfect(p) == kind {
            x.push(p);
        }
    }
    x
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn basic_classify() {
        use Classification::*;
        assert_eq!(classify_perfect(1), Deficient);
        assert_eq!(classify_perfect(6), Perfect);
        assert_eq!(classify_perfect(12), Excessive);
        assert_eq!(classify_perfect(28), Perfect);
    }

    #[test]
    fn basic_select() {
        use Classification::*;
        assert_eq!(select_perfect(1..10_000, Perfect), vec![6, 28, 496, 8128]);
        assert_eq!(
            select_perfect(1..50, Excessive),
            vec![12, 18, 20, 24, 30, 36, 40, 42, 48]
        );
        assert_eq!(
            select_perfect(1..11, Deficient),
            vec![1, 2, 3, 4, 5, 7, 8, 9, 10]
        );
    }
}

#[cfg(feature = "portable_simd")]
use std::simd::StdFloat;

use rayon::iter::IntoParallelIterator;
use rayon::iter::ParallelIterator;

#[allow(dead_code)]
pub fn par_is_prime(n: u64) -> bool {
    let x = 2..=((n as f64).sqrt() as u64 )+1;
    let y = x.clone().into_par_iter().any(|a| n % a == 0);
    if n < 2 {
        return false;
    }
    else if n == 2 {
        return true;
    }
    else {
        if y == false {
            return true;
        }
        else {
            return false;
        }
    }
}

#[allow(dead_code)]
pub fn par_count_primes(n: u32) -> usize {
    let x = (1..n).into_par_iter().filter(|a| par_is_prime(*a as u64)).count();
    return x;
}

#[cfg(test)]
mod tests {
    use crate::numprimes::{par_count_primes, par_is_prime};

    #[test]
    fn basic_is_prime() {
        assert_eq!(false, par_is_prime(0)); 
        assert_eq!(false, par_is_prime(1));
        assert_eq!(true, par_is_prime(2));
        assert_eq!(true, par_is_prime(3));
        assert_eq!(false, par_is_prime(4));
        assert_eq!(false, par_is_prime(6));
        assert_eq!(false, par_is_prime(25));
        assert_eq!(true, par_is_prime(41));
    }
    #[test]
    fn basic_count_primes() {
        assert_eq!(25, par_count_primes(100));
        assert_eq!(78498, par_count_primes(1_000_000));
    }
}

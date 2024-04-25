#[allow(dead_code)]
pub fn par_lin_search<T: Eq + Sync>(xs: &[T], k: &T) -> Option<usize> {
    panic!("Not yet implemented!")
    /* 
    use rayon::iter::*;
    let x = xs
    .par_iter()
    .reduce(identity, op)
    */
}

#[cfg(test)]
mod tests {
    use crate::linsearch::par_lin_search;

    #[test]
    fn basic_lin_search() {
        let xs = vec![3, 1, 4, 2, 7, 3, 1, 9];
        assert_eq!(par_lin_search(&xs, &3), Some(0));
        assert_eq!(par_lin_search(&xs, &5), None);
        assert_eq!(par_lin_search(&xs, &1), Some(1));
        assert_eq!(par_lin_search(&xs, &2), Some(3));
    }
}

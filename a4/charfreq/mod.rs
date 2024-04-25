use chashmap::CHashMap;
use rayon::iter::{IntoParallelRefIterator, ParallelIterator};
use std::collections::{HashMap, HashSet};

pub fn par_char_freq(chars: &[u8]) -> HashMap<u8, u32> {
    let mut x: CHashMap<&u8, u32> = CHashMap::new();
    chars
    .par_iter()
    .for_each(|a: &u8| {
        x.upsert(a, || 1, |v| *v += 1);
    });

    //let mut y: HashMap<u8, u32> = HashMap::new();
    let mut y: HashMap<u8, u32> = HashMap::new();
    x.into_iter().for_each(|a: (&u8, u32)| {
        y.insert(*a.0, a.1);
    });

    return y;
}

#[cfg(test)]
mod tests {
    use crate::charfreq::par_char_freq;
    use std::collections::HashMap;

    #[test]
    fn basic_tests() {
        
    }
}

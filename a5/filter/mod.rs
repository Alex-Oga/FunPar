#[allow(dead_code)]
pub fn par_filter<F>(xs: &[i32], p: F) -> Vec<i32>
where 
    F: Fn(i32) -> bool + Send + Sync
{
    todo!("Write me")
}

use std::io;

struct FlightRecord {
    unique_carrier: String,
    actual_elapsed_time: i32,
    arrival_delay: i32,
}

#[allow(dead_code)]
fn parse_line(line: &str) -> Option<FlightRecord> {
    todo!("Write me")
}

#[allow(dead_code)]
pub fn ontime_rank(filename: &str) -> Result<Vec<(String, f64)>, io::Error> {
    todo!("Write me")
}

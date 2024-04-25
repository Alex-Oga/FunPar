#[allow(dead_code)]
pub fn par_encode_base64(bytes: &[u8]) -> String {
    format!("Write me")
}


#[allow(dead_code)]
pub fn par_decode_base64(code: &str) -> Option<Vec<u8>> {
    None 
}

#[cfg(test)]
mod tests {
    use crate::base64::{par_encode_base64, par_decode_base64};

    #[test]
    fn basic_encode() {
        assert_eq!(&"aGVsbG8xNTAxKys9", &par_encode_base64(b"hello1501++="));
        assert_eq!(&"bGlnaHQgd29yaw==", &par_encode_base64(b"light work"));
        assert_eq!(&"IBBnAwJnZw==", &par_encode_base64(b"\x20\x10g\x03\x02gg"));
    }

    #[test]
    fn basic_decode() {
        assert_eq!(Some(b"light work".to_vec()), par_decode_base64("bGlnaHQgd29yaw=="));
        assert_eq!(Some(b"hello1501++=".to_vec()), par_decode_base64("aGVsbG8xNTAxKys9"));
        assert_eq!(Some(b"\x20\x10g\x03\x02gg".to_vec()), par_decode_base64("IBBnAwJnZw=="));
    }
}

#[allow(dead_code)]
pub fn to_roman(n: u16) -> String {
    let mut rome = String::new();
    let mut m = n;
    
    while m > 0 {
        if m / 1000 > 0 {
            rome.push('M');
            m -= 1000;
            continue;
        }
        else if (m+100) / 1000 > 0 {
            rome.push_str("CM");
            m-= 900;
        }
        else if m / 500 > 0 {
            rome.push('D');
            m -= 500;
        }
        else if (m+100) / 500 > 0 {
            rome.push_str("CD");
            m -= 400;
        }
        else if m / 100 > 0 {
            rome.push('C');
            m -= 100;
            continue;
        }
        else if (m+10) / 100 > 0 {
            rome.push_str("XC");
            m -= 90;
        }
        else if m / 50 > 0 {
            rome.push('L');
            m -= 50;
        }
        else if (m+10) / 50 > 0 {
            rome.push_str("XL");
            m -= 40;
        }
        else if m / 10 > 0 {
            rome.push('X');
            m -= 10;
            continue;
        }
        else if (m+1) / 10 > 0 {
            rome.push_str("IX");
            m -= 9;
        }
        else if m / 5 > 0 {
            rome.push('V');
            m -= 5;
        }
        else if (m+1) / 5 > 0 {
            rome.push_str("IV");
            m -= 4;
        }
        else {
            rome.push('I');
            m -= 1;
            continue;
        }
    }
    return rome;
}

#[allow(dead_code)]
pub fn parse_roman(roman_number: &str) -> u16 {
    let all_Num = roman_number.chars().rev();
    let mut total = 0;
    let mut cur = 0;
    let mut sum = 0;
    let mut check = 0;

    for ch in all_Num {
        let num = match ch {
            'I' => 1,
            'V' => 5,
            'X' => 10,
            'L' => 50,
            'C' => 100,
            'D' => 500,
            'M' => 1000,
            _ => 0,
        };
        if cur == 0 {
            cur = num;
            sum = 1;
        }
        else if cur != num {
            if check == 1 {
                total -= sum*cur;
                cur = num;
                sum = 1;
                check = 0;
            }
            else {
                if cur > num {
                    check = 1;
                    total += sum*cur;
                    cur = num;
                    sum = 1;
                }
                else {
                    total += sum*cur;
                    cur = num;
                    sum = 1;
                }
            }
        }
        else {
            sum+=1;
        }
    }
    if sum != 0 && check == 0 {
        total += sum*cur;
    }
    else if sum != 0 && check == 1 {
        total -= sum*cur;
    }

    return total;
}

#[cfg(test)]
mod tests {
    use super::{parse_roman, to_roman};

    #[test]
    fn basic_digits() {
        assert_eq!("I", to_roman(1));
        assert_eq!("V", to_roman(5));
        assert_eq!("X", to_roman(10));
        assert_eq!("L", to_roman(50));
        assert_eq!("C", to_roman(100));
    }

    #[test]
    fn basic_mixture() {
        assert_eq!("II", to_roman(2));
        assert_eq!("IV", to_roman(4));
        assert_eq!("IX", to_roman(9));
        assert_eq!("XII", to_roman(12));
        assert_eq!("XIV", to_roman(14));
        assert_eq!("MCMLIV", to_roman(1954));
    }

    #[test]
    fn basic_parsing() {
        assert_eq!(3, parse_roman("III"));
        assert_eq!(4, parse_roman("IV"));
        assert_eq!(8, parse_roman("VIII"));
        assert_eq!(19, parse_roman("XIX"));
    }
}

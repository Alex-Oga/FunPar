pub fn main() {
    let string = format!("my friend");
    greet(&string);
    greet(&string);
}

fn greet(name: &str) {
    let mut a = 0;
    for word in name.split(' ') {
        if a == 1 {
            println!("Hello, {}", word);
        }
        a += 1;
    }
}

// Goal #1: Convert `greet` to use borrowing, not ownership, so that
// this program executes without doing any cloning.
//
// Goal #2: Use a subslice so that it prints "Hello, friend" instead of
// "Hello, my friend".

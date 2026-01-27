def reverse_string(s):
    return s[::-1]

def main():
    name = input("Enter your name: ")
    reversed_name = reverse_string(name)

    dob = input("Enter your date of birth (YYYY-MM-DD): ")
    reversed_dob = reverse_string(dob)

    print(f"Reversed name: {reversed_name}")
    print(f"Reversed Date of Birth: {reversed_dob}")

if __name__ == "__main__":
    main()
# Student Grade Calculator

A console-based student grade management system in Java. Supports multiple students, weighted grade calculations, and detailed academic reports.

## Features

- Add multiple students with unique IDs
- Add modules with credit weighting and percentage marks
- Generate a full grade report per student
- Identify best and weakest modules
- Flag failed modules automatically
- Class-wide summary with average and top performer
- Weighted average calculation based on module credits

## Grade Scale

| Grade       | Mark Range |
| ----------- | ---------- |
| Distinction | 75% – 100% |
| Merit       | 65% – 74%  |
| Pass        | 50% – 64%  |
| Fail        | 0% – 49%   |

## Concepts Demonstrated

- **ArrayList** — dynamic storage for students and modules
- **Encapsulation** — private fields in `Student` and `Module` classes
- **Weighted calculations** — credit-based average using loops
- **Input validation** — marks constrained to 0–100, credits must be positive
- **Search logic** — find student by ID or name
- **Comparisons** — finding highest/lowest module marks
- **Exception handling** — `IllegalArgumentException` for invalid data

## How to Run

### Requirements

- Java JDK 8 or higher

### Steps

```bash
# Clone the repository
git clone https://github.com/ukofi/grade-calculator.git
cd grade-calculator/src

# Compile
javac *.java

# Run
java Main
```

## Project Structure

```
grade-calculator/
└── src/
    ├── Module.java   # Module model with mark validation
    ├── Student.java  # Student model with report generation
    └── Main.java     # Entry point and menu interface
```

## Sample Report

```
==========================================
         STUDENT GRADE REPORT
==========================================
Student ID : STU001
Name       : Unathi Kofi
------------------------------------------
Module                    Credits  Mark (%)
------------------------------------------
Advanced Java             12       93.0
Programming with C#       12       94.0
DevOps Engineering        10       94.0
Solutions Development     10       79.0
------------------------------------------
Simple Average     : 90.00%
Weighted Average   : 90.23%
Overall Grade      : DISTINCTION
Best Module        : Programming with C# (94.0%)
Needs Attention    : Solutions Development (79.0%)
==========================================
```

## Author

**Unathi Kofi** — [GitHub](https://github.com/ukofi)

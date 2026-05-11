# Contributing Guidelines

Thanks for contributing to this project

This project is structured in modular Java architecture (e.g. `core`, `auth`, etc.), so contributions must respect the architecture and code quality rules below.

---

## Project Structure Rules

- All logic must be placed inside its correct module:
  - `core` → shared logic and utilities
  - `auth` → authentication-related features
  - other modules must be clearly separated by responsibility

- Do NOT mix unrelated logic between modules.

---

## Code Quality Rules

### 1. Self-explanatory code

Code must be readable without needing extra explanation.

- Use meaningful variable names
- Avoid overly complex logic in a single method
- Break logic into small, clear methods

Bad:
```java
int x = a + b;
```
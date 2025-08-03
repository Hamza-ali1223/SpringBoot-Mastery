# 🌱 Spring Security: User Registration & Authentication Flow

## 🌟 Tips

**Tip 01: How to Build Secure User Registration & Login in Spring Boot**

🔒 Use a DAO Authentication Provider with a custom `UserDetailsService` to load users from your database.

🧑‍💻 Always hash passwords with BCrypt before saving them—never store plain text passwords!

📝 When a user registers:
- Receive their username, password, and role as JSON.
- Hash the password using BCrypt.
- Save the user (with the hashed password) to the database.

🔑 When a user logs in:
- Spring Security fetches the user by username using your custom service.
- It compares the hashed password in the database with the password provided (after hashing).
- If they match, authentication succeeds!

📦 Example user JSON (for learning):
```json
{
  "id": 2,
  "password": "$2a$10$6bdvNhvAIDfdvGDX8YQMweqAG90AFb2EQxxd9D5Kzvx3PkEDBE1ta",
  "roles": "ADMIN",
  "username": "admin",
  "authorities": [
    { "authority": "ROLE_ADMIN" }
  ],
  "enabled": true,
  "credentialsNonExpired": true,
  "accountNonExpired": true,
  "accountNonLocked": true
}
```

🖼️ **Visual Flow:**
```
+-------------------+      POST /users      +-------------------+
|   Client (User)   |  ------------------>  |   UserController  |
+-------------------+   (username, pass)    +-------------------+
                                                |
                                                | 1. Hash password (BCrypt)
                                                v
                                         +-------------------+
                                         |   UserService     |
                                         +-------------------+
                                                |
                                                | 2. Save user to DB
                                                v
                                         +-------------------+
                                         |   Database        |
                                         +-------------------+

------------------- LOGIN FLOW -------------------

+-------------------+      POST /login      +-------------------+
|   Client (User)   |  ------------------>  | Spring Security   |
+-------------------+   (username, pass)    +-------------------+
                                                |
                                                | 1. Load user from DB
                                                v
                                         +-------------------+
                                         | CustomUserDetails |
                                         +-------------------+
                                                |
                                                | 2. Compare hashed passwords
                                                v
                                         +-------------------+
                                         | Authentication    |
                                         +-------------------+
```

🔄 **Control Flow Explained:**

1. **Registration Flow:**
    - User sends username, password, and role to your `/users` endpoint
    - Your controller receives this data and passes it to the service
    - The service hashes the password using BCrypt (turning "password123" into something like "$2a$10$...")
    - The user is saved to the database with the hashed password, not the original one
    - The database now has a secure record that can be used for authentication

2. **Login Flow:**
    - User sends username and password to Spring Security's `/login` endpoint
    - Spring Security asks your custom UserDetailsService to find the user by username
    - Your service fetches the user from the database (with the hashed password)
    - Spring Security takes the password the user just entered, hashes it, and compares it to the stored hash
    - If the hashes match, the user is authenticated and receives authorization based on their roles

✨ **In short:**  
Use a custom user service and BCrypt for security. Store only hashed passwords. Let Spring Security handle the rest!
```
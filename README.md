# Luminara Connect ✨

**Luminara Connect** is a Java (Spring Boot) messaging + wallet application with custom Aurora Glass design and complete mobile-number authentication.

## Complete Features
- Mobile number **sign-up and sign-in** with OTP-style verification flow.
- Multi-page web app:
  - `auth.html` → login/sign-up via mobile number
  - `index.html` → dashboard & user management
  - `chats.html` → chat creation + messaging timeline
  - `wallet.html` → top-up, transfer, transaction timeline
  - `gifts.html` → gift catalog + gift sending
- REST backend APIs for auth, users, chats, wallet transfers, gifts, and transactions.

## Tech Stack
- Java 17
- Spring Boot 3
- Maven
- Vanilla HTML/CSS/JS frontend in `src/main/resources/static`

## Run
```bash
mvn spring-boot:run
```

Then open:
- `http://localhost:8080/auth.html`

## API Overview

### Authentication
- `POST /api/auth/request-code` request OTP code by mobile number (sign-up or sign-in)
- `POST /api/auth/verify-code` verify OTP and return session token
- `GET /api/auth/me?token=...` resolve current user from token

### Users
- `POST /api/users` create user
- `GET /api/users` list users
- `GET /api/users/{userId}` get one user
- `POST /api/users/wallet/top-up` top-up wallet

### Chats
- `POST /api/chats` create chat
- `GET /api/chats` list chats
- `GET /api/chats/{chatId}` get chat details
- `POST /api/chats/{chatId}/messages` send message

### Wallet & Gifts
- `POST /api/wallet/transfer` transfer money
- `GET /api/wallet/gifts` list gift catalog
- `POST /api/wallet/gifts/send` send a gift
- `GET /api/wallet/transactions/{userId}` get user transaction timeline

## Notes
- OTP flow currently returns a `demoCode` for local/demo usage.
- For production: integrate SMS provider, token expiry/refresh, persistent database, encryption, media storage, and websocket delivery.

# Luminara Connect ✨

**Luminara Connect** is a Java (Spring Boot) messaging + wallet application with a custom **Aurora Glass** UI design (intentionally different from WhatsApp), built to be beautiful and highly functional.

## What You Get
- Multi-page web app (no missing pages):
  - `index.html` → Dashboard & user onboarding
  - `chats.html` → chat creation + messaging timeline
  - `wallet.html` → top-up, transfer, transaction timeline
  - `gifts.html` → gift catalog + gift sending
- REST backend APIs for users, chats, wallet transfers, gifts, and transactions.
- In-memory persistence for rapid prototyping.

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
- `http://localhost:8080/index.html`

## API Overview

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

## Product Direction
This project is now a complete prototype experience with dedicated pages and flows. Next production steps: auth, E2EE, persistent database, media upload/storage, websocket presence/typing, and push notifications.

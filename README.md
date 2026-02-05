# Luminara Connect ✨

**Luminara Connect** is a Java (Spring Boot) app inspired by WhatsApp, with built-in wallet features so users can:

- chat in real time-ready conversation structures,
- top up wallet balances,
- send money to each other,
- send paid digital gifts.

## Tech Stack
- Java 17
- Spring Boot 3
- Maven
- In-memory data store (for demo/prototype)

## Run
```bash
mvn spring-boot:run
```

App runs on `http://localhost:8080`.

## API Overview

### Users
- `POST /api/users` create user
- `GET /api/users` list users
- `POST /api/users/wallet/top-up` top-up wallet

### Chats
- `POST /api/chats` create 1:1 or group chat
- `GET /api/chats` list chats
- `GET /api/chats/{chatId}` get chat details
- `POST /api/chats/{chatId}/messages` send message

### Wallet & Gifts
- `POST /api/wallet/transfer` transfer money user-to-user
- `GET /api/wallet/gifts` list gift catalog
- `POST /api/wallet/gifts/send` send a gift (wallet-backed)

## Example Flow
1. Create two users.
2. Top up sender wallet.
3. Create a chat with both participants.
4. Send chat messages.
5. Transfer money and send gifts.

---
This is a strong backend foundation for a full WhatsApp-style app. To reach full production parity, next steps include auth, media storage, end-to-end encryption, delivery/read receipts, push notifications, persistent DB, and WebSocket messaging.

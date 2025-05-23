# Cricket League Franchise System – JavaFX + Networking Project

This is a JavaFX-based client-server application designed for a simulated **Cricket League**, where multiple franchise clients can connect to a central server to manage player trades, view profiles, and add new players. Built using **Java Socket Networking** and **JavaFX UI**, this project demonstrates real-time communication and basic user-role access control.

---

## Project Architecture

- **Server:** Manages all client requests, stores player data, and handles synchronization.
- **Client (Franchises):** Login-enabled clients that can buy and sell players.
- **Public Viewers:** Users without franchise login can still view player profiles and add new players.

---

## Features

### For Logged-in Franchises:
- Buy players from the market or other franchises
- Sell players
- View player profiles
- Receive updates from the server

### For General Users (Not Logged In):
- View all player profiles
- search players based on different criteria
- Add new players to the league

---

## Technologies Used

- JavaFX (for UI)
- Java Socket Programming (for client-server communication)
- Multithreading (for handling multiple clients)
- FXML (for layout design)
- CSS (for UI styling)

---

## How to Run

### Server:
1. Navigate to the `server` directory.
2. Run the server:
   ```bash
   javac Server.java
   java Server
3. Run the client program:
  ```bash
   javac App.java
   java App

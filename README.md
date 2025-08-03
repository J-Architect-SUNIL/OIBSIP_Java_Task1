# OIBSIP_Java_Task1
# Online Reservation System

## 💡 Objective
To design a Java-based console application that simulates an online reservation system, enabling users to register, log in, book train tickets, and cancel reservations.

## 🛠️ Tools & Technologies Used
- Java (JDK 22)
- Scanner class (for input)
- Vector class (for storing user and reservation details)
- Basic Java OOP concepts

## ✨ Features
- User registration and login
- Book a reservation with:
  - Train number
  - Class type
  - Date
  - Source and destination
  - Auto-generated PNR and seat number
- Cancel reservation by PNR
- Seat management
- Simple CLI interface

## 🧩 How It Works
1. User logs in or creates a new account.
2. Once logged in, user can:
   - Make a reservation
   - Cancel a reservation
   - Continue or exit
3. Randomly assigns a seat number and PNR
4. Maintains a list of active reservations
5. Cancels reservations and releases seats accordingly

## ▶️ How to Run
```bash
javac OnlineReservationSystem.java
java OnlineReservationSystem

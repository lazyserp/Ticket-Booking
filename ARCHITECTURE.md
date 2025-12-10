# Low Level Design (LLD) - IRCTC Clone

## 1. Entities (The Nouns)

### User
- **Description:** Represents a registered user of the platform.
- **Attributes:** `userId`, `name`, `password` (hashed), `ticketsBooked` (List).
- **Relationships:** - 1 User has N Tickets (1:N).

### Station
- **Description:** A physical location where trains stop.
- **Attributes:** `stationId` (e.g., NDLS), `stationName` (e.g., New Delhi).
- **Relationships:**
  - Used by `TrainStop` to define a schedule.

### Train
- **Description:** Represents the static metadata of a train.
- **Attributes:** `trainId`, `trainNo`, `trainName`, `stationTimes` (Map of Station -> Time).
- **Relationships:**
  - 1 Train runs on 1 Route (List of Stations).

### Ticket
- **Description:** Proof of a confirmed booking.
- **Attributes:** `ticketId`, `userId`, `source`, `destination`, `dateOfTravel`, `train`.
- **Relationships:**
  - Belongs to 1 User.
  - Belongs to 1 Train.

---

## 2. Services (The Verbs)

### UserBookingService
- **Responsibility:** Handles user-specific actions.
- **Methods:**
  - `loginUser()`: Authenticates credentials.
  - `signUp()`: Creates a new user.
  - `fetchBookings()`: Retrieves history.

### TrainService
- **Responsibility:** Handles train search and schedule management.
- **Methods:**
  - `searchTrains(source, destination)`: The core algorithm.
  - `addTrain()`: Admin capability to add new trains.

---

## 3. Relationships Diagram
User 1 -- * Ticket
Train 1 -- * Ticket
Train 1 -- * Station (via TrainStop)
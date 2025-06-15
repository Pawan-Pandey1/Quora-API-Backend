# Quora Clone Backend API

![Project Banner](images/QuoraBanner.png)

## 🧩 Problem Statement

This project is a backend API for a Quora-like *Question and Answer* platform. It allows users to:

- Register
- Post questions
- Answer questions
- Comment on answers and other comments
- Like content
- Follow other users
- Organize content via topic tags

The system uses *UUIDs* for unique identification and employs a *relational database* with *JPA/Hibernate* for data persistence, following a *layered architecture* (Models, DTOs, Repositories, Services, Controllers).

---

## 🚀 Project Overview

Built using *Spring Boot* and designed following *RESTful API* principles, this backend is modular, scalable, and production-ready.

---

## ✨ Key Features

- User registration and profile management
- Posting and searching questions (text/topic filter)
- Answer creation and editing
- Nested comments on answers/comments
- Like system for questions, answers, comments
- Follow/unfollow users
- Topic tagging for questions
- Use of DTOs to prevent infinite recursion and control data exposure

---
## 🏗 Architecture
````
├── main/
│ ├── java/
│ │ └── com/quora/
│ │ ├── config/
│ │ ├── controller/
│ │ ├── service/
│ │ ├── repository/
│ │ ├── model/
│ │ └── dto/
│ └── resources/
│ └── application.properties
````
---
## 🔗 Complex Relationships
![ER Diagram](images/ERdigram.png)


## 🧱 Data Models (Entities)

### 🔹 User
- id: UUID
- username: String (unique)
- email: String (unique)
- bio: String (optional)

*Relationships*:
- OneToMany → Questions, Answers, Follows

---

### 🔹 Question
- id: UUID
- title: String
- body: String
- createdAt: Timestamp

*Relationships*:
- ManyToOne → User (author)
- ManyToMany → Topic
- OneToMany → Answers

---

### 🔹 Answer
- id: UUID
- text: String
- createdAt: Timestamp

*Relationships*:
- ManyToOne → Question, User
- OneToMany → Comments

---

### 🔹 Comment
- id: UUID
- text: String
- createdAt: Timestamp

*Relationships*:
- ManyToOne → Answer, Comment (if nested), User

---

### 🔹 Topic
- id: UUID
- name: String (unique)

*Relationships*:
- ManyToMany → Questions

---

## 🔗 Entity Relationships and Joins

- User–Question: One-to-Many
- User–Answer: One-to-Many
- Question–Answer: One-to-Many
- Answer–Comment: One-to-Many
- Comment–Comment: Many-to-One (nested)
- Question–Topic: Many-to-Many
- Likes: Handled via join tables between User and liked entities

---

## 🏗 Layered Architecture

### 🧾 DTO (Data Transfer Object)
- Decouples API models from entity models
- Prevents infinite recursion
- Example: QuestionResponse, CommentRequest

---

### 🧩 Model (Entity)
- Represents DB structure
- Used by Hibernate for ORM
- Example: User, Answer, Comment

---

### 📦 Repository
- Interface for data access
- Extends JpaRepository
- Custom queries supported

---

### ⚙ Service
- Contains business logic
- Handles validation, transactions
- Example: UserService, AnswerService

---

### 🌐 Controller
- Handles HTTP requests
- Maps endpoints to services
- Returns DTO responses

---

## 📡 API Endpoints Overview

### 👤 User

- **Register a new user**:
  ![Register User](images/createuser.jpg)  
  **Purpose**: Registering new users  
  **Method**: POST  
  **API Called**: /users


- **Get user profile**:  
  ![Get User](images/getuserbyid.jpg)  
  **Purpose**: Fetch a user by ID  
  **Method**: GET  
  **API Called**: /users/{userId}

---

### ❓ Question

- **Post a new question**: 
  ![Post Question](images/createquestion.jpg)  
  **Purpose**: Creating a new question  
  **Method**: POST  
  **API Called**: /questions


- **Search questions**:   
  ![Search Questions](images/getquestion.jpg)  
  **Purpose**: Searching questions by text or tag  
  **Method**: GET  
  **API Called**: /questions/search

---

### ✍ Answer

- **Post an answer**:   
  ![Post Answer](images/createanswer.jpg)  
  **Purpose**: Post answer to a question  
  **Method**: POST  
  **API Called**: /questions/{questionId}/answers


- **Edit an answer**:  
  ![Edit Answer](images/updateanswer.jpg)  
  **Purpose**: Edit an existing answer  
  **Method**: PUT  
  **API Called**: /answers/{answerId}

---

### 💬 Comment

- **Comment on an answer**:  
  ![Comment on Answer](images/createcomment.jpg)
  **Purpose**: Comment on existing answer  
  **Method**: POST  
  **API Called**: /answers/{answerId}/comments


- **Reply to a comment**:  
  ![Reply to Comment](images/replycomment.jpg)
  **Purpose**: Comment on existing comment  
  **Method**: POST     
  **API Called**: /comments/{commentId}/comments


- **Get all comments**:  
  ![Get Comment Replies](images/getallcomment.jpg)
  **Purpose**: Comment on existing comment  
  **Method**: GET      
  **API Called**: /comments/{commentId}/comments

---

### 👍 Like

- **Like a Question**:  
  ![Like Question](images/likequestion.jpg)
  **Purpose**: Like comment  
  **Method**: POST  
  **API Called**: /questions/{questionId}/likes


- **Like an answer**:  
  ![Like Answer](images/likeanswer.jpg)
  **Purpose**: Like Answer  
  **Method**: POST   
  **API Called**: /answers/{answerId}/likes


- **Like a Comment**:  
  ![Like Comment](images/likecomment.jpg)
  **Purpose**: Like Comment  
  **Method**: POST    
  **API Called**: /comments/{commentId}/likes

---

### 🏷 Topic

- **Create a topic**:  
  ![Create Topic](images/createtopic.jpg)
  **Purpose**: Create topics  
  **Method**: POST   
  **API Called**: /topics


- **Get all topics**:  
  ![List Topics](images/getalltopics.jpg)
  **Purpose**: Get topics  
  **Method**: GET   
  **API Called**: /topics


---
### 🤝 Follow

  - **Follow a user**:  
    ![Create Topic](images/followuser.jpg)
    **Purpose**: Follow other users  
    **Method**: POST   
    **API Called**: /users/{userId}/follow/{targetUserId}

---


## 💡 Best Practices Used

- UUIDs for primary keys
- DTO usage to avoid serialization issues
- Separation of concerns via layered architecture
- Transactional service methods
- Custom search queries
- Join tables for Many-to-Many relations

---

## 🔧 How to Extend

- Add pagination to GET endpoints
- Implement notifications for likes/follows
- Add analytics (e.g., most liked answers)
- Implement role-based access (admin, user)

---

## 🚀 Getting Started

1. *Clone the repo*
   ```bash
   git clone https://github.com/your-repo/quora-backend.git
   cd quora-backend
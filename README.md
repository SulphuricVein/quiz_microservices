

# Quiz Application - Microservices Architecture

## Overview

This **Quiz Application** is built using a **microservices architecture** with **Spring Boot**. The system consists of the following services:

1. **Quiz Service**: Manages quiz creation, question retrieval for a quiz, and submission of quiz answers.
2. **Question Service**: Handles the question pool, question addition, deletion, and delivery of questions to the quiz service.
3. **API Gateway**: Provides a unified entry point for clients, routing requests to the appropriate services.
4. **Service Registry**: Using **Eureka Server**, it registers and discovers services, enabling smooth interaction between microservices.

### Technology Stack:
- **Spring Boot**
- **Eureka Server & Eureka Client** for service discovery
- **OpenFeign** for inter-service communication
- **PostgreSQL** for persistent storage of quiz and question data
- **Spring Cloud Gateway** (Reactive API Gateway) for request routing

## Architecture

### Key Components:
1. **Quiz Service**: 
    - Responsible for quiz creation and management.
    - Communicates with the Question Service to retrieve questions via OpenFeign.

2. **Question Service**:
    - Manages the question pool (CRUD operations for questions).
    - Delivers questions to the Quiz Service based on quiz requests.

3. **API Gateway**:
    - All external traffic passes through the API Gateway, which forwards requests to the Quiz and Question Services.

4. **Eureka Service Registry**:
    - Used for registering and discovering services, allowing the Quiz and Question Services to interact without hardcoded addresses.

### Microservices:
- **Quiz Service**: Handles all quiz-related operations.
- **Question Service**: Manages question-related data and provides services to the quiz service.
- **API Gateway**: Routes client requests to appropriate microservices.
- **Service Registry**: Facilitates service discovery between microservices.

---

## API Endpoints

### 1. Quiz Service (`/quiz`)
- **POST /quiz/create**  
  Creates a new quiz based on a category and number of questions.
  - Request body: `QuizDto` (includes `categoryName`, `numQuestions`, `title`)
  - Response: `String` indicating success or failure

- **POST /quiz/get/{id}**  
  Retrieves the questions for a specific quiz ID.
  - Path variable: `id` (quiz ID)
  - Response: `List<QuizWrapper>` containing questions

- **POST /quiz/submit/{id}**  
  Submits quiz answers and calculates the result.
  - Path variable: `id` (quiz ID)
  - Request body: `List<Response>` (answers to quiz questions)
  - Response: `Integer` indicating the score


### 2. Question Service (`/question`)
- **GET /question/allQuestions**  
  Fetches all available questions.
  - Response: `List<question>` containing all questions

- **GET /question/category/{category}**  
  Retrieves questions based on a specific category.
  - Path variable: `category` (question category)
  - Response: `List<question>` containing questions from the specified category

- **POST /question/add**  
  Adds a new question to the pool.
  - Request body: `question` (new question details)
  - Response: `String` indicating success or failure

- **DELETE /question/delete/{id}**  
  Deletes a question based on its ID.
  - Path variable: `id` (question ID)
  - Response: `String` indicating success or failure

#### Endpoints for the Quiz Service
- **GET /question/generate**  
  Retrieves a list of question IDs for a quiz based on the category and the number of questions.
  - Query params: `categoryName`, `numQuestions`
  - Response: `List<Integer>` (IDs of questions)

- **POST /question/fetch**  
  Fetches question details based on a list of question IDs.
  - Request body: `List<Integer>` (IDs of questions)
  - Response: `List<quizDTO>` containing question details

- **POST /question/results**  
  Calculates the quiz results based on the submitted responses.
  - Request body: `List<Response>` (submitted responses)
  - Response: `Integer` indicating the score

---

## Installation and Setup

### Prerequisites:
- **Java 21**
- **Maven**
- **PostgreSQL**

1. **Clone the repository**:
    ```bash
    git clone https://github.com/SulphuricVein/quiz_microservices.git
    ```

2. **Set up PostgreSQL**:
    - Ensure PostgreSQL is installed and running.
    - Create databases for both the **quiz service** and **question service**.

3. **Configure Environment Variables**:
    - Update the `application.yml` files for each service with your PostgreSQL database credentials and Eureka configurations.

4. **Build the Application**:
    - Navigate to the root directory of each service and build them:
    ```bash
    ./mvnw clean install
    ```

5. **Run the Application**:
    - Start the services in the following order:
        1. **Service Registry (Eureka Server)**
        2. **Question Service**
        3. **Quiz Service**
        4. **API Gateway**

    You can run each service by navigating to its root directory and using:
    ```bash
    ./mvnw spring-boot:run
    ```

---

## Running Tests

- You can run unit tests with Maven:
    ```bash
    ./mvnw test
    ```









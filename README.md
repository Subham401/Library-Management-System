# 📚 Library Management System

A JavaFX-based desktop application for managing library operations such as adding/removing members, issuing and returning books, and searching the catalog by title, author, or publisher.

## 🚀 Features

- Add or remove library members
- Add new books to the collection
- Search for books by title, author, or publisher
- Issue books to members and maintain issue records
- Return books and update inventory
- View book availability and details
- User-friendly GUI built with FXML (JavaFX)
- MySQL-based backend for data persistence

## 🛠️ Technologies Used

- Java 17
- JavaFX (FXML for UI layout)
- MySQL (JDBC for database connectivity)
- Maven for project management
- MVC architecture

## 📁 Project Structure

Library_management_System/\n
├── src/main/java/com/example/library_management_system/
│ ├── AddOrRemoveMember.java
│ ├── Book.java
│ ├── BookAdd.java
│ ├── CheckBook.java
│ ├── ConnectionClass.java
│ ├── InterfaceController.java
│ ├── Library_Management_System.java
│ ├── Member.java
│ ├── SearchBook.java
│ ├── issueRecord.java
│ └── module-info.java
├── resources/com/example/library_management_system/
│ ├── Interface.fxml
│ ├── Member.fxml
│ ├── bookDetails.fxml
│ ├── byAuthor.fxml
│ ├── byPublisher.fxml
│ ├── byTitle.fxml
│ ├── checkBook.fxml
│ ├── issueBook.fxml
│ ├── newMember.fxml
│ ├── removeMember.fxml
│ └── returnBook.fxml
├── pom.xml



## ⚙️ Getting Started

1. **Clone the repository**
   ```bash
   git clone https://github.com/Subham401/Library_management_System.git
2. **Set up MySQL Database**
    Create a database and required tables (books, members, issue_records, etc.).
    Update database credentials in ConnectionClass.java.

3. **Build and Run**
    Use Maven to build the project:
    mvn clean install
    Run using your IDE or through the command line.

📌 Note
- Make sure JavaFX SDK is properly set up in your IDE.
- Java 17+ is recommended.
- This is a desktop application (not web-based).

📄 License
This project is licensed under the MIT License - see the LICENSE file for details.

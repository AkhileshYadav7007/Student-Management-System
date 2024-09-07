# Student Management System
The Student Management System is a web-based Java application designed to manage student records. It allows administrators to add, view, update, and delete student details in a simple and organized way. This system streamlines the process of maintaining student information in schools or colleges.

View Repository https://github.com/AkhileshYadav7007/Student-Management-System

# Features
### Add Students: Add new students to the system with details such as name, age, and class.
View Student Information: View details of existing students stored in the database.
Update Student Details: Modify student details as needed.
Delete Records: Remove student records from the system when no longer needed.
Hibernate ORM: Uses Hibernate for Object-Relational Mapping (ORM), making database management more efficient and cleaner.
Simple UI: Intuitive and user-friendly interface.
# Technologies Used
Java: Backend logic for managing student data.
Hibernate: Used to handle database operations such as CRUD (Create, Read, Update, Delete) via ORM.
MySQL: Relational database to store student information.
HTML/CSS: For frontend design and layout (if applicable).
Maven: Used to manage dependencies and build the project.
Installation
Prerequisites
Java JDK (version 8 or higher)
Apache Maven (for dependency management)
MySQL (for database storage)
Hibernate (included via Maven)
Steps to Install
Clone the repository:
bash
Copy code
git clone https://github.com/AkhileshYadav7007/Student-Management-System.git
Navigate to the project directory:
bash
Copy code
cd Student-Management-System
Configure the MySQL database:
Create a new database in MySQL:
sql
Copy code
CREATE DATABASE student_management;
Update the hibernate.cfg.xml file with your database credentials (username, password, URL).
Build the project using Maven:
bash
Copy code
mvn clean install
Run the project through your preferred Java IDE or terminal.
Usage
Run the application, and it will connect to your MySQL database.
You will be able to:
Add a student record.
View all student records.
Update existing student records.
Delete student records.
All changes are reflected in real-time and persisted in the database using Hibernate.
One-to-One Explanation of Core Logic
The core logic of the Student Management System revolves around Hibernate for database management, and basic CRUD operations (Create, Read, Update, Delete) are implemented using Java classes.

1. Entity Class (Student.java):
This class represents the student object and is mapped to a database table using Hibernate annotations.

# java
Copy code
@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "name")
    private String name;
    
    @Column(name = "age")
    private int age;
    
    @Column(name = "class")
    private String className;

    // Getters and Setters
}
@Entity: Indicates that this class is a Hibernate entity.
@Table: Specifies the table name in the database.
@Id and @GeneratedValue: Define the primary key with auto-increment.
2. Hibernate Configuration (hibernate.cfg.xml):
This XML file configures Hibernate, specifying database connection details, dialect, and other settings.

xml
Copy code
<hibernate-configuration>
    <session-factory>
        <!-- Database connection settings -->
        <property name="hibernate.connection.driver_class">com.mysql.cj.jdbc.Driver</property>
        <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/student_management</property>
        <property name="hibernate.connection.username">root</property>
        <property name="hibernate.connection.password">your_password</property>

        <!-- JDBC connection pool settings -->
        <property name="hibernate.c3p0.min_size">5</property>
        <property name="hibernate.c3p0.max_size">20</property>

        <!-- SQL dialect -->
        <property name="hibernate.dialect">org.hibernate.dialect.MySQL8Dialect</property>

        <!-- Echo all executed SQL to stdout -->
        <property name="hibernate.show_sql">true</property>

        <!-- Automatically create or update database tables -->
        <property name="hibernate.hbm2ddl.auto">update</property>

        <!-- Mapped classes -->
        <mapping class="veens.Student"/>
    </session-factory>
</hibernate-configuration>
3. Adding a Student (DAO Method):
This method in the StudentDAO class handles adding a new student to the database.

java
Copy code
public void addStudent(Student student) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        session.save(student);  // Save student object to the database
        transaction.commit();   // Commit the transaction
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}
session.save(student): Adds a new student record to the database.
transaction.commit(): Commits the transaction to ensure data is saved.
4. Retrieving Students (DAO Method):
This method fetches all student records from the database.

java
Copy code
@SuppressWarnings("unchecked")
public List<Student> getAllStudents() {
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        return session.createQuery("from Student").list();  // HQL query to fetch all students
    }
}
session.createQuery("from Student"): HQL (Hibernate Query Language) query to fetch all records from the Student table.
5. Updating a Student (DAO Method):
This method updates an existing student's information.

java
Copy code
public void updateStudent(Student student) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        session.update(student);  // Update the student record
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}
session.update(student): Updates the existing student record in the database.
6. Deleting a Student (DAO Method):
This method deletes a student record by ID.

java
Copy code
public void deleteStudent(int id) {
    Transaction transaction = null;
    try (Session session = HibernateUtil.getSessionFactory().openSession()) {
        transaction = session.beginTransaction();
        Student student = session.get(Student.class, id);  // Fetch the student by ID
        if (student != null) {
            session.delete(student);  // Delete the student record
        }
        transaction.commit();
    } catch (Exception e) {
        if (transaction != null) {
            transaction.rollback();
        }
        e.printStackTrace();
    }
}
session.delete(student): Removes the student record from the database.
Contributing
Contributions are welcome! If you'd like to contribute to this project, follow these steps:

Fork the repository.
Create a new branch:
bash
Copy code
git checkout -b feature/AmazingFeature
Commit your changes:
bash
Copy code
git commit -m 'Add AmazingFeature'
Push to the branch:
bash
Copy code
git push origin feature/AmazingFeature
Open a pull request.
License
This project is open-source and available under the MIT License.

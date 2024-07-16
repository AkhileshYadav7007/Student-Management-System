import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentDetails {
    public static <StudentConnect> boolean AuthenticateAdmin(String username, String password) throws Exception {
        StudentConnect c = new StudentConnect();
        String query = "SELECT * FROM StudentLogin WHERE username= ? AND password = ?";
        PreparedStatement ps = c.conn.prepareStatement(query);
        ps.setString(1, username);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        boolean isAuthenticated = false;
        do {
            System.out.print("Enter the Username: ");
            String username = sc.nextLine();
            System.out.print("Enter your Password: ");
            String password = sc.nextLine();
            isAuthenticated = AuthenticateAdmin(username, password);
            if (isAuthenticated) {
                System.out.println("Login Successfully");
            } else {
                System.out.println("username and password does not match");
            }
        }
        while (!isAuthenticated);
        System.out.println("Welcome");
        while (isAuthenticated){
            while (true) {
                System.out.println("1. Add Student");
                System.out.println("2. Update Student");
                System.out.println("3. Delete Student");
                System.out.println("4. Display Student");
                System.out.println("5. Display Student by ID");
                System.out.println("6. Exit");
                int option = sc.nextInt();
                sc.nextLine();
                if (option == 1) {
                    System.out.println("Enter your Name:- ");
                    String Name = sc.nextLine();
                    System.out.println("Enter your E-Mail:- ");
                    String Email = sc.nextLine();
                    System.out.println("Enter your Phone No.:- ");
                    double PNo = sc.nextDouble();
                    sc.nextLine();

                    Student std = new Student(Name, Email, PNo);
                    StudentDao stdao = new StudentDao();
                    boolean isValid = stdao.addStudent(std);
                    if (isValid) {
                        System.out.println("Added Successfully");
                    } else {
                        System.out.println("UnSuccess");
                    }
                }
                else if (option == 2) {
                    System.out.println("Entre your ID for which you want to change the data:- ");
                    int id = sc.nextInt();
                    StudentDao stdupd = new StudentDao();
                    boolean isUpdate = stdupd.updateStudent(1);

                    if (isUpdate) {
                        System.out.println("Updated SuccessFully");
                    } else {
                        System.out.println("Error while Updating");
                    }
                }

                else if (option==3) {
                    System.out.println("Enter the ID which you want to Delete :- ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    StudentDao stddel = new StudentDao();
                    boolean isDelete = stddel.deleteStudent(id);
                    if(isDelete){
                        System.out.println("Deleted SuccessFully");
                    } else {
                        System.out.println("Error while Deleting");
                    }
                }

                else if (option==4) {
                    System.out.println("Display:-");
                    StudentDao std = new StudentDao();
                    List<Student> studentList = new ArrayList<>();
                    System.out.println(studentList);
                }
            }
}
}
}
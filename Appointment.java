package HospitalManagementSystem;
import java.sql.*;
import java.sql.Connection;
import java.util.Scanner;

public class Appointment {
    public static void viewAppointments(Scanner scanner, Connection connection){
        String query ="Select * from appointments;";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("+------------+-----------+------------------+");
            System.out.println("| Patient Id | Doctor id | Appointment Date |");
            System.out.println("+------------+-----------+------------------+");
            while(resultSet.next()){
                int pId=resultSet.getInt("patient_id");
                int dId=resultSet.getInt("doctor_id");
                String date=resultSet.getString("appointment_date");
                System.out.printf("| %-10s | %-8s | %-18s |\n",pId,dId,date);
                System.out.println("+------------+-----------+------------------+");

            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }
}

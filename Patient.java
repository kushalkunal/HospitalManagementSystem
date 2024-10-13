package HospitalManagementSystem;

import java.sql.*;
import java.util.Scanner;

public class Patient {
    private Connection connection;
    private Scanner scanner;

    public Patient(Connection connection, Scanner scanner){
        this.connection = connection;
        this.scanner = scanner;
    }

    public void addPatient(){
        System.out.print("Enter Patient First Name: ");

        String nameFirst= scanner.next();
        System.out.print("Enter Patient last Name: ");
        String namelast= scanner.next();
        String name=nameFirst+" "+namelast;
        System.out.print("Enter Patient Age: ");
        int age = scanner.nextInt();
        System.out.print("Enter Patient Gender: ");
        String gender = scanner.next();
        System.out.print("Enter Patient Phone Number: ");
        long phone_number= scanner.nextLong();


        try{
            String query = "INSERT INTO patients(name, age, gender,phone_number) VALUES(?, ?, ?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, name);
            preparedStatement.setInt(2, age);
            preparedStatement.setString(3, gender);
            preparedStatement.setLong(4, phone_number);
            int affectedRows = preparedStatement.executeUpdate();
            if(affectedRows>0){
                System.out.println("Patient Added Successfully!!");
            }else{
                System.out.println("Failed to add Patient!!");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void viewPatients(){
        String query = "select * from patients";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("Patients: ");
            System.out.println("+------------+--------------------+----------+------------+-----------------+");
            System.out.println("| Patient Id | Name               | Age      | Gender     | Phone Number    |");
            System.out.println("+------------+--------------------+----------+------------+-----------------+");
            while(resultSet.next()){
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                Long PhoneNumber =resultSet.getLong("phone_number");
                System.out.printf("| %-10s | %-18s | %-8s | %-10s | %-15s |\n", id, name, age, gender, PhoneNumber);
                System.out.println("+------------+--------------------+----------+------------+-----------------+");
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean getPatientById(int id){
        String query = "SELECT * FROM patients WHERE id = ?";
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                return true;
            }else{
                return false;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}

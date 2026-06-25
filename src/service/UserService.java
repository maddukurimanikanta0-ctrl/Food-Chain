package service;

import java.util.ArrayList;
import java.util.Scanner;

import model.User;
import util.DatabaseService;
import util.InputHelper;

public class UserService {

    private ArrayList<User> users =
            new ArrayList<>();

    public User register(Scanner sc) {

        System.out.println(
                "\n===== USER REGISTRATION =====");

        String name =
                InputHelper.readString(
                        sc,
                        "Enter Name : ");

        String phone =
                InputHelper.readPhone(
                        sc,
                        "Enter Phone Number : ");

        String area =
                InputHelper.readString(
                        sc,
                        "Enter Area : ");

        String city =
                InputHelper.readString(
                        sc,
                        "Enter City : ");

        String password =
                InputHelper.readPassword(
                        sc,
                        "Create Password : ");

        User user =
                new User(
                        name,
                        phone,
                        area,
                        city,
                        password);

        users.add(user);

        DatabaseService db =
                new DatabaseService();

        db.saveUser(user);

        System.out.println(
                "\nRegistration Successful");

        return user;
    }

    public User login(Scanner sc) {

        System.out.println(
                "\n===== LOGIN =====");

        String phone =
                InputHelper.readPhone(
                        sc,
                        "Phone Number : ");

        String password =
                InputHelper.readPassword(
                        sc,
                        "Password : ");

        DatabaseService db =
                new DatabaseService();

        User user =
                db.loginUser(
                        phone,
                        password);

        if(user != null) {

            System.out.println(
                    "\nWelcome "
                    + user.getName());

            return user;
        }

        System.out.println(
                "\nInvalid Login");

        return null;
    }

    public int getTotalUsers() {
        return users.size();
    }
}
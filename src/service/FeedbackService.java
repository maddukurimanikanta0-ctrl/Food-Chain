package service;

import java.util.Scanner;


import model.User;
import util.DatabaseService;
import util.InputHelper;

public class FeedbackService {

    public void collectFeedback(
            User user,
            Scanner sc) {

        System.out.println(
                "\n===== FEEDBACK =====");

        System.out.print(
                "Rating (1-5) : ");

        int rating =
                InputHelper.readPositiveInt(
                        sc,
                        "Rating (1-5) : ");

        System.out.print(
                "Comment : ");

        String comment =
                sc.nextLine();

        System.out.println(
                "\nThank You "
                + user.getName());

        System.out.println(
                "Rating : "
                + rating);

        System.out.println(
                "Feedback Saved");
        DatabaseService db =
                new DatabaseService();

        db.saveFeedback(
                user.getName(),
                rating,
                comment);
        
    }
    
}
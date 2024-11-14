package org.example.utils;

import org.example.exceptions.EntityException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import org.example.utils.ReadingTypeUtils;
public class UiUtils {


    public static int readingInt(String meaning) {
        int x;
        while (true) {
            try {
                System.out.println("Enter " + meaning + " : ");
                Scanner scanner = new Scanner(System.in);;
                String input = scanner.nextLine();
                return org.example.utils.ReadingTypeUtils.readingInt(input);
            } catch (EntityException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static String readingString(String meaning) {
        while (true) {
            try {
                System.out.println("Enter" + meaning + ": ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                return org.example.utils.ReadingTypeUtils.readingString(input);
            } catch (EntityException e) {
                System.out.println(e.getMessage());
            }

        }
    }

    public static Date readingDate(){
        while (true) {
            try {
                System.out.println("Enter the date of the appointment in yyyy-MM-dd HH:mm format : ");
                Scanner scanner = new Scanner(System.in);
                String input = scanner.nextLine();
                return org.example.utils.ReadingTypeUtils.readingDate(input);
            } catch (EntityException e) {
                System.out.println(e.getMessage());
            }

        }
    }
}

package util;

import java.util.Scanner;

public class InputHelper {

    public static int readInt(
            Scanner sc,
            String msg) {

        while(true) {

            try {

                System.out.print(msg);

                return Integer.parseInt(
                        sc.nextLine().trim());

            } catch(Exception e) {

                System.out.println(
                        "Invalid Input. Enter Numbers Only.");
            }
        }
    }

    public static int readPositiveInt(
            Scanner sc,
            String msg) {

        while(true) {

            try {

                System.out.print(msg);

                int value =
                        Integer.parseInt(
                                sc.nextLine().trim());

                if(value <= 0) {

                    System.out.println(
                            "Value must be greater than 0.");

                    continue;
                }

                return value;

            } catch(Exception e) {

                System.out.println(
                        "Invalid Input. Enter Numbers Only.");
            }
        }
    }

    public static double readDouble(
            Scanner sc,
            String msg) {

        while(true) {

            try {

                System.out.print(msg);

                return Double.parseDouble(
                        sc.nextLine().trim());

            } catch(Exception e) {

                System.out.println(
                        "Invalid Input. Enter Decimal Number.");
            }
        }
    }

    public static String readString(
            Scanner sc,
            String msg) {

        while(true) {

            System.out.print(msg);

            String value =
                    sc.nextLine().trim();

            if(!value.isBlank()) {

                return value;
            }

            System.out.println(
                    "Input Cannot Be Empty.");
        }
    }

    public static String readPhone(
            Scanner sc,
            String msg) {

        while(true) {

            System.out.print(msg);

            String phone =
                    sc.nextLine().trim();

            if(phone.matches("\\d{10}")) {

                return phone;
            }

            System.out.println(
                    "Enter Valid 10 Digit Phone Number.");
        }
    }

    public static String readPassword(
            Scanner sc,
            String msg) {

        while(true) {

            System.out.print(msg);

            String password =
                    sc.nextLine();

            if(password.length() >= 4) {

                return password;
            }

            System.out.println(
                    "Password Must Contain At Least 4 Characters.");
        }
    }

    public static String readCardNumber(
            Scanner sc,
            String msg) {

        while(true) {

            System.out.print(msg);

            String card =
                    sc.nextLine()
                    .replaceAll("\\s", "");

            if(card.matches("\\d{16}")) {

                return card;
            }

            System.out.println(
                    "Invalid Card Number.");
        }
    }

    public static String readCVV(
            Scanner sc,
            String msg) {

        while(true) {

            System.out.print(msg);

            String cvv =
                    sc.nextLine();

            if(cvv.matches("\\d{3}")) {

                return cvv;
            }

            System.out.println(
                    "Invalid CVV.");
        }
    }

    public static String readUPIPin(
            Scanner sc,
            String msg) {

        while(true) {

            System.out.print(msg);

            String pin =
                    sc.nextLine();

            if(pin.matches("\\d{4}")) {

                return pin;
            }

            System.out.println(
                    "Invalid UPI PIN.");
        }
    }

    public static String readYesNo(
            Scanner sc,
            String msg) {

        while(true) {

            System.out.print(msg);

            String value =
                    sc.nextLine()
                    .trim()
                    .toUpperCase();

            if(value.equals("Y")
                    || value.equals("N")) {

                return value;
            }

            System.out.println(
                    "Please Enter Y or N.");
        }
    }

    public static int readChoice(
            Scanner sc,
            String msg,
            int min,
            int max) {

        while(true) {

            int choice =
                    readInt(
                            sc,
                            msg);

            if(choice >= min
                    && choice <= max) {

                return choice;
            }

            System.out.println(
                    "Enter Choice Between "
                    + min
                    + " and "
                    + max);
        }
        
    }
    public static String readExpiry(
            Scanner sc,
            String msg) {

        while(true) {

            System.out.print(msg);

            String expiry =
                    sc.nextLine().trim();

            if(expiry.matches(
                    "(0[1-9]|1[0-2])/\\d{2}")) {

                return expiry;
            }

            System.out.println(
                    "Invalid Expiry Date.");
        }
    }
    public static double readPositiveDouble(
            Scanner sc,
            String msg) {

        while(true) {

            try {

                System.out.print(msg);

                double value =
                        Double.parseDouble(
                                sc.nextLine());

                if(value > 0) {

                    return value;
                }

                System.out.println(
                        "Value Must Be Greater Than 0.");

            } catch(Exception e) {

                System.out.println(
                        "Invalid Input.");
            }
        }
    }
    
}
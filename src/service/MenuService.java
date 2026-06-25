package service;

import java.util.ArrayList;

import model.MenuItem;

public class MenuService {

    public void showMenu(
            ArrayList<MenuItem> menu) {

        if(menu.isEmpty()) {

            System.out.println(
                    "\nNo Menu Available");

            return;
        }

        System.out.println(
                "\n========== MENU ==========");

        for(MenuItem item : menu) {

            System.out.println(
                    item.getSerialNo()
                    + ". "
                    + item.getItemName()
                    + "  Rs."
                    + item.getPrice());
        }
    }

    public MenuItem findItem(
            ArrayList<MenuItem> menu,
            int serialNo) {

        for(MenuItem item : menu) {

            if(item.getSerialNo()
                    == serialNo) {

                return item;
            }
        }

        return null;
    }
}
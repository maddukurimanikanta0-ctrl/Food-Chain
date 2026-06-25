package util;

import java.io.FileInputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import model.DeliveryAgent;
import model.MenuItem;
import model.Restaurant;

public class ExcelReader {

    private static final String FILE_PATH =
            "data/FoodChain_Sorted_By_Zone.xlsx";

    // ==========================================
    // LOAD RESTAURANTS
    // ==========================================

    public ArrayList<Restaurant> loadRestaurants() {

        ArrayList<Restaurant> restaurants =
                new ArrayList<>();

        try {

            FileInputStream fis =
                    new FileInputStream(FILE_PATH);

            Workbook workbook =
                    new XSSFWorkbook(fis);

            Sheet sheet =
                    workbook.getSheet("📊 Summary");

            if(sheet == null) {

                System.out.println(
                        "Summary Sheet Not Found");

                workbook.close();

                return restaurants;
            }

            System.out.println(
                    "Loading Restaurants...");

            for(int i = 8;
                i <= sheet.getLastRowNum();
                i++) {

                Row row =
                        sheet.getRow(i);

                if(row == null)
                    continue;

                try {

                    String id =
                            getCellValue(
                                    row.getCell(0));

                    String name =
                            getCellValue(
                                    row.getCell(1));

                    String location =
                            getCellValue(
                                    row.getCell(2));

                    String city =
                            getCellValue(
                                    row.getCell(3));

                    String zone =
                            getCellValue(
                                    row.getCell(8));

                    restaurants.add(
                            new Restaurant(
                                    id,
                                    name,
                                    location,
                                    city,
                                    zone));

                } catch(Exception e) {

                    continue;
                }
            }

            workbook.close();

            System.out.println(
                    "Restaurants Loaded : "
                            + restaurants.size());

        } catch(Exception e) {

        	System.out.println(
        	        "Unable To Load Excel File.");        }

        return restaurants;
    }

    // ==========================================
    // LOAD DELIVERY AGENTS
    // ==========================================

    public ArrayList<DeliveryAgent>
    loadDeliveryAgents() {

        ArrayList<DeliveryAgent> agents =
                new ArrayList<>();

        try {

            FileInputStream fis =
                    new FileInputStream(FILE_PATH);

            Workbook workbook =
                    new XSSFWorkbook(fis);

            Sheet sheet =
                    workbook.getSheet(
                            "🚴 Delivery Agents");

            if(sheet == null) {

                System.out.println(
                        "Delivery Agent Sheet Not Found");

                workbook.close();

                return agents;
            }

            System.out.println(
                    "Loading Delivery Agents...");

            for(int i = 4;
                i <= sheet.getLastRowNum();
                i++) {

                Row row =
                        sheet.getRow(i);

                if(row == null)
                    continue;

                try {

                    String agentId =
                            getCellValue(
                                    row.getCell(0));

                    String name =
                            getCellValue(
                                    row.getCell(1));

                    String zone =
                            getCellValue(
                                    row.getCell(2));

                    String phone =
                            getCellValue(
                                    row.getCell(5));

                    String vehicle =
                            getCellValue(
                                    row.getCell(6));

                    String status =
                            getCellValue(
                                    row.getCell(7));

                    double rating =
                            Double.parseDouble(
                                    getCellValue(
                                            row.getCell(9)));

                    agents.add(
                            new DeliveryAgent(
                                    agentId,
                                    name,
                                    phone,
                                    vehicle,
                                    zone,
                                    status,
                                    rating));

                } catch(Exception e) {

                    continue;
                }
            }

            workbook.close();

            System.out.println(
                    "Agents Loaded : "
                            + agents.size());

        } catch(Exception e) {

        	System.out.println(
        	        "Unable To Load Excel File.");        }

        return agents;
    }

    // ==========================================
    // LOAD MENU OF SELECTED RESTAURANT
    // ==========================================

    public ArrayList<MenuItem>
    loadMenu(String sheetName) {

        ArrayList<MenuItem> menu =
                new ArrayList<>();

        try {

            FileInputStream fis =
                    new FileInputStream(FILE_PATH);

            Workbook workbook =
                    new XSSFWorkbook(fis);

            Sheet sheet =
                    workbook.getSheet(sheetName);

            if(sheet == null) {

                System.out.println(
                        "Menu Sheet Not Found");

                workbook.close();

                return menu;
            }

            for(int i = 4;
                i <= sheet.getLastRowNum();
                i++) {

                Row row =
                        sheet.getRow(i);

                if(row == null)
                    continue;

                try {

                    int itemId =
                            Integer.parseInt(
                                    getCellValue(
                                            row.getCell(0)));

                    String itemName =
                            getCellValue(
                                    row.getCell(1));

                    double price =
                            Double.parseDouble(
                                    getCellValue(
                                            row.getCell(2))
                                            .replace("₹", "")
                                            .replace(",", ""));

                    String category =
                            getCellValue(
                                    row.getCell(3));

                    menu.add(
                            new MenuItem(
                                    itemId,
                                    itemName,
                                    price,
                                    category));

                } catch(Exception e) {

                    continue;
                }
            }

            workbook.close();

        } catch(Exception e) {

        	System.out.println(
        	        "Unable To Load Excel File.");        }

        return menu;
    }

    // ==========================================
    // CELL VALUE HELPER
    // ==========================================

    private String getCellValue(Cell cell) {

        if(cell == null)
            return "";

        switch(cell.getCellType()) {

        case STRING:

            return cell.getStringCellValue();

        case NUMERIC:

            return String.valueOf(
                    (long) cell.getNumericCellValue());

        case BOOLEAN:

            return String.valueOf(
                    cell.getBooleanCellValue());

        default:

            return cell.toString();
        }
    }
}
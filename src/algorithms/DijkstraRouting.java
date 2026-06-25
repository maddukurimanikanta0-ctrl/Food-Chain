package algorithms;

import java.util.*;

public class DijkstraRouting {

    private int lastDistance = 0;
    private int lastETA = 0;
    private int lastTrafficDelay = 0;

    public int getLastDistance() {
        return lastDistance;
    }

    public int getLastETA() {
        return lastETA;
    }

    public int getLastTrafficDelay() {
        return lastTrafficDelay;
    }
    private final Map<String, List<Edge>> graph =
            new HashMap<>();

    static class Edge {

        String destination;
        int distance;

        Edge(String destination,
             int distance) {

            this.destination = destination;
            this.distance = distance;
        }
    }

    public DijkstraRouting() {

        // ===== CENTRAL VIJAYAWADA =====

        addRoad("Benz Circle","Bandar Road",3);
        addRoad("Benz Circle","Governorpet",2);
        addRoad("Benz Circle","Patamata",2);
        addRoad("Benz Circle","Labbipet",1);
        addRoad("Benz Circle","MG Road",1);
        addRoad("Benz Circle","Suryaraopet",2);
        addRoad("Benz Circle","Gunadala",3);

        addRoad("Bandar Road","Krishnalanka",2);

        // ===== CITY =====

        addRoad("Governorpet","One Town",2);
        addRoad("Governorpet","Ajit Singh Nagar",4);
        addRoad("Governorpet","Eluru Road",2);

        addRoad("Governorpet","Satyanarayanapuram",2);

        addRoad("Governorpet","Bhavanipuram",4);

        addRoad("Bhavanipuram","Vidyadharapuram",2);
        addRoad("Suryaraopet", "MG Road", 1);
        addRoad("Suryaraopet", "Labbipet", 1);

        addRoad("MG Road", "Governorpet", 2);

        addRoad("Krishnalanka", "Bhavanipuram", 2);

        addRoad("Ajit Singh Nagar", "Payakapuram", 3);

        addRoad("Payakapuram", "Ramavarappadu", 4);

        addRoad("Ramavarappadu", "Enikepadu", 3);

        addRoad("Enikepadu", "Gannavaram", 5);

        addRoad("Kanuru", "Yanamalakuduru", 2);

        addRoad("Yanamalakuduru", "Penamaluru", 2);

        addRoad("Currency Nagar", "Kanuru", 2);

        addRoad("Auto Nagar", "Kanuru", 2);

        addRoad("One Town", "Krishnalanka", 2);

        addRoad("Satyanarayanapuram", "Suryaraopet", 2);

        // ===== EAST VIJAYAWADA =====

        addRoad("Patamata","Kanuru",3);
        addRoad("Kanuru","Poranki",2);
        addRoad("Poranki","Penamaluru",3);

        addRoad("Penamaluru","Kankipadu",4);

        addRoad("Kankipadu","Vuyyuru",8);

        addRoad("Patamata","Auto Nagar",2);

        addRoad("Auto Nagar","Ramavarappadu",4);

        addRoad("Patamata","Currency Nagar",2);

        // ===== NORTH =====

        addRoad("Labbipet","Moghalrajpuram",2);

        addRoad("Moghalrajpuram","Gunadala",3);

        addRoad("Gunadala","Gannavaram",8);

        addRoad("Gannavaram","Ibrahimpatnam",10);

        addRoad("Ibrahimpatnam","Mylavaram",12);

        addRoad("Mylavaram","Nandigama",18);

        addRoad("Nandigama","Jaggayyapeta",25);

        addRoad("Mylavaram","Tiruvuru",20);

        addRoad("Gannavaram","Nuzvid",18);

        // ===== KRISHNA DISTRICT =====

        addRoad("Gannavaram","Gudivada",12);

        addRoad("Gudivada","Kaikaluru",15);

        addRoad("Kaikaluru","Kalidindi",8);

        addRoad("Gudivada","Machilipatnam",20);
        addRoad("Vuyyuru", "Pamarru", 8);

        addRoad("Pamarru", "Gudivada", 10);

        addRoad("Gudivada", "Pedana", 10);

        addRoad("Pedana", "Machilipatnam", 8);

        addRoad("Gudivada", "Hanuman Junction", 12);

        addRoad("Hanuman Junction", "Bapulapadu", 8);

        addRoad("Bapulapadu", "Nuzvid", 10);
    }

    private void addRoad(
            String source,
            String destination,
            int distance) {

        graph.computeIfAbsent(
                source,
                k -> new ArrayList<>())
                .add(
                        new Edge(
                                destination,
                                distance));

        graph.computeIfAbsent(
                destination,
                k -> new ArrayList<>())
                .add(
                        new Edge(
                                source,
                                distance));
    }

    public void showShortestRoute(
            String restaurantArea,
            String customerArea) {

        restaurantArea =
                normalizeArea(
                        restaurantArea);

        customerArea =
                normalizeArea(
                        customerArea);

        if(!graph.containsKey(
                restaurantArea)
                ||
           !graph.containsKey(
                customerArea)) {

            lastDistance = 0;

            System.out.println(
                    "\nRoute Data Not Available");

            System.out.println(
                    "Location Not Present In Route Network");

            return;
        }

        dijkstra(
                restaurantArea,
                customerArea);
    }

   
    private String normalizeArea(String area) {

        if(area == null) {
            return "";
        }

        area = area.toLowerCase()
                   .replace(",", " ")
                   .replace("-", " ")
                   .trim();

        if(area.contains("ajit")) return "Ajit Singh Nagar";
        if(area.contains("auto")) return "Auto Nagar";
        if(area.contains("bandar")) return "Bandar Road";
        if(area.contains("benz")) return "Benz Circle";
        if(area.contains("eluru")) return "Eluru Road";
        if(area.contains("gannavaram")) return "Gannavaram";
        if(area.contains("governor")) return "Governorpet";
        if(area.contains("gudivada")) return "Gudivada";
        if(area.contains("gunadala")) return "Gunadala";
        if(area.contains("ibrahim")) return "Ibrahimpatnam";
        if(area.contains("jaggayya")) return "Jaggayyapeta";
        if(area.contains("kaikaluru")) return "Kaikaluru";
        if(area.contains("kalidindi")) return "Kalidindi";
        if(area.contains("kankipadu")) return "Kankipadu";
        if(area.contains("kanuru")) return "Kanuru";
        if(area.contains("krishnalanka")) return "Krishnalanka";
        if(area.contains("labbipet")) return "Labbipet";
        if(area.contains("mg road")) return "MG Road";
        if(area.contains("machilipatnam")) return "Machilipatnam";
        if(area.contains("mylavaram")) return "Mylavaram";
        if(area.contains("nandigama")) return "Nandigama";
        if(area.contains("nuzvid")) return "Nuzvid";
        if(area.contains("one town")) return "One Town";
        if(area.contains("patamata")) return "Patamata";
        if(area.contains("penamaluru")) return "Penamaluru";
        if(area.contains("poranki")) return "Poranki";
        if(area.contains("ramavarappadu")) return "Ramavarappadu";
        if(area.contains("suryaraopet")) return "Suryaraopet";
        if(area.contains("tiruvuru")) return "Tiruvuru";
        if(area.contains("vuyyuru")) return "Vuyyuru";
        if(area.contains("currency")) return "Currency Nagar";
        if(area.contains("satyanarayan")) return "Satyanarayanapuram";
        if(area.contains("bhavanipuram")) return "Bhavanipuram";
        if(area.contains("vidyadharapuram")) return "Vidyadharapuram";
        if(area.contains("moghalraj")) return "Moghalrajpuram";

        return area;
    }
    private void dijkstra(
            String source,
            String destination) {

        Map<String,Integer> distance =
                new HashMap<>();

        Map<String,String> parent =
                new HashMap<>();

        PriorityQueue<String> pq =
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                distance::get));

        for(String node :
                graph.keySet()) {

            distance.put(
                    node,
                    Integer.MAX_VALUE);
        }

        distance.put(
                source,
                0);

        pq.add(
                source);

        while(!pq.isEmpty()) {

            String current =
                    pq.poll();

            for(Edge edge :
                    graph.get(current)) {

                int newDistance =
                        distance.get(current)
                        + edge.distance;

                if(newDistance
                        <
                        distance.get(
                                edge.destination)) {

                    distance.put(
                            edge.destination,
                            newDistance);

                    parent.put(
                            edge.destination,
                            current);

                    pq.add(
                            edge.destination);
                }
            }
        }

        lastDistance =
                distance.get(
                        destination);

        List<String> route =
                new ArrayList<>();

        String current =
                destination;

        while(current != null) {

            route.add(current);

            current =
                    parent.get(
                            current);
        }

        Collections.reverse(
                route);

        if(lastDistance == 0) {

            lastDistance = 1;
        }

        lastTrafficDelay =
                new Random().nextInt(6);

        lastETA =
                (lastDistance * 1)
                + lastTrafficDelay;
        System.out.println(
                "\n====================================");

        System.out.println(
                "        DELIVERY PROGRESS");

        System.out.println(
                "====================================");

        if(route.size() == 1) {

            System.out.println(
                    "\nSame Area Delivery");

            System.out.println(
                    "\nRestaurant Area : "
                    + route.get(0));

            System.out.println(
                    "Customer Area : "
                    + route.get(0));
        }
        else {

            System.out.println(
                    "\nRestaurant Picked Up");

            for(String area : route) {

                System.out.println(
                        "   ↓");

                System.out.println(
                        area);
            }

            System.out.println(
                    "   ↓");

            System.out.println(
                    "Customer Location");
        }

        System.out.println(
                "\nDistance : "
                + lastDistance
                + " KM");
        System.out.println(
                "Traffic Delay : "
                + lastTrafficDelay
                + " Minutes");

        System.out.println(
                "ETA : "
                + lastETA
                + " Minutes");

        System.out.println(
                "\nAlgorithm Used : Dijkstra Shortest Path");
    }
}
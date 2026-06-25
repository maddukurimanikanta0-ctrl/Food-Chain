package service;

import java.util.ArrayList;

import model.DeliveryAgent;

public class DeliveryService {

    private String getZone(
            String area) {

        area =
                area.toLowerCase();

        if(area.contains("benz")
                || area.contains("patamata")
                || area.contains("governorpet")
                || area.contains("bandar")
                || area.contains("labbipet")
                || area.contains("suryaraopet")) {

            return "Zone A";
        }

        if(area.contains("gannavaram")
                || area.contains("ramavarappadu")
                || area.contains("poranki")
                || area.contains("kanuru")
                || area.contains("penamaluru")
                || area.contains("autonagar")) {

            return "Zone B";
        }

        return "Zone C";
    }

    public DeliveryAgent assignAgentByArea(
            ArrayList<DeliveryAgent> agents,
            String area) {

        String customerZone =
                getZone(area);

        DeliveryAgent selected =
                null;

        for(DeliveryAgent agent :
                agents) {

            if(agent.isAvailable()
                    &&
               agent.getZone()
                    .toLowerCase()
                    .contains(
                            customerZone.toLowerCase())) {

                if(selected == null
                        ||
                   agent.getRating()
                   >
                   selected.getRating()) {

                    selected =
                            agent;
                }
            }
        }

        if(selected == null) {

            for(DeliveryAgent agent :
                    agents) {

                if(agent.isAvailable()) {

                    if(selected == null
                            ||
                       agent.getRating()
                       >
                       selected.getRating()) {

                        selected =
                                agent;
                    }
                }
            }
        }

        if(selected != null) {

            selected.setAvailable(
                    false);
        }

        return selected;
    }

    public void releaseAgent(
            DeliveryAgent agent) {

        if(agent != null) {

            agent.setAvailable(
                    true);
        }
    }

    public void showAgent(
            DeliveryAgent agent) {

        if(agent == null) {

            System.out.println(
                    "\nNo Agent Available");

            return;
        }

        System.out.println(
                "\n===== DELIVERY AGENT =====");

        System.out.println(
                "Name : "
                + agent.getName());

        System.out.println(
                "Phone : "
                + agent.getPhone());

        System.out.println(
                "Vehicle : "
                + agent.getVehicle());

        System.out.println(
                "Zone : "
                + agent.getZone());

        System.out.println(
                "Rating : "
                + agent.getRating());

        System.out.println(
                "Status : "
                + (agent.isAvailable()
                ? "Available"
                : "Assigned"));
    }
}
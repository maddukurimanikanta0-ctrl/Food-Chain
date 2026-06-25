package algorithms;

import java.util.HashMap;

public class AgentAnalytics {

    static class AgentData {

        int deliveries;
        double revenue;
    }

    private static HashMap<String, AgentData>
            agents =
            new HashMap<>();

    public static void addDelivery(
            String agentName,
            double amount) {

        AgentData data =
                agents.getOrDefault(
                        agentName,
                        new AgentData());

        data.deliveries++;
        data.revenue += amount;

        agents.put(
                agentName,
                data);
    }

    public static void showAnalytics() {

        System.out.println(
                "\n===== DELIVERY AGENT ANALYTICS =====");

        for(String name :
                agents.keySet()) {

            AgentData data =
                    agents.get(name);

            System.out.println(
                    "\nAgent : "
                    + name);

            System.out.println(
                    "Deliveries : "
                    + data.deliveries);

            System.out.println(
                    "Revenue Generated : Rs."
                    + data.revenue);

            System.out.println(
                    "Average Revenue : Rs."
                    + (data.revenue /
                    data.deliveries));
        }
    }

    public static String getTopAgent() {

        String top = "None";
        int max = 0;

        for(String name :
                agents.keySet()) {

            if(agents.get(name)
                    .deliveries > max) {

                max =
                        agents.get(name)
                        .deliveries;

                top =
                        name;
            }
        }

        return top;
    }
}
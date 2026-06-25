package algorithms;

import java.util.PriorityQueue;

import model.Order;

public class HeapOrderPriority {

    private PriorityQueue<Order> queue =
            new PriorityQueue<>(
                    (o1, o2) ->
                    Integer.compare(
                            getPriority(o2),
                            getPriority(o1)));

    private int getPriority(
            Order order) {

        if(order.getAmount() >= 1000)
            return 3;

        if(order.getAmount() >= 500)
            return 2;

        return 1;
    }

    public void addOrder(
            Order order) {

        queue.offer(order);
    }

    public Order processOrder() {

        return queue.poll();
    }

    public boolean isEmpty() {

        return queue.isEmpty();
    }

    public void showQueue() {

        System.out.println(
                "\n===== ORDER PRIORITY QUEUE =====");

        for(Order order : queue) {

            System.out.println(
                    order.getOrderId()
                    + " -> Rs."
                    + order.getAmount());
        }
    }
}
package ua.kiev.supersergey.siski_bot.test;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Created by sergey on 30.11.2016.
 */
public class Test {
    public static void main(String[] args) {
        Queue<Integer> queue = new PriorityQueue<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }
    }

    private <T> T getElement(List<T> list) {
        return list.get(0);
    }
}

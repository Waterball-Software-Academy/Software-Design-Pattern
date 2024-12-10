package lib.ioc;

import org.junit.jupiter.api.Test;
import lib.commands.EchoCommandTask;
import lib.commands.RepeatCommandTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Paths;

import static java.nio.file.Files.readString;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class WaterballContainerTest {
    static class Apple {
        public int size;
        public Apple(int size) {
            this.size = size;
        }
    }

    static class Banana {
        public int length;
        public Banana(int length) {
            this.length = length;
        }
    }

    static class Basket {
        public Apple apple;
        public Banana banana;

        public Basket(Apple apple, Banana banana) {
            this.apple = apple;
            this.banana = banana;
        }
    }

    static class Order {
        public Apple apple;
        public Banana banana;
        public Basket basket;

        public Order(Apple apple, Banana banana, Basket basket) {
            this.apple = apple;
            this.banana = banana;
            this.basket = basket;
        }
    }

    @Test
    public void testDependencyInjection() {
        WaterballContainer ioc = new WaterballContainer();
        Apple apple = new Apple(20);
        Banana banana = new Banana(20);
        ioc.register(apple);
        ioc.register(banana);
        ioc.register(Basket.class);
        ioc.register(Order.class);

        Basket basket = ioc.getInstance(Basket.class);

        assertSame(basket.apple, apple);
        assertSame(basket.banana, banana);
        assertSame(basket, ioc.getInstance(Basket.class));
        assertSame(ioc.getInstance(Basket.class), ioc.getInstance(Basket.class));

        Order order = ioc.getInstance(Order.class);
        assertSame(order.apple, apple);
        assertSame(order.banana, banana);
        assertSame(order.basket, basket);
        assertSame(order, ioc.getInstance(Order.class));
    }

    @Test
    void testCommandLineTriggering() throws IOException {
        var out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
        WaterballContainer ioc = new WaterballContainer();
        ioc.register(Apple.class);
        ioc.register(Banana.class);
        ioc.registerCommand("repeat", RepeatCommandTask.class);
        ioc.registerCommand("echo", EchoCommandTask.class);

        ioc.runScript(readString(Paths.get("script.json")));

        assertEquals("Hello World\nYo\nYo\nYo\n", out.toString());
    }

}
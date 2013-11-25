package no.bouvet.lambdaws;

import java.util.Arrays;
import java.util.List;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

public class Apples {
    private static final class Apple {
        public static enum Color {RED, GREEN}

        private Color color;
        private double price;

        public Apple(Color color, double price) {
            this.color = color;
            this.price = price;
        }

        public Color getColor() {
            return color;
        }

        public double getPrice() {
            return price;
        }
    }

    private static double priceOfRedApplesUsingTraditionalJava(List<Apple> listOfApples) {
        double sum = 0.0;

        for (Apple apple : listOfApples) {
            if (apple.getColor() == Apple.Color.RED) {
                sum += apple.getPrice();
            }
        }

        return sum;
    }

    private static double priceOfRedApplesUsingStreamWithInnerClasses(List<Apple> listOfApples) {
        Predicate<Apple> redApplesOnly = new Predicate<Apple>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor() == Apple.Color.RED;
            }
        };

        Function<Apple, Double> fromApplesToPrices = new Function<Apple, Double>() {
            @Override
            public Double apply(Apple apple) {
                return apple.getPrice();
            }
        };

        BinaryOperator<Double> toSumOfPrices = new BinaryOperator<Double>() {
            @Override
            public Double apply(Double price1, Double price2) {
                return price1 + price2;
            }
        };

        return listOfApples.stream()
                .filter(redApplesOnly)
                .map(fromApplesToPrices)
                .reduce(toSumOfPrices)
                .orElse(0.0);
    }

    private static double priceOfRedApplesUsingStreamAndLambda(List<Apple> listOfApples) {
        Predicate<Apple> redApplesOnly =
                apple -> apple.getColor() == Apple.Color.RED;

        Function<Apple, Double> fromApplesToPrices =
                apple -> apple.getPrice();

        BinaryOperator<Double> toSumOfPrices =
                (price1, price2) -> price1 + price2;

        return listOfApples.stream()
                .filter(redApplesOnly)
                .map(fromApplesToPrices)
                .reduce(toSumOfPrices)
                .orElse(0.0);
    }

    private static double priceOfRedApplesUsingLambdaDoubleStream(List<Apple> listOfApples) {
        return listOfApples.stream()
                .filter(apple -> apple.getColor() == Apple.Color.RED)
                .mapToDouble(apple -> apple.getPrice())
                .sum();
    }

    public static void main(String... args) {

        List<Apple> listOfApples = Arrays.asList(
                new Apple(Apple.Color.GREEN, 5.40),
                new Apple(Apple.Color.RED, 2.30),
                new Apple(Apple.Color.RED, 4.45),
                new Apple(Apple.Color.GREEN, 1.29),
                new Apple(Apple.Color.RED, 5.90)
        );

        System.out.println(priceOfRedApplesUsingTraditionalJava(listOfApples));
        System.out.println(priceOfRedApplesUsingStreamWithInnerClasses(listOfApples));
        System.out.println(priceOfRedApplesUsingStreamAndLambda(listOfApples));
        System.out.println(priceOfRedApplesUsingLambdaDoubleStream(listOfApples));

        listOfApples.sort(comparing(Apple::getPrice));
        listOfApples.forEach(apple -> System.out.println(apple.getPrice()));
    }
}

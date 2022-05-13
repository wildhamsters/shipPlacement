package org.wildhamsters.shipplacement.configuration;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

@Test
public class ShipPlacementConfigurerTest {

    private final static int DISTINCT_PLACEMENT_TRESHOLD = 20;
    private final static Random RANDOM = new Random();

    public void shouldReturnDifferentShipPositions_whenInvokedMultipleTimes() {
        //given
        var configurer = new ShipPlacementConfigurer(10, 10, RANDOM);
        //when
        var distinctPlacements = IntStream.range(0, 20).asDoubleStream()
                .boxed()
                .map(i -> configurer.placeShips(List.of(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)))
                .distinct()
                .count();
        //then
        Assert.assertNotEquals(distinctPlacements, DISTINCT_PLACEMENT_TRESHOLD);
    }
}
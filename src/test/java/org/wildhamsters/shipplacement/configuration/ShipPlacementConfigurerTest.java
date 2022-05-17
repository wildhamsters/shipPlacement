package org.wildhamsters.shipplacement.configuration;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

import static org.testng.Assert.*;

@Test
public class ShipPlacementConfigurerTest {

    private final static int DISTINCT_PLACEMENT_TRESHOLD = 20;
    private final static Random RANDOM = new Random();

    @Test(dataProvider = "configurationInput")
    public void shouldReturnDifferentShipPositions_whenInvokedMultipleTimes(int height, int width, List<Integer> shipsToBePlaced) {
        //given
        var configurer = new ShipPlacementConfigurer(height, width, RANDOM);
        //when
        var distinctPlacements = IntStream.range(0, 20).asDoubleStream()
                .boxed()
                .map(i -> configurer.placeShips(shipsToBePlaced))
                .distinct()
                .count();
        //then
        assertTrue(distinctPlacements == DISTINCT_PLACEMENT_TRESHOLD);
    }

    @DataProvider
    Object[][] configurationInput() {
        return new Object[][] {
                {10, 10, List.of(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)},
                {10, 10, List.of(3, 3, 2, 2, 2, 1, 1, 1)},
                {10, 10, List.of(3, 2, 2, 2, 1, 1, 1)},
                {10, 10, List.of(4, 3, 2, 2, 2, 1, 1, 1)},
                {10, 10, List.of(4, 3, 3, 2, 2, 2, 1, 1, 1, 1)},
                {10, 10, List.of(4, 3, 3)}
        };
    }
}
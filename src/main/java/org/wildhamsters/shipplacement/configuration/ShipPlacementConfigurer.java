package org.wildhamsters.shipplacement.configuration;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import org.wildhamsters.shipplacement.fleet.ShipPosition;
import org.wildhamsters.shipplacement.fleet.ShipsPositions;

/**
 * Creates positions of ships on a board.
 *
 * @author Dominik Żebracki
 */
public class ShipPlacementConfigurer {

    private final int height;
    private final int width;
    private final int boardSize;
    private final Random random;

    public ShipPlacementConfigurer(int height, int width, Random random) {
        this.height = height;
        this.width = width;
        this.boardSize = width * height - 1;
        this.random = random;
    }

    // TODO relaunch placing randomizer.
    // Sometimes enters infinite loop with unlucky placing with small board and a
    // lot of ships
    // works fine with standard settings 10x10, standard fleet

    /**
     * Creates randomly generated fleet of given ship sizes.
     *
     * @param shipSizesToBePlaced ship sizes in a fleet.
     * @return ship positions.
     */
    public ShipsPositions placeShips(List<Integer> shipSizesToBePlaced) {
        var shipSizes = new ArrayList<>(shipSizesToBePlaced); // in case immutable list is passed
        shipSizes.sort(Comparator.reverseOrder());
        var board = new ConfigurationBoard(height, width);
        var fleet = new ArrayList<List<Integer>>();
        shipSizes.stream()
                .map(i -> createRandomValidMastPositions(i, board))
                .forEach(p -> {
                    board.placeShip(p);
                    fleet.add(p);
                });
        return new ShipsPositions(fleet.stream().map(ShipPosition::new).toList());
    }

    private List<Integer> createRandomValidMastPositions(int shipSize, ConfigurationBoard board) {
        var direction = establishShipDirection();
        List<Integer> mastPositions;
        do {
            mastPositions = calculateMastPositions(shipSize, random.nextInt(boardSize), direction);
        } while (!board.canShipBePlaced(mastPositions, direction));
        return mastPositions;
    }

    private List<Integer> calculateMastPositions(int shipSize, int startingPosition, ShipDirection direction) {
        var mastPositions = new ArrayList<Integer>();
        switch (direction) {
            case HORIZONTAL -> IntStream.range(0, shipSize).forEach(i -> mastPositions.add(startingPosition + i));
            case VERTICAL -> IntStream.range(0, shipSize).forEach(i -> mastPositions.add(startingPosition + i * width));
            default -> throw new IllegalArgumentException("Unknown ship placement direction.");
        }
        return mastPositions;
    }

    private ShipDirection establishShipDirection() {
        return (random.nextInt(ShipDirection.values().length) == 0) ? ShipDirection.HORIZONTAL : ShipDirection.VERTICAL;
    }

    public List<Integer> generateDefaultList() {
        return List.of(4, 3, 3, 2, 2, 2, 1, 1, 1, 1);
    }
}
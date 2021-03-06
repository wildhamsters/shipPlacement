package org.wildhamsters.shipplacement.fleet;

import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * @author Dominik Żebracki
 */
public class ShipsPositions {

    private final List<ShipPosition> shipsPosition;

    public ShipsPositions(List<ShipPosition> shipsPosition) {
        this.shipsPosition = shipsPosition;
    }

    public List<ShipPosition> getAllShipsPositions() {
        return shipsPosition;
    }

    public List<Integer> allOccupiedFields() {
        return shipsPosition.stream()
                .flatMap(p -> p.positions().stream())
                .toList();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ShipsPositions that = (ShipsPositions) o;
        var occupiedFields = allOccupiedFields();
        var thatOccupiedFields = that.allOccupiedFields();
        return occupiedFields.equals(thatOccupiedFields);
    }

    @Override
    public int hashCode() {
        return Objects.hash(IntStream
                .range(0, getAllShipsPositions().size() - 1)
                .map(Objects::hash)
                .sum());
    }
}

package org.wildhamsters.shipplacement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.wildhamsters.shipplacement.configuration.ShipPlacementConfigurer;
import org.wildhamsters.shipplacement.fleet.ShipPosition;
import org.wildhamsters.shipplacement.fleet.ShipsPositions;

@SpringBootApplication
public class ShipPlacement {

	public static void main(String[] args) {

		List<Integer> list = new ArrayList<>();
		list.add(4);
		list.add(3);
		list.add(3);
		list.add(2);
		list.add(2);
		list.add(2);
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		ShipPlacementConfigurer placer = new ShipPlacementConfigurer(10, 10, new Random());
		ShipsPositions positions = placer.placeShips(list);

		List<ShipPosition> pos = positions.getAllShipsPositions();

		for (ShipPosition shipPosition : pos) {
			System.out.println(shipPosition.toString());
		}

		SpringApplication.run(ShipPlacement.class, args);
	}

}

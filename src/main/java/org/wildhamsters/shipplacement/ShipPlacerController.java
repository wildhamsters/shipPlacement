package org.wildhamsters.shipplacement;

import java.util.Random;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wildhamsters.shipplacement.configuration.ShipPlacementConfigurer;
import org.wildhamsters.shipplacement.fleet.ShipsPositions;

@Controller
class ShipPlacerController {
    Random random = new Random();

    @GetMapping("/placeShips")
    @ResponseBody
    public ShipsPositions placeShips(@RequestParam(name = "height", required = false, defaultValue = "10") int height,
            @RequestParam(name = "width", required = false, defaultValue = "10") int width) {
        ShipPlacementConfigurer spc = new ShipPlacementConfigurer(height, width, random);
        return spc.placeShips(spc.generateDefaultList());
    }
}

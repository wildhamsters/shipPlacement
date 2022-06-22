package org.wildhamsters.shipplacement;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import org.wildhamsters.shipplacement.configuration.ShipPlacementConfigurer;
import org.wildhamsters.shipplacement.fleet.PositionsDTO;

import java.util.Random;

@Component
class MessageListener {

    private final RabbitTemplate rabbitTemplate;
    Random random = new Random();

    MessageListener(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RabbitListener(queues = RabbitMQConfig.REQUEST_QUEUE)
    void listener(String message) {
        System.out.println(message);
        ShipPlacementConfigurer spc = new ShipPlacementConfigurer(10, 10, random);
        rabbitTemplate.convertAndSend(RabbitMQConfig.MESSAGE_EXCHANGE, RabbitMQConfig.POSITIONS_KEY,
                new PositionsDTO(spc.placeShips(spc.generateDefaultList()).getAllShipsPositions()));
    }
}

package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.enums.PaymentMethodEnum;
import com.lucalucenak.Noxus.models.*;
import com.lucalucenak.Noxus.models.pks.OrderDrinkPk;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@SpringJUnitConfig
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class OrderDrinkRepositoryTest {

    @Autowired
    private OrderDrinkRepository orderDrinkRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private DrinkRepository drinkRepository;
    @Autowired
    private SizeRepository sizeRepository;
    @Autowired
    private StatusRepository statusRepository;
    @Autowired
    private PaymentMethodRepository paymentMethodRepository;
    @Autowired
    private DeliveryTypeRepository deliveryTypeRepository;
    @Autowired
    private NeighbourhoodRepository neighbourhoodRepository;
    @Autowired
    private ClientAccountRepository clientAccountRepository;
    @Autowired
    private AddressRepository addressRepository;

    SizeModel sizeModel;
    StatusModel statusModel;
    PaymentMethodModel paymentMethodModel;
    DeliveryTypeModel deliveryTypeModel;
    NeighbourhoodModel neighbourhoodModel;
    ClientAccountModel clientAccountModel;
    AddressModel addressModel;
    DrinkModel drinkModel;
    OrderModel orderModel;
    OrderDrinkPk orderDrinkPk;
    OrderDrinkModel orderDrinkModel;

    @BeforeEach
    public void setUp() {
        sizeModel = SizeModel.builder().size("1000ML").build();
        sizeRepository.save(sizeModel);
        statusModel = StatusModel.builder().status("ACTIVE").build();
        statusRepository.save(statusModel);
        paymentMethodModel = PaymentMethodModel.builder().paymentMethod(PaymentMethodEnum.PIX).build();
        paymentMethodRepository.save(paymentMethodModel);
        deliveryTypeModel = DeliveryTypeModel.builder().deliveryType("DELIVERY").build();
        deliveryTypeRepository.save(deliveryTypeModel);
        neighbourhoodModel = NeighbourhoodModel.builder().neighbourhood("TEST").deliveryTax(10.0).build();
        neighbourhoodRepository.save(neighbourhoodModel);
        clientAccountModel = ClientAccountModel.builder()
                .firstName("Test first name")
                .lastName("test last name")
                .cpf("731.003.230-68")
                .email("mermaidlove@moneypayday.biz")
                .placedOrdersQuantity(10)
                .status(statusModel)
                .build();
        clientAccountRepository.save(clientAccountModel);
        addressModel = AddressModel.builder()
                .streetName("Rua Test")
                .houseNumber("83")
                .city("Campina Grande")
                .cep("12345-678")
                .complement("Complement Test")
                .referencePoint("Reference point test")
                .neighbourhood(neighbourhoodModel)
                .clientAccount(clientAccountModel)
                .build();
        addressRepository.save(addressModel);

        drinkModel = DrinkModel.builder()
                .name("CALDO DE KENGA")
                .price(18.50)
                .build();
        drinkRepository.save(drinkModel);

        orderModel = OrderModel.builder()
                .orderPrice(drinkModel.getPrice())
                .observation("OBSERVATION_TEST")
                .dispatchTime(LocalDateTime.now().plusHours(2))
                .arrivalForecast(LocalDateTime.now())
                .status(statusModel)
                .address(addressModel)
                .clientAccount(clientAccountModel)
                .paymentMethod(paymentMethodModel)
                .deliveryType(deliveryTypeModel)
                .build();
        orderRepository.save(orderModel);

        orderDrinkPk = OrderDrinkPk.builder()
                .order(orderModel)
                .drink(drinkModel)
                .build();

        orderDrinkModel = OrderDrinkModel.builder()
                .id(orderDrinkPk)
                .quantity(10)
                .build();
        orderDrinkRepository.save(orderDrinkModel);
    }

    @Test
    public void OrderDrinkRepository_DeleteByIdOrderId_ReturnNothing() {
        // Arrange at setUp

        // Act
        Assertions.assertTrue(orderDrinkRepository.existsById(orderDrinkPk));
        orderDrinkRepository.deleteByIdOrderId(orderModel.getId());

        // Assert
        Assertions.assertFalse(orderDrinkRepository.existsById(orderDrinkPk));
    }

    @Test
    public void OrderDrinkRepository_ExistsByIdOrderId_ReturnTrue() {
        // Assert
        Assertions.assertTrue(orderDrinkRepository.existsByIdOrderId(orderModel.getId()));
    }

    @Test
    public void OrderDrinkRepository_ExistsByIdOrderId_ReturnFalse() {
        // Assert
        orderDrinkRepository.delete(orderDrinkModel);
        Assertions.assertFalse(orderDrinkRepository.existsByIdOrderId(orderModel.getId()));
    }

    @Test
    public void OrderDrinkRepository_FindByIdOrderId_ReturnListOfOrderSoup() {
        // Arrange

        // Act
        List<Optional<OrderDrinkModel>> orderDrinksOptional = orderDrinkRepository.findByIdOrderId(orderModel.getId());

        // Assert
        for (Optional<OrderDrinkModel> i : orderDrinksOptional) {
            Assertions.assertTrue(i.isPresent());
            Assertions.assertEquals(orderModel, i.get().getId().getOrder());
        }
    }
}

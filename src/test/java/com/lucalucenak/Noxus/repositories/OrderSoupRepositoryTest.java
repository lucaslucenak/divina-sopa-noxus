package com.lucalucenak.Noxus.repositories;

import com.lucalucenak.Noxus.enums.PaymentMethodEnum;
import com.lucalucenak.Noxus.models.*;
import com.lucalucenak.Noxus.models.pks.OrderSoupPk;
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
public class OrderSoupRepositoryTest {

    @Autowired
    private OrderSoupRepository orderSoupRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private SoupRepository soupRepository;
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
    SoupModel soupModel;
    OrderModel orderModel;
    OrderSoupPk orderSoupPk;
    OrderSoupModel orderSoupModel;

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

        soupModel = SoupModel.builder()
                .name("CALDO DE KENGA")
                .price(18.50)
                .size(sizeModel)
                .build();
        soupRepository.save(soupModel);

        orderModel = OrderModel.builder()
                .orderPrice(soupModel.getPrice())
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

        orderSoupPk = OrderSoupPk.builder()
                .order(orderModel)
                .soup(soupModel)
                .build();

        orderSoupModel = OrderSoupModel.builder()
                .id(orderSoupPk)
                .quantity(10)
                .build();
        orderSoupRepository.save(orderSoupModel);
    }

    @Test
    public void OrderSoupRepository_DeleteByIdOrderId_ReturnNothing() {
        // Arrange at setUp

        // Act
        Assertions.assertTrue(orderSoupRepository.existsById(orderSoupPk));
        orderSoupRepository.deleteByIdOrderId(orderModel.getId());

        // Assert
        Assertions.assertFalse(orderSoupRepository.existsById(orderSoupPk));
    }

    @Test
    public void OrderSoupRepository_ExistsByIdOrderId_ReturnTrue() {
        // Assert
        Assertions.assertTrue(orderSoupRepository.existsByIdOrderId(orderModel.getId()));
    }

    @Test
    public void OrderSoupRepository_ExistsByIdOrderId_ReturnFalse() {
        // Assert
        orderSoupRepository.delete(orderSoupModel);
        Assertions.assertFalse(orderSoupRepository.existsByIdOrderId(orderModel.getId()));
    }

    @Test
    public void OrderSoupRepository_FindByIdOrderId_ReturnListOfOrderSoup() {
        // Arrange

        // Act
        List<Optional<OrderSoupModel>> orderSoupModelsOptional = orderSoupRepository.findByIdOrderId(orderModel.getId());

        // Assert
        for (Optional<OrderSoupModel> i : orderSoupModelsOptional) {
            Assertions.assertTrue(i.isPresent());
            Assertions.assertEquals(orderModel, i.get().getId().getOrder());
        }
    }
}

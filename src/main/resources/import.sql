insert into status(status, created_at, updated_at) values ('ACTIVE', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('ORDERED', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('FINISHED', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('INACTIVE', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('EXPIRED', NOW(), NOW());
insert into delivery_type(delivery_type, created_at, updated_at) values ('DELIVERY', NOW(), NOW());
insert into delivery_type(delivery_type, created_at, updated_at) values ('WITHDRAWAL', NOW(), NOW());
insert into size(size, status_id, created_at, updated_at) values ('ML_300', 1, NOW(), NOW());
insert into payment_method(payment_method, created_at, updated_at) values ('CASH', NOW(), NOW());

insert into distance_tax(tax, initial_distance, final_distance, status_id, created_at, updated_at) values (7.0, 2.0, 5.0, 1, NOW(), NOW());
insert into deliveryman(name, cellphone_number, status_id, created_at, updated_at) values ('Lucas', '83986907270', 1, NOW(), NOW());


insert into neighbourhood(neighbourhood, delivery_tax, status_id, created_at, updated_at) values ('SAO_JOSE', 7.0, 1, NOW(), NOW());
insert into client_account(first_name, last_name, cpf, email, cellphone_number, placed_orders_quantity, status_id, password, role, created_at, updated_at) values ('Lucas', 'de Lucena Siqueira', '108.917.264-89', 'lucas.lucenak@gmail.com', '83986907270', 10, 1, '$2a$10$.e59Lm98RsgIiO5LVHVXAuWkMWUo0tMDebatRvjr2kNEhtGJ8pALW', 'ADMIN', NOW(), NOW());
insert into address(street_name, house_number, city, cep, complement, reference_point, neighbourhood_id, client_account_id, status_id, created_at, updated_at) values ('Rua dos bobos', '0', 'Campina Grande', 'CEP', 'não tem nada', 'unica rua do bairro', 1, 1, 1, NOW(), NOW());

--insert into soup (name, price, size_id, status_id, created_at, updated_at) values ('FEIJÃO', 10.00, 1, 1, NOW(), NOW());
--insert into drink (name, price, status_id, created_at, updated_at) values ('COCA COLA 500ML', 8.0, 1, NOW(), NOW());

insert into delivery (address_id, deliveryman_id, delivery_type_id, distance_tax_id, status_id, tax, distance, created_at, updated_at) values (1, 1, 1, 1, 1, 5.0, 2.3, NOW(), NOW());

--insert into orderr(order_price, observation, dispatch_time, arrival_forecast, status_id, client_account_id, payment_method_id, delivery_id, created_at, updated_at) values (25.0, 'sem torrada', '2023-07-27T15:30:00', '2023-07-27T16:15:00', 1, 1, 1, 1, NOW(), NOW());
--
--insert into order_drink(order_id, drink_id, quantity) values (1, 1, 1);
--insert into order_soup(order_id, soup_id, quantity) values (1, 1, 1);
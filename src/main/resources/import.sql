insert into status(status, created_at, updated_at) values ('ACTIVE', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('ORDERED', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('FINISHED', NOW(), NOW());
insert into delivery_type(delivery_type, created_at, updated_at) values ('DELIVERY', NOW(), NOW());
insert into size(size, created_at, updated_at) values ('ML_300', NOW(), NOW());
insert into payment_method(payment_method, created_at, updated_at) values ('CASH', NOW(), NOW());

insert into neighbourhood(neighbourhood, delivery_tax, created_at, updated_at) values ('SAO_JOSE', 7.0, NOW(), NOW());
insert into client_account(first_name, last_name, cpf, email, placed_orders_quantity, status_id, created_at, updated_at) values ('Lucas', 'de Lucena Siqueira', '123.123.123-12', 'lucas.lucenak@gmail.com', 10, 1, NOW(), NOW());
insert into address(street_name, house_number, city, cep, complement, reference_point, neighbourhood_id, client_account_id, status_id, created_at, updated_at) values ('Rua dos bobos', '0', 'Campina Grande', 'CEP', 'não tem nada', 'unica rua do bairro', 1, 1, 1, NOW(), NOW());

insert into soup (name, price, size_id, created_at, updated_at) values ('FEIJÃO', 10.00, 1, NOW(), NOW());
insert into drink (name, price, created_at, updated_at) values ('COCA COLA 500ML', 8.0, NOW(), NOW());

insert into orderr(order_price, observation, dispatch_time, arrival_forecast, status_id, address_id, client_account_id, payment_method_id, delivery_type_id, created_at, updated_at) values (25.0, 'sem torrada', '2023-07-27T15:30:00', '2023-07-27T16:15:00', 1, 1, 1, 1, 1, NOW(), NOW());

insert into order_drink(order_id, drink_id, quantity) values (1, 1, 1);
insert into order_soup(order_id, soup_id, quantity) values (1, 1, 1);
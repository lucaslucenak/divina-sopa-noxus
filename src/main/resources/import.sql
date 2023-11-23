insert into status(status, created_at, updated_at) values ('ACTIVE', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('ORDERED', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('FINISHED', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('INACTIVE', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('EXPIRED', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('DISPATCHED', NOW(), NOW());
insert into status(status, created_at, updated_at) values ('CANCELLED', NOW(), NOW());

insert into delivery_type(delivery_type, created_at, updated_at) values ('DELIVERY', NOW(), NOW());
insert into delivery_type(delivery_type, created_at, updated_at) values ('WITHDRAWAL', NOW(), NOW());
insert into size(size, status_id, created_at, updated_at) values ('ML_300', 1, NOW(), NOW());
insert into payment_method(payment_method, status_id, created_at, updated_at) values ('CASH', 1, NOW(), NOW());
insert into payment_method(payment_method, status_id, created_at, updated_at) values ('PIX', 1, NOW(), NOW());

insert into distance_tax(tax, initial_distance, final_distance, status_id, created_at, updated_at) values (7.0, 2.0, 5.0, 1, NOW(), NOW());
insert into deliveryman(name, cellphone_number, status_id, created_at, updated_at) values ('Lucas', '83986907270', 1, NOW(), NOW());

insert into neighbourhood(neighbourhood, delivery_tax, status_id, created_at, updated_at) values ('SAO_JOSE', 70.0, 1, NOW(), NOW());
insert into client_account(first_name, last_name, cpf, email, cellphone_number, placed_orders_quantity, status_id, password, role, created_at, updated_at) values ('Lucas', 'de Lucena Siqueira', '10891726489', 'lucas.lucenak@gmail.com', '83986907270', 10, 1, '$2a$10$.e59Lm98RsgIiO5LVHVXAuWkMWUo0tMDebatRvjr2kNEhtGJ8pALW', 'ADMIN', NOW(), NOW());
insert into address(street_name, house_number, city, cep, complement, reference_point, neighbourhood_id, client_account_id, status_id, created_at, updated_at) values ('Rua dos bobos', '0', 'Campina Grande', 'CEP', 'não tem nada', 'unica rua do bairro', 1, 1, 1, NOW(), NOW());

insert into delivery (address_id, deliveryman_id, delivery_type_id, distance_tax_id, status_id, tax, distance, created_at, updated_at) values (1, 1, 1, 1, 1, 5.0, 2.3, NOW(), NOW());
insert into coupon (description, coupon_value, minimum_order_value, max_usages, start_at, finish_at, status_id) values ('Pedido acima de R$50,00', 10.0, 50.0, 1, NOW(), NOW(), 1);
insert into product_type (type, description, status_id, created_at, updated_at) values ('DIVERSOS', 'Produtos diversos', 1, NOW(), NOW());
insert into product_type (type, description, status_id, created_at, updated_at) values ('Sopas', 'Sopas de todos os tipos', 1, NOW(), NOW());
insert into product_type (type, description, status_id, created_at, updated_at) values ('Bebidas', 'Bebidas de todos os sabores', 1, NOW(), NOW());
insert into product_type (type, description, status_id, created_at, updated_at) values ('Açaí', 'Açaí', 1, NOW(), NOW());
insert into product (name, description, price, image_url, size_id, status_id, product_type_id, created_at, updated_at) values ('CALDO DE KENGA', 'Delicioso caldo de kenga (creme de macaxeira, bacon, calabresa, frango desfiado, coentro e especiarias da casa.)', 8.00, 'https://zaun-test.s3.amazonaws.com/caldoDeKenga.jpeg', 1, 1, 1, NOW(), NOW());
insert into product (name, description, price, image_url, size_id, status_id, product_type_id, created_at, updated_at) values ('CABEÇA DE GALO', 'Farinha de mandioca, OVO DE CAPOEIRA, OVO DE CODORNA, alho, cebola, coentro, cebolinha e especiarias da casas.', 9.00, 'https://zaun-test.s3.amazonaws.com/caldoCabecaGalo.jpeg', 1, 1, 1, NOW(), NOW());
insert into product (name, description, price, image_url, size_id, status_id, product_type_id, created_at, updated_at) values ('CARNE', 'Sopa de Carne.', 9.00, 'https://zaun-test.s3.amazonaws.com/sopaCarne.jpeg', 1, 1, 2, NOW(), NOW());
insert into product (name, description, price, image_url, size_id, status_id, product_type_id, created_at, updated_at) values ('CHARQUE', 'Deliciosa sopa de charque (macarrão, legumes, pedaços de charque e especiarias da casa), acompanhada de torradas.', 123.00, 'https://zaun-test.s3.amazonaws.com/sopaCharque.jpeg', 1, 1, 2, NOW(), NOW());
insert into product (name, description, price, image_url, size_id, status_id, product_type_id, created_at, updated_at) values ('CARNE DE SOL', 'Deliciosa sopa de carne de sol.', 11.00, 'https://zaun-test.s3.amazonaws.com/sopaCarneSol.jpeg', 1, 1, 2, NOW(), NOW());
insert into product (name, description, price, image_url, size_id, status_id, product_type_id, created_at, updated_at) values ('FEIJÃO COM CHARQUE', 'Deliciosa sopa de feijão com charque (macarrão, legumes selecionados, charque cortada e especiarias da casa), acompanhada de torradas.', 9.00, 'https://zaun-test.s3.amazonaws.com/sopaFeijaoCarne.jpeg', 1, 1, 2, NOW(), NOW());
insert into product (name, description, price, image_url, size_id, status_id, product_type_id, created_at, updated_at) values ('Açaí 1litro', '1litro do melhor e mais puro açaí. Se desejar pode pedir complementos a parte.', 22.00, 'https://zaun-test.s3.amazonaws.com/acaiUm.jpeg', 1, 1, 4, NOW(), NOW());
insert into product (name, description, price, image_url, size_id, status_id, product_type_id, created_at, updated_at) values ('Faça do seu jeito', '', 12.99, 'https://zaun-test.s3.amazonaws.com/acaiDois.jpeg', 1, 1, 4, NOW(), NOW());

--insert into soup (name, price, size_id, status_id, created_at, updated_at) values ('FEIJÃO', 10.00, 1, 1, NOW(), NOW());
--insert into drink (name, price, status_id, created_at, updated_at) values ('COCA COLA 500ML', 8.0, 1, NOW(), NOW());

--insert into orderr(order_price, observation, dispatch_time, arrival_forecast, status_id, client_account_id, payment_method_id, delivery_id, created_at, updated_at) values (25.0, 'sem torrada', '2023-07-27T15:30:00', '2023-07-27T16:15:00', 1, 1, 1, 1, NOW(), NOW());

--insert into order_drink(order_id, drink_id, quantity) values (1, 1, 1);
--insert into order_soup(order_id, soup_id, quantity) values (1, 1, 1);
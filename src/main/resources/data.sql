
insert into balance(balance_id, balance)
values(100, 101);

INSERT INTO product (product_id, name, status, order_cnt, is_deleted, created_at)
VALUES (100, 'Sample', 0, 10, 0, CURRENT_DATE);


INSERT INTO product_option(product_option_id, product_id, stock, name, status, price)
VALUES(101, 100, 10, 'Option 1', 0, 2000);


INSERT INTO product_option(product_option_id, product_id, stock, name, status, price)
VALUES(102, 100, 10, 'Option 2', 0, 3000);


INSERT INTO product_option(product_option_id, product_id, stock, name, status, price)
VALUES(103, 100, 10, 'Option 3', 0, 5000);
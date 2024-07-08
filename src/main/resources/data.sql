
insert into balance(balance_id, balance)
values(100, 101);

INSERT INTO product (product_id, name, status, order_cnt)
VALUES (100, 'Sample', 'inactive', 10);


INSERT INTO product_option(product_option_id, product_id, stock, name, status)
VALUES(101, 100, 10, 'Option 1', 'inactive');


INSERT INTO product_option(product_option_id, product_id, stock, name, status)
VALUES(102, 100, 10, 'Option 2', 'inactive');


INSERT INTO product_option(product_option_id, product_id, stock, name, status)
VALUES(103, 100, 10, 'Option 3', 'inactive');
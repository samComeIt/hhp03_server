CREATE INDEX idx_product_status ON PRODUCT(status);
CREATE INDEX idx_product_option_status ON PRODUCT_OPTION(status);

CREATE INDEX idx_order_status ON "order"(order_id, status);
CREATE INDEX idx_order_updated_at ON "order"(updated_at);

CREATE INDEX idx_order_item_status ON ORDER_ITEM(status);

CREATE INDEX idx_payment_status ON PAYMENT(payment_id, status);

insert into balance(balance_id, balance)
values(100, 100000);

INSERT INTO product (product_id, name, status, order_cnt, is_deleted, created_at)
VALUES (100, 'Sample', 0, 10, 0, CURRENT_DATE);


INSERT INTO product_option(product_option_id, product_id, stock, name, status, price)
VALUES(101, 100, 10, 'Option 1', 0, 2000);


INSERT INTO product_option(product_option_id, product_id, stock, name, status, price)
VALUES(102, 100, 10, 'Option 2', 0, 3000);


INSERT INTO product_option(product_option_id, product_id, stock, name, status, price)
VALUES(103, 100, 10, 'Option 3', 0, 5000);

INSERT INTO cart02(cart_id, product_id, product_option_id, quantity, single_price, total_price, user_id, product_name, product_option_name)
VALUES(1, 100, 101, 2, 2000, 4000, 100, 'Sample', 'Option 1');

INSERT INTO order_sheet(order_sheet_id)
VALUES(1);

INSERT INTO order_sheet_item(order_sheet_id, order_sheet_item_id, cart_id, status, product_id, product_option_id, single_price, total_cnt, total_price, pname, poption_name)
VALUES(1,1,1,1,100,101,2000,2, 4000, 'Sample', 'Option 1');

INSERT INTO "order"(order_id, status)
VALUES(1, 1);

INSERT INTO order_item(order_item_id, order_id, status, product_id, product_option_id, single_price, total_price, pname, poption_name)
VALUES(1,1,1,100,101, 2000, 4000, 'Sample', 'Option 1');
-- products
INSERT INTO products (VERSION, PRODUCT_NAME, PRICE, DESCRIPTION, IMAGE, RATING)
VALUES (0, "Telefon mobil Samsung Galaxy S9, Dual SIM, 64GB, 4G, Purple", 3899, "some description", "samsung.jpg", 5);

INSERT INTO products (VERSION, PRODUCT_NAME, PRICE, DESCRIPTION, IMAGE, RATING)
VALUES (0, "Telefon mobil Apple iPhone X, 256GB, 4G, Space Grey", 6299, "some description", "iphonex.jpg", 3);

INSERT INTO products (VERSION, PRODUCT_NAME, PRICE, DESCRIPTION, IMAGE, RATING)
VALUES (0, "Telefon Mobil Google Pixel 2 XL, 64GB, Just Black", 4399, "some description", "pixel2.jpg", 4);

-- users + customer
INSERT INTO users (VERSION, EMAIL, PASSWORD, ROLE)
VALUES (0, "gutabogdanvlad@gmail.com", "parola", "ADMIN");
INSERT INTO customers(VERSION, ADDRESS, FIRST_NAME, LAST_NAME, USER_ID)
VALUES (0, "some address", "Bogdan", "Guta", 1);
UPDATE users SET CUSTOMER_ID = 1 WHERE ID = 1;

INSERT INTO users (VERSION, EMAIL, PASSWORD, ROLE)
VALUES (0, "jon@snow.com", "parola", "USER");
INSERT INTO customers(VERSION, ADDRESS, FIRST_NAME, LAST_NAME, USER_ID)
VALUES (0, "some address near the Wall", "Jon", "Snow", 2);
UPDATE users SET CUSTOMER_ID = 2 WHERE ID = 2;

-- comments
INSERT INTO comments (VERSION, NAME, EMAIL, REVIEW, LIKES, PRODUCT_ID)
VALUES (0, "Jon Snow", "jon@snow.com", "I know nothing", 1, 2);
INSERT INTO comments (VERSION, NAME, EMAIL, REVIEW, LIKES, PRODUCT_ID)
VALUES (0, "Alucard", "alucard@seriously.com", "Alucard reversed is Dracula", 4, 3);

-- orders + sales
INSERT INTO orders (VERSION, ORDERSTATUS, TRACKINGNUMBER, CUSTOMER_ID)
VALUES (0, 0, "134053496404", 1);
INSERT INTO sales(VERSION, ORDER_ID, PRODUCT_ID, QUANTITY, TOTALPRICE)
VALUES (0, 1, 3, 1, 4399);


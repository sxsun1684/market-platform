USE market_system;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


-- ----------------------------
-- Table structure for address
-- ----------------------------
DROP TABLE IF EXISTS `address`;
CREATE TABLE `address`
(
    `id`          int(10)                                                       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`     int(10)                                                       NULL DEFAULT NULL COMMENT 'User ID',
    `username`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Recipient',
    `useraddress` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Delivery Address',
    `phone`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Contact Phone',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 7
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Address Table'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of address
-- ----------------------------
INSERT INTO `address`
VALUES (1, 5, 'Alice1', 'No. 888, Pudong New Area, Shanghai', '1234567890');
INSERT INTO `address`
VALUES (4, 5, 'Alice2', 'No. 888, Hefei, Anhui Province', '1234567891');
INSERT INTO `address`
VALUES (5, 5, 'Alice3', '600 Wanping South Road, Xuhui District, Shanghai', '1234567892');
INSERT INTO `address`
VALUES (6, 9, 'Alice4', '22335 Foothill Blvd, Hayward, California, USA', '1234567893');

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
    `id`       int(10)                                                       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Username',
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Password',
    `name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Name',
    `avatar`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Avatar',
    `role`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Role',
    `phone`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Phone',
    `email`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Email',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Admin Table'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin`
VALUES (1, 'admin', '123456', 'Administrator', 'http://localhost:9090/files/admin001.png', 'ADMIN',
        '13677889922', 'admin001@northeastern.edu');

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business`
(
    `id`          int(10)                                                       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Username',
    `password`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Password',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Store Name',
    `avatar`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Avatar',
    `role`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Role',
    `phone`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Phone',
    `email`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Email',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Business Description',
    `status`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Review Status',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Business Table'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business`
VALUES (2, 'hkneu', '123456', 'Hena', 'http://localhost:9090/files/b1.jpg', 'BUSINESS',
        '74174741', 'hk@northeastern.edu',
        'Hi, I am a Northeastern lecturer and have a few items to trade :)',
        'Reviewed');
INSERT INTO `business`
VALUES (3, 'djbneu', '123456', ' DaJiebao', 'http://localhost:9090/files/b2.jpg', 'BUSINESS',
        '925594438', 'dajiebao@northeastern.edu',
        'New CS student living on Oakland campus',
        'Under Review');
INSERT INTO `business`
VALUES (4, 'ckneu', '123456', 'Christos', 'http://localhost:9090/files/b3.jpg',
        'BUSINESS', '131494520', 'chubbell@northeastern.edu',
        'Graduating and looking to sell all my stuff',
        'Under Review');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`
(
    `id`          int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`     int(10) NULL DEFAULT NULL COMMENT 'User ID',
    `goods_id`    int(10) NULL DEFAULT NULL COMMENT 'Product ID',
    `business_id` int(10) NULL DEFAULT NULL COMMENT 'Store ID',
    `num`         int(10) NULL DEFAULT NULL COMMENT 'Quantity',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Shopping Cart Table'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart`
VALUES (8, 5, 10, 2, 1);

-- ----------------------------
-- Table structure for collect
-- ----------------------------
DROP TABLE IF EXISTS `collect`;
CREATE TABLE `collect`
(
    `id`          int(10) NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`     int(10) NULL DEFAULT NULL COMMENT 'User ID',
    `goods_id`    int(10) NULL DEFAULT NULL COMMENT 'Product ID',
    `business_id` int(10) NULL DEFAULT NULL COMMENT 'Store ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Collection Information Table'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of collect
-- ----------------------------
INSERT INTO `collect`
VALUES (4, 5, 5, 2);
INSERT INTO `collect`
VALUES (5, 5, 6, 2);
INSERT INTO `collect`
VALUES (7, 9, 5, 2);
INSERT INTO `collect`
VALUES (8, 5, 10, 2);

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`
(
    `id`          int(10)                                                       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `user_id`     int(10)                                                       NULL DEFAULT NULL COMMENT 'User ID',
    `goods_id`    int(10)                                                       NULL DEFAULT NULL COMMENT 'Product ID',
    `business_id` int(10)                                                       NULL DEFAULT NULL COMMENT 'Store ID',
    `content`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Comment Content',
    `time`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Comment Time',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Comment Information Table'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment`
VALUES (1, 5, 5, 2, 'I like it!!!', '2024-11-10 17:10:30');
INSERT INTO `comment`
VALUES (2, 5, 5, 2, 'These goods are really awesome!!!', '2024-11-10 17:12:55');
INSERT INTO `comment`
VALUES (3, 9, 5, 2,
        'My friend said this seller is nice!',
        '2024-11-10 18:35:11');

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`
(
    `id`          int(10)                                                       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Product Name',
    `img`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Product Image',
    `description` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci     NULL COMMENT 'Product Description',
    `price`       double(10, 2)                                                 NULL DEFAULT NULL COMMENT 'Product Price',
    `unit`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Unit of Measurement',
    `count`       int(10)                                                       NULL DEFAULT 0 COMMENT 'Product Sales',
    `type_id`     int(10)                                                       NULL DEFAULT NULL COMMENT 'Category ID',
    `business_id` int(10)                                                       NULL DEFAULT NULL COMMENT 'Business ID',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 8
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Product Information Table'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods`
VALUES (5,
        'Bike with helmet and lights',
        'http://localhost:9090/files/1699257986682-1.png',
        '<p><br/></p><p><img src="http://localhost:9090/files/1699261664936-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699261669398-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699261673992-d3.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699261678840-d4.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699261682978-d5.png" style="max-width:100%;" contenteditable="false"/></p>',
        150.00, 'piece', 0, 5, 2);
INSERT INTO `goods`
VALUES (6, 'Breville 800ESXL Espresso Machine',
        'http://localhost:9090/files/1699428705672-1.png',
        '<p><img src="http://localhost:9090/files/1699428736624-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699428739895-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699428742752-d3.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699428745711-d4.png" style="max-width:100%;" contenteditable="false"/></p>',
        120.00, 'piece', 0, 7, 2);
INSERT INTO `goods`
VALUES (7, 'Home Plant.Healthy home plant! pot included',
        'http://localhost:9090/files/1699445257509-1.png',
        '<p><img src="http://localhost:9090/files/1699445276508-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445283145-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445286061-d3.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445289121-d4.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445291821-d5.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445294540-d6.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445297097-d7.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445299644-d8.png" style="max-width:100%;" contenteditable="false"/></p>',
        10.00, 'piece', 0, 16, 2);
INSERT INTO `goods`
VALUES (8, 'Classic Novels Book Bundle.10 classic novels, hardcover & paperback',
        'http://localhost:9090/files/1699952256595-1.png',
        '<p><img src="http://localhost:9090/files/1699952270468-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952292732-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952296929-d3.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952300817-d4.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952304309-d5.png" style="max-width:100%;" contenteditable="false"/></p>',
        30.00, 'piece', 0, 1, 2);
INSERT INTO `goods`
VALUES (9, 'Wooden Bar Cart[lightweight w/ wheels,24 x15.5 in trays,32 in height to handlebar]',
        'http://localhost:9090/files/1699952462164-1.png',
        '<p><img src="http://localhost:9090/files/1699952478199-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952482285-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952488092-d4.png" style="max-width:100%;" contenteditable="false"/></p>',
        30.00, 'piece', 0, 6, 2);
INSERT INTO `goods`
VALUES (10, 'Teletubies Costume[perfect condition worn once for halloween im a 5\'\'11 costume is for TEEN size]',
        'http://localhost:9090/files/1699952515679-z1.png',
        '<p><img src="http://localhost:9090/files/1699952526794-1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952530411-2.png" style="max-width:100%;" contenteditable="false"/></p>',
        40.00, 'piece', 0, 15, 2);

-- ----------------------------
-- Table structure for notice
-- ----------------------------
DROP TABLE IF EXISTS `notice`;
CREATE TABLE `notice`
(
    `id`      int(11)                                                       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `title`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Title',
    `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Content',
    `time`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Creation Time',
    `user`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Creator',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 4
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Notice Information Table'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of notice
-- ----------------------------
INSERT INTO `notice`
VALUES (1, 'Please ensure all items listed comply with our platform rules. No prohibited items such as counterfeit products or illegal substances are allowed.',
        'Please ensure all items listed comply with our platform rules. No prohibited items such as counterfeit products or illegal substances are allowed.', '2024-11-05', 'admin');
INSERT INTO `notice`
VALUES (2, 'Always meet in a public place on campus when exchanging items for safety reasons. ', 'Always meet in a public place on campus when exchanging items for safety reasons. ',
        '2024-11-05', 'admin');
INSERT INTO `notice`
VALUES (3, 'Please be cautious and report any suspicious behavior to the admin team immediately.',
        'Please be cautious and report any suspicious behavior to the admin team immediately.', '2024-11-05', 'admin');


-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`
(
    `id`          int(10)                                                       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `order_id`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Order ID',
    `goods_id`    int(10)                                                       NULL DEFAULT NULL COMMENT 'Product ID',
    `business_id` int(10)                                                       NULL DEFAULT NULL COMMENT 'Business ID',
    `num`         int(10)                                                       NULL DEFAULT NULL COMMENT 'Product Quantity',
    `user_id`     int(10)                                                       NULL DEFAULT NULL COMMENT 'User ID',
    `price`       double(10, 2)                                                 NULL DEFAULT NULL COMMENT 'Order Price',
    `address_id`  int(10)                                                       NULL DEFAULT NULL COMMENT 'Address ID',
    `status`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Order Status',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Order Information Table'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders`
VALUES (3, '20241109175131', 5, 2, 2, 5, 118.00, 1, 'Reviewed');
INSERT INTO `orders`
VALUES (4, '20241109175131', 6, 2, 2, 5, 318.00, 1, 'Completed');
INSERT INTO `orders`
VALUES (6, '20241109175321', 5, 2, 2, 5, 118.00, 1, 'Completed');
INSERT INTO `orders`
VALUES (7, '20241109175321', 6, 2, 1, 5, 159.00, 1, 'Completed');
INSERT INTO `orders`
VALUES (8, '20241109180904', 7, 2, 1, 9, 129.00, 6, 'Completed');

-- ----------------------------
-- Table structure for type
-- ----------------------------
DROP TABLE IF EXISTS `type`;
CREATE TABLE `type`
(
    `id`          int(10)                                                       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Category Name',
    `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Category Description',
    `img`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Category Icon',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'Product Category Table'
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of type
-- ----------------------------
INSERT INTO `type`
VALUES (1, 'Books/Audio-Visual', 'This is Books and Audio-Visual',
        'http://localhost:9090/files/books.png');
INSERT INTO `type`
VALUES (2, 'Rent/Household', 'This is Rent/Household',
        'http://localhost:9090/files/house.png');
INSERT INTO `type`
VALUES (3, 'Medicines/Health Products', 'This is Medicines / Health Products',
        'http://localhost:9090/files/medication.png');
INSERT INTO `type`
VALUES (4, 'Home Textiles/Decor/Flowers', 'This is Home Textiles / Decor / Flowers',
        'http://localhost:9090/files/deco.png');
INSERT INTO `type`
VALUES (5, 'Automobiles/Accessories/Bikes', 'This is Automobiles / Accessories / Bikes',
        'http://localhost:9090/files/car.png');
INSERT INTO `type`
VALUES (6, 'Furniture/Lighting/Bathroom', 'This is Furniture / Lighting / Bathroom',
        'http://localhost:9090/files/furniture.png');
INSERT INTO `type`
VALUES (7, 'Household Appliances/Daily Necessities', 'This is Household Appliances / Daily Necessities',
        'http://localhost:9090/files/application.png');
INSERT INTO `type`
VALUES (8, 'Fruits/Fresh Food', 'This is Fruits / Fresh Food', 'http://localhost:9090/files/fruit.png');
INSERT INTO `type`
VALUES (9, 'Snacks/Tea & Alcohol/Food', 'This is Snacks/Tea & Alcohol/Food',
        'http://localhost:9090/files/food.png');
INSERT INTO `type`
VALUES (10, 'Toys', 'This is Toys',
        'http://localhost:9090/files/toy.png');
INSERT INTO `type`
VALUES (11, 'Digital Devices/Accessories', 'This is Mobile Phones / Digital Devices / Accessories',
        'http://localhost:9090/files/iPhones.png');
INSERT INTO `type`
VALUES (12, 'Computers/Office Accessories', 'This is Computers / Office Electronics / Accessories',
        'http://localhost:9090/files/computers.png');
INSERT INTO `type`
VALUES (13, 'Jewelry/Skincare/Accessories', 'This is Jewelry / Skincare / Accessories',
        'http://localhost:9090/files/jewelry.png');
INSERT INTO `type`
VALUES (14, 'stationary/bags', 'This is stationary/bags',
        'http://localhost:9090/files/stationary.png');
INSERT INTO `type`
VALUES (15, 'Men\'s Clothing/Sports & Outdoors', 'This is Men\'s Clothing / Sports & Outdoors',
        'http://localhost:9090/files/men-clothing.png');
INSERT INTO `type`
VALUES (16, 'Stylish Women\'s Clothing', 'This is Stylish Women\'s Clothing / Underwear',
        'http://localhost:9090/files/women-clothing.png');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`       int(10)                                                       NOT NULL AUTO_INCREMENT COMMENT 'ID',
    `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Username',
    `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Password',
    `name`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'User Nickname',
    `avatar`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Avatar',
    `role`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Role Identifier',
    `phone`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Phone',
    `email`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT 'Email',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_unicode_ci COMMENT = 'User Information Table'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (5, 'alice', '123456', 'alice', 'http://localhost:9090/files/user1.png', 'USER',
        '9259259251', 'ali@northeastern.edu');
INSERT INTO `user`
VALUES (9, 'sam', '123456', 'sam', 'http://localhost:9090/files/user2.png', 'USER', '18866660000',
        'sam@northeastern.edu.com');

SET FOREIGN_KEY_CHECKS = 1;

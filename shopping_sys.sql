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
VALUES (1, 5, 'Jack Smith', 'No. 888, Pudong New Area, Shanghai', '1234567890');
INSERT INTO `address`
VALUES (4, 5, 'Alice Wang', 'No. 888, Hefei, Anhui Province', '1234567891');
INSERT INTO `address`
VALUES (5, 5, 'Wenzhi Li', '600 Wanping South Road, Xuhui District, Shanghai', '1234567892');
INSERT INTO `address`
VALUES (6, 9, 'Cezhi Zhou', '22335 Foothill Blvd, Hayward, California, USA', '1234567893');

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
VALUES (1, 'admin', '123456', 'Administrator', 'http://localhost:9090/files/1697438073596-avatar.png', 'ADMIN',
        '13677889922', 'admin@xm.com');

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
VALUES (2, 'meshe', '123456', 'ifashion', 'http://localhost:9090/files/1699023227734-avatar.png', 'BUSINESS',
        '18899990000', 'meshe@xm.com',
        'MsShe (Founded in 2010, defined as "European and American minimalist luxury style, dedicated to spreading the confident and elegant beauty of women.")',
        'Approved');
INSERT INTO `business`
VALUES (3, 'perfume', '123456', 'Perfume House', 'http://localhost:9090/files/1699025808382-1.jpg', 'BUSINESS',
        '18877776666', 'perfume@xm.com',
        'Founded in 2010, a large-scale perfume wholesaler under the Momo Group. Promotes a "convenient and affordable" lifestyle, improving consumer quality of life. As of July 2023, it has spread across 31 provinces, 220+ cities, with nearly 2000 stores.',
        'Under Review');
INSERT INTO `business`
VALUES (4, 'computer', '123456', 'Computer Accessories', 'http://localhost:9090/files/1699025860605-3.jpg',
        'BUSINESS', '18800007777', 'computer@xm.com',
        'Founded in 2010, a large wholesaler under Lulu Group. Promotes a "convenient and affordable" lifestyle, improving consumer quality of life. As of July 2023, it has spread across 31 provinces, 220+ cities, with nearly 2000 stores.',
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
VALUES (1, 5, 5, 2, 'The clothes are nice, will come again next time!!!', '2023-11-10 17:10:30');
INSERT INTO `comment`
VALUES (2, 5, 5, 2, 'These clothes are indeed great, really awesome!!!', '2023-11-10 17:12:55');
INSERT INTO `comment`
VALUES (3, 9, 5, 2,
        'My friend said this store is good, so I bought something too. Let us see how it goes, if it is good, I will come back again!',
        '2023-11-10 17:35:11');

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
        'Designer Slim Fit Short T-shirt for Women Summer New Round Neck Thin Short Sleeve White High Waist Tight Crop Top',
        'http://localhost:9090/files/1699257986682-1.png',
        '<p><br/></p><p><img src="http://localhost:9090/files/1699261664936-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699261669398-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699261673992-d3.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699261678840-d4.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699261682978-d5.png" style="max-width:100%;" contenteditable="false"/></p>',
        59.00, 'piece', 0, 16, 2);
INSERT INTO `goods`
VALUES (6, 'MsShe Plus Size New Elastic Waist Decor Faux Two Piece Button Down Shirt Hem',
        'http://localhost:9090/files/1699428705672-1.png',
        '<p><img src="http://localhost:9090/files/1699428736624-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699428739895-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699428742752-d3.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699428745711-d4.png" style="max-width:100%;" contenteditable="false"/></p>',
        159.00, 'piece', 0, 16, 2);
INSERT INTO `goods`
VALUES (7, 'Thickened MsShe Plus Size 2023 New Autumn Dopamine V-neck Loose Hoodie for Winter',
        'http://localhost:9090/files/1699445257509-1.png',
        '<p><img src="http://localhost:9090/files/1699445276508-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445283145-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445286061-d3.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445289121-d4.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445291821-d5.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445294540-d6.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445297097-d7.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699445299644-d8.png" style="max-width:100%;" contenteditable="false"/></p>',
        129.00, 'piece', 0, 16, 2);
INSERT INTO `goods`
VALUES (8, 'American Retro Short Sleeve T-shirt Women Pure Cotton Summer Ins Trend New Design V-neck',
        'http://localhost:9090/files/1699952256595-1.png',
        '<p><img src="http://localhost:9090/files/1699952270468-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952292732-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952296929-d3.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952300817-d4.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952304309-d5.png" style="max-width:100%;" contenteditable="false"/></p>',
        59.00, 'piece', 0, 16, 2);
INSERT INTO `goods`
VALUES (9, '[Gift Extended Strap] MsShe Plus Size Sports Bra Wireless Shockproof Push-up One-piece',
        'http://localhost:9090/files/1699952462164-1.png',
        '<p><img src="http://localhost:9090/files/1699952478199-d1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952482285-d2.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952488092-d4.png" style="max-width:100%;" contenteditable="false"/></p>',
        89.00, 'piece', 0, 16, 2);
INSERT INTO `goods`
VALUES (10, 'MsShe Plus Size Winter New Vintage Quilted Long Coat Adjustable Waist',
        'http://localhost:9090/files/1699952515679-z1.png',
        '<p><img src="http://localhost:9090/files/1699952526794-1.png" style="max-width:100%;" contenteditable="false"/><img src="http://localhost:9090/files/1699952530411-2.png" style="max-width:100%;" contenteditable="false"/></p>',
        199.00, 'piece', 0, 16, 2);

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
VALUES (1, 'The system is officially online today, internal testing begins',
        'The system is officially online today, internal testing begins', '2023-09-05', 'admin');
INSERT INTO `notice`
VALUES (2, 'All features are completed and available for use', 'All features are completed and available for use',
        '2023-09-05', 'admin');
INSERT INTO `notice`
VALUES (3, 'The weather is great today, a good time to go out and have fun',
        'The weather is great today, a good time to go out and have fun', '2023-09-05', 'admin');


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
VALUES (3, '20231109175131', 5, 2, 2, 5, 118.00, 1, 'Reviewed');
INSERT INTO `orders`
VALUES (4, '20231109175131', 6, 2, 2, 5, 318.00, 1, 'Completed');
INSERT INTO `orders`
VALUES (6, '20231109175321', 5, 2, 2, 5, 118.00, 1, 'Completed');
INSERT INTO `orders`
VALUES (7, '20231109175321', 6, 2, 1, 5, 159.00, 1, 'Completed');
INSERT INTO `orders`
VALUES (8, '20231109180904', 7, 2, 1, 9, 129.00, 6, 'Completed');

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
        'http://localhost:9090/files/1699015316548-图书音像.png');
INSERT INTO `type`
VALUES (2, 'Cookware/Storage/Pets', 'This is Cookware / Storage / Pets',
        'http://localhost:9090/files/1699015562438-居家.png');
INSERT INTO `type`
VALUES (3, 'Medicines/Health Products', 'This is Medicines / Health Products',
        'http://localhost:9090/files/1699015585629-医药保健.png');
INSERT INTO `type`
VALUES (4, 'Home Textiles/Decor/Flowers', 'This is Home Textiles / Decor / Flowers',
        'http://localhost:9090/files/1699015602362-家纺家饰.png');
INSERT INTO `type`
VALUES (5, 'Automobiles/Accessories/Supplies', 'This is Automobiles / Accessories / Supplies',
        'http://localhost:9090/files/1699015621841-汽车配件.png');
INSERT INTO `type`
VALUES (6, 'Furniture/Lighting/Bathroom', 'This is Furniture / Lighting / Bathroom',
        'http://localhost:9090/files/1699015637219-家具建材.png');
INSERT INTO `type`
VALUES (7, 'Household Appliances/Daily Necessities', 'This is Household Appliances / Daily Necessities',
        'http://localhost:9090/files/1699015658084-家用电器.png');
INSERT INTO `type`
VALUES (8, 'Fruits/Fresh Food', 'This is Fruits / Fresh Food', 'http://localhost:9090/files/1699015679164-喵鲜生.png');
INSERT INTO `type`
VALUES (9, 'Snacks/Tea & Alcohol/Food', 'This is Snacks/Tea & Alcohol/Food',
        'http://localhost:9090/files/1699015694567-食品.png');
INSERT INTO `type`
VALUES (10, 'Maternity & Baby/Toys', 'This is Maternity & Baby / Toys',
        'http://localhost:9090/files/1699015709389-母婴玩具.png');
INSERT INTO `type`
VALUES (11, 'Digital Devices/Accessories', 'This is Mobile Phones / Digital Devices / Accessories',
        'http://localhost:9090/files/1699015723293-数码手机.png');
INSERT INTO `type`
VALUES (12, 'Computers/Office Accessories', 'This is Computers / Office Electronics / Accessories',
        'http://localhost:9090/files/1699015735715-电脑.png');
INSERT INTO `type`
VALUES (13, 'Jewelry/Skincare/Accessories', 'This is Jewelry / Skincare / Accessories',
        'http://localhost:9090/files/1699015765623-珠宝饰品.png');
INSERT INTO `type`
VALUES (14, 'Women\'s/Men\'s Shoes/Bags', 'This is Women\'s Shoes / Men\'s Shoes / Bags',
        'http://localhost:9090/files/1699015794478-鞋_箱包.png');
INSERT INTO `type`
VALUES (15, 'Men\'s Clothing/Sports & Outdoors', 'This is Men\'s Clothing / Sports & Outdoors',
        'http://localhost:9090/files/1699015810103-男装.png');
INSERT INTO `type`
VALUES (16, 'Stylish Women\'s Clothing', 'This is Stylish Women\'s Clothing / Underwear',
        'http://localhost:9090/files/1699015824719-女装内衣.png');

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
VALUES (5, 'Alice Wang', '123456', 'Alice Wang', 'http://localhost:9090/files/user1.png', 'USER',
        '18811112222', 'zhangsan@xm.com');
INSERT INTO `user`
VALUES (9, 'Daniel Chan', '123456', 'Daniel Chan', 'http://localhost:9090/files/1699254328253-corgi.jpeg', 'USER', '18866660000',
        'lisi@xm.com');

SET FOREIGN_KEY_CHECKS = 1;

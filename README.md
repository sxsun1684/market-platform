# Project：Bunny Market

## Background
This project is a small-scale online second-hand trading platform for students at the Oakland campus of Northeastern University. It aims to facilitate the transfer of personal idle items within the campus. Students can either open and manage their own stores or act as consumers to purchase second-hand items from others.

## Implementation
The website is based on a multi-merchant structure, allowing different merchants to manage their own data independently. A collaborative filtering recommendation algorithm is integrated for personalized recommendations, enabling tailored suggestions for users. The recommendation process leverages asynchronous multi-threading to efficiently handle data from different users.

## Key Features by Role

### Administrator
- Login, Personal Dashboard, Change Password
- Verify student registration information; once approved, merchants can publish product information
- Manage admin information, merchant information, and user data
- Manage product categories and product information across the platform
- Manage orders across different merchants
- Manage reviews on products from different merchants
- Publish system-wide announcements

### Business: Student Seller
- Register, Login, Personal Dashboard, Change Password
- Publish product information
- Manage orders within their store
- Manage users and reviews within their store

### User: Student Buyer
- Register, Login, Personal Dashboard, Change Password
- Browse all products on the platform, including search and category-based browsing
- View recommended products, powered by a collaborative filtering algorithm
- Save items of interest to a wishlist
- View product details, reviews, add products to the cart, and place orders
- Leave reviews for purchased products after completing an order
- Maintain shipping address information
- View their orders and track the status of placed orders

## Requirements
- **Backend**: Java 17+, Spring Boot
- **Frontend**: Node.js 16+, Vue
- **Database**: MySQL, navicat
- **Tools**: Maven, npm or yarn

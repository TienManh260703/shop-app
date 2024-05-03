CREATE DATABASE SHOPAPP_1;
USE SHOPAPP_1;
-- Khách hàng muốn mua hàng => phải đắng ký tài khoản = > bằng users
CREATE TABLE users
(
    id INT PRIMARY KEY AUTO_INCREMENT, -- AUTO_INCREMENT tự tăng
    fullName NVARCHAR(100) DEFAULT '' ,
    phone_number VARCHAR(15) NOT NULL,-- đại diện đăng nhập bằng trường này
    dia_chi  VARCHAR(200) DEFAULT '',
    password VARCHAR(100) NOT NULL DEFAULT '' , -- mật khẩu mã hóa
    create_at DATETIME,
    update_at DATETIME,
    is_active TINYINT(1) DEFAULT 1 ,-- xóa mềm TINYINT(1) 1 chữ số 1->9
    date_of_birth DATE ,
    facebook_account_id INT DEFAULT 0,-- ID FACEBOOK
    google_account_id INT DEFAULT 0
);
ALTER TABLE users add COLUMN role_id int ;
GO 

CREATE TABLE roles(
    id int PRIMARY KEY ,
    name VARCHAR(20) NOT NULL
)
ALTER TABLE users ADD FOREIGN KEY (role_id) REFERENCES roles(id);
GO
CREATE TABLE tokens
(
    id int PRIMARY KEY AUTO_INCREMENT,
    token VARCHAR(255) UNIQUE NOT NULL,
    token_type VARCHAR(50) NOT NULL,
    expiration_data DATETIME , -- Ngày hết hạn của token
    revoked TINYINT(1) NOT NULL,
    expired TINYINT(1) NOT NULL,
    user_id int  ,-- Khóa ngoại
    FOREIGN KEY (user_id) REFERENCES users(id)
)
GO
--- Hỗ trợ đăng nhập bằng Facebook và Google
CREATE TABLE  social_account
(
    id int PRIMARY KEY AUTO_INCREMENT,
    provider VARCHAR(10) NOT NULL COMMENT 'Tên của nhà social network',
    provider_id VARCHAR(50) NOT NULL ,-- id của tài khoản mạng xã hội
    email VARCHAR(150) NOT NULL COMMENT 'Email tài khoản',
    name VARCHAR(100) NOT NULL COMMENT 'Tên người dùng',
    user_id int ,
    FOREIGN KEY (user_id) REFERENCES users(id)
)

-- Danh mục sản phẩm
CREATE TABLE categories
(
    id int PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL DEFAULT'' COMMENT 'Tên danh mục vd : Đồ điện tử',
)

-- Bảng sản phẩm (Product) 
CREATE TABLE products
(
     id int PRIMARY KEY AUTO_INCREMENT,
     name VARCHAR(350) COMMENT 'Tên sản phẩm',
     price FLOAT NOT NULL CHECK(price >=0) ,-- Giá
     thumbnail VARCHAR(300) DEFAULT '',-- Đường dẫn ảnh
     description LONGTEXT DEFAULT '', -- Mô tả
     create_at DATETIME ,
     update_at DATETIME, 
     category_id int ,
     FOREIGN KEY (category_id) REFERENCES categories(id)
)

-- Đặt hàng - orders
CREATE TABLE orders
(
     id int PRIMARY KEY AUTO_INCREMENT,
     user_id int ,
     fullName VARCHAR(100) DEFAULT '',
     email VARCHAR(100) DEFAULT '',
     phone_number VARCHAR(20)NOT NULL,
     address VARCHAR(200) NOT NULL,-- Địa chỉ nơi gửi
     note VARCHAR(250) DEFAULT '',
     orders_date DATETIME DEFAULT CURRENT_TIMESTAMP,
     status VARCHAR(20) ,
     total_money FLOAT CHECK (total_money >=0),
     FOREIGN KEY (user_id) REFERENCES users(id)
)

ALTER TABLE orders ADD COLUMN 'shipping_method' VARCHAR(100);
ALTER TABLE orders ADD COLUMN 'shipping_address' VARCHAR(200);--Địa chỉ ship đến
ALTER TABLE orders ADD COLUMN 'shipping_date' DATE;-- ngày nào gửi đến
ALTER TABLE orders ADD COLUMN 'tracking_number' VARCHAR(100);-- Số vận đơn
ALTER TABLE orders ADD COLUMN 'payment_method' VARCHAR(100);-- Phương thức thanh toán
-- Xóa mềm 
ALTER TABLE orders ADD COLUMN active TINYINT(1);
-- Trạng thái đơn hàng chỉ được phép nhận "một số  giá trị cụ thể"
ALTER TABLE orders
MODIFY COLUMN status ENUM('pending','processing', 'shipped', 'delivered', 'cancelled')
COMMENT 'Trạng thái đơn hàng';


--
CREATE TABLE order_details 
(
    id int PRIMARY KEY AUTO_INCREMENT,
    order_id int ,
    product_id int ,
    price FLOAT CHECK(price>=0),
    number_of_products INT CHECK(number_of_products>0), -- Số sản phẩm
    total_money Float CHECK(total_money>=0),
    color VARCHAR(20) DEFAULT '', -- Mã màu
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (provider_id) REFERENCES products(id)
)


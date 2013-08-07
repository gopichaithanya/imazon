-- Removes foreign keys checking to avoid problems when dropping tables. ------

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

-- Indexes for primary keys have been explicitly created.

-- ---------- Table for validation queries from the connection pool. ----------

DROP TABLE PingTable;
CREATE TABLE PingTable (foo CHAR(1));

-- ------------------------------ Book ----------------------------------------

DROP TABLE Book;

CREATE TABLE Book (
	bookId BIGINT NOT NULL AUTO_INCREMENT,
	title VARCHAR(64) NOT NULL,
	author VARCHAR(64) NOT NULL,
	categoryId BIGINT NOT NULL,
	publisherId BIGINT NOT NULL,
	publicationDate DATE DEFAULT CURRENT_DATE,
	price NUMERIC(17,2) NOT NULL,
	language VARCHAR(32) NOT NULL,
	city VARCHAR(64),
	country VARCHAR(64) NOT NULL,
	isbn VARCHAR(13),
	version BIGINT,
	CONSTRAINT Book_PK PRIMARY KEY (bookId),
	CONSTRAINT BookCategory_FK FOREIGN KEY (categoryId) REFERENCES Category(categoryId),
	CONSTRAINT BookUser_FK FOREIGN KEY (publisherId) REFERENCES User(userId),
	CONSTRAINT BookIsbn_UK UNIQUE (isbn)
) ENGINE = InnoDB;

CREATE INDEX BookIndexByBookId ON Book(bookId);

-- ------------------------------ Category ------------------------------------

DROP TABLE Category;

CREATE TABLE Category (
	categoryId BIGINT NOT NULL AUTO_INCREMENT,
	name VARCHAR(16) NOT NULL,
	CONSTRAINT Category_PK PRIMARY KEY (categoryId),
	CONSTRAINT CategoryName_UK UNIQUE (name)
) ENGINE = InnoDB;

CREATE INDEX CategoryIndexByCategoryId ON Category(categoryId);

-- ------------------------------ Order ---------------------------------------

DROP TABLE `Order`;

CREATE TABLE `Order` (
	orderId BIGINT NOT NULL AUTO_INCREMENT,
	orderDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	userId BIGINT NOT NULL,
	name VARCHAR(32) NOT NULL,
	surname VARCHAR(64) NOT NULL,
	address VARCHAR(128) NOT NULL,
	state VARCHAR(1) NOT NULL,
	stateMessage VARCHAR(128),
	voucher VARCHAR(64), -- Proof of payment
	version BIGINT,
	CONSTRAINT Order_PK PRIMARY KEY (orderId),
	CONSTRAINT OrderUser_FK FOREIGN KEY (userId) REFERENCES `User`(userId),
	CONSTRAINT OrderVoucher_UK UNIQUE (voucher)
) ENGINE = InnoDB;

CREATE INDEX OrderIndexByOrderId ON `Order`(orderId);

-- ------------------------------ OrderBook -----------------------------------

DROP TABLE OrderBook;

CREATE TABLE OrderBook (
	orderId BIGINT NOT NULL,
	bookId BIGINT NOT NULL,
	quantity INT NOT NULL,
	price NUMERIC(17,2) NOT NULL,
    version BIGINT,
	CONSTRAINT OrderBookOrder_FK FOREIGN KEY (orderId) REFERENCES `Order`(orderId),
	CONSTRAINT OrderBookBook_FK FOREIGN KEY (bookId) REFERENCES Book(bookId),
	CONSTRAINT OrderBook_PK PRIMARY KEY (orderId, bookId)
) ENGINE = InnoDB;

-- ------------------------------ SellerOrder ---------------------------------

DROP TABLE SellerOrder;

CREATE TABLE SellerOrder (
	sellerId BIGINT NOT NULL,
	orderId BIGINT NOT NULL,
	modificationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	state VARCHAR(1) NOT NULL,
	stateMessage VARCHAR(128),
    version BIGINT,
	CONSTRAINT SellerOrderSeller_FK FOREIGN KEY (sellerId) REFERENCES (userId),
	CONSTRAINT SellerOrderOrder_FK FOREIGN KEY (orderId) REFERENCES `Order`(orderId),
	CONSTRAINT SellerOrder_PK PRIMARY KEY (sellerId, orderId)
) ENGINE = InnoDB;

-- ------------------------------ User ----------------------------------------

DROP TABLE `User`;

CREATE TABLE `User` (
    userId BIGINT NOT NULL AUTO_INCREMENT,
	profile VARCHAR(1) NOT NULL,
	name VARCHAR(32) NOT NULL,
	surname VARCHAR(64) NOT NULL,
    login VARCHAR(32) COLLATE latin1_bin NOT NULL,
    `password` VARCHAR(32) NOT NULL,
    email VARCHAR(64) NOT NULL,
	birthDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
	phone VARCHAR(9) NOT NULL,
	mobile VARCHAR(9) NOT NULL,
	address VARCHAR(128) NOT NULL,
    version BIGINT,
    CONSTRAINT User_PK PRIMARY KEY (userId),
    CONSTRAINT UserLogin_UK UNIQUE (login)
) ENGINE = InnoDB;

CREATE INDEX UserIndexByUserId ON `User`(usrId);
CREATE INDEX UserIndexByLogin ON `User`(login);

-- Enables foreign keys checking newly. ---------------------------------------

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;




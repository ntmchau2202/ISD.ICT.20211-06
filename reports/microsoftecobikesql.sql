--
-- File generated with SQLiteStudio v3.2.1 on CN Thg2 20 19:00:18 2022
--
-- Text encoding used: UTF-8
--
-- PRAGMA foreign_keys = off;
BEGIN TRANSACTION;

-- Table: Administrator
-- DROP TABLE IF EXISTS Administrator;

CREATE TABLE Administrator (
    username VARCHAR (256) NOT NULL
                           PRIMARY KEY,
    pwd      VARCHAR (256) NOT NULL
);


-- Table: Bike
-- DROP TABLE IF EXISTS Bike;

CREATE TABLE Bike (
    name               VARCHAR (256) NOT NULL,
    bike_type          VARCHAR (16)  NOT NULL,
    license_plate_code VARCHAR (32)  NOT NULL,
    bike_image         VARCHAR (256),
    bike_barcode       VARCHAR (10)  PRIMARY KEY,
    currency_unit      VARCHAR (3)   NOT NULL,
    create_date        DATE,
    creator            VARCHAR (256),
    CONSTRAINT FK_Bike_Creator FOREIGN KEY (
        creator
    )
    REFERENCES Administrator (username) 
);

-- Table: Dock
-- DROP TABLE IF EXISTS Dock;

CREATE TABLE Dock (
    name         VARCHAR (256),
    dock_id      INTEGER       PRIMARY KEY IDENTITY,
    dock_address VARCHAR (256),
    dock_area    FLOAT,
    num_dock     INTEGER,
    dock_image   VARCHAR (256) 
);

-- Table: BikeInDock
-- DROP TABLE IF EXISTS BikeInDock;

CREATE TABLE BikeInDock (
    dock_id      INTEGER,
    bike_barcode VARCHAR (10) NOT NULL,
    CONSTRAINT PK_Bike_In_Dock PRIMARY KEY (
        dock_id,
        bike_barcode
    ),
    CONSTRAINT FK_BikeInDock_Dock FOREIGN KEY (
        dock_id
    )
    REFERENCES Dock (dock_id),
    CONSTRAINT FK_BikeInDock_Bike FOREIGN KEY (
        bike_barcode
    )
    REFERENCES Bike (bike_barcode) 
);


-- Table: BikeStatus
-- DROP TABLE IF EXISTS BikeStatus;

CREATE TABLE BikeStatus (
    bike_barcode    VARCHAR (10) NOT NULL
                                 PRIMARY KEY,
    current_status  VARCHAR (4),
    total_rent_time INTEGER,
    current_battery FLOAT,
    CONSTRAINT FK_BikeStatus_Barcode FOREIGN KEY (
        bike_barcode
    )
    REFERENCES Bike (bike_barcode),
    CONSTRAINT Check_BikeStatus_Total_Rent_Time CHECK (total_rent_time >= 0),
    CONSTRAINT Check_BikeStatus_Battery CHECK (current_battery >= 0) 
);


-- Table: Configs
-- DROP TABLE IF EXISTS Configs;

CREATE TABLE Configs (
    bike_type   VARCHAR (256) PRIMARY KEY,
    bike_price  FLOAT,
    saddles     INTEGER,
    pedals      INTEGER,
    motors      INTEGER,
    rear_seats  INTEGER,
    rent_factor FLOAT
);





-- Table: EcoBikeTransaction
-- DROP TABLE IF EXISTS EcoBikeTransaction;

CREATE TABLE EcoBikeTransaction (
    transaction_id     INTEGER       NOT NULL
                                     PRIMARY KEY IDENTITY,
    transaction_amount FLOAT         NOT NULL,
    transaction_time   VARCHAR (256) NOT NULL,
    transaction_detail VARCHAR (256),
    creditcard_number  VARCHAR (25)  NOT NULL,
    rent_id            INTEGER
);

-- Table: RentBike
-- DROP TABLE IF EXISTS RentBike;

CREATE TABLE RentBike (
    rent_id      INTEGER       NOT NULL
                               PRIMARY KEY IDENTITY,
    bike_barcode VARCHAR (10)  NOT NULL,
    start_time   VARCHAR (256) NOT NULL,
    end_time     VARCHAR (256),
    rent_period  INTEGER,
    CONSTRAINT FK_RentBike_Bike FOREIGN KEY (
        bike_barcode
    )
    REFERENCES Bike (bike_barcode),
);


-- Table: Invoice
-- DROP TABLE IF EXISTS Invoice;

CREATE TABLE Invoice (
    invoice_id     INTEGER       NOT NULL,
    transaction_id INTEGER       NOT NULL,
    rent_id        INTEGER       NOT NULL,
    date_issued    VARCHAR (256),
	CONSTRAINT PK_Rent_Trans_ID PRIMARY KEY(transaction_id, rent_id),
    CONSTRAINT FK_Invoice_Transaction FOREIGN KEY (transaction_id) REFERENCES EcoBikeTransaction(transaction_id),
    CONSTRAINT FK_Invoice_Customer FOREIGN KEY (rent_id) REFERENCES RentBike(rent_id),
);





COMMIT TRANSACTION;
-- PRAGMA foreign_keys = on;

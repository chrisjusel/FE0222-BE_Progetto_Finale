# Energy Service
[![it](https://img.shields.io/badge/lang-it-red.svg)](https://github.com/chrisjusel/FE0222-BE_Progetto_Finale/blob/main/README.md)

This educational project is aimed at managing the data of a CRM that manages customers and invoices.

# Present content.
- [Overview](#Overview)
- [Technologies used](#Technologies-used)
- [Features] (#Features)
- [Examples of API use](#Examples-of-API-use)
- [Additional resources](#Additional-resources)

## Overview
This application was built with Spring and exposes REST methods that allow the main data read and write operations.
- There is the persistence layer on Database and the JPA framework is used, specifically through the Hybernate ORM,
to abstract the business logic written in Java from the operations to be performed on whatever underlying DBMS is desired.
- All the methods that the API exposes are documented with Open-Api and Swagger.
- There is also an authentication system through JWT tokens.
- An exception handling system was adopted within the project, and the code was tested with the JUnit testing framework.
- Each time the application is started, by default, all test data are reloaded and go to populate the database by reading from csv files. 
This behavior can be changed in the application.properties.
- Paging has been implemented for read operations.
- The application has been released on Heroku.

## Technologies used
- Spring MVC
- Spring Boot
- Spring Data
- Spring Security
- JWT
- Swagger and Open-Api
- JUnit (testing)

## Features.
### Provinces
- Entering a province
- Editing a province
- Retrieving a province via id
- Retrieving all provinces

### Municipalities
- Entering a municipality
- Editing a municipality
- Removing a commune (if not associated with any address)
- Retrieving a municipality via id
- Retrieving all municipalities

### Customers
- Entering a customer
- Editing a customer (cascading its address as well)
- Removing a customer (cascading all of its invoices)

##### Recovery operations and sorting
- Retrieval of a customer by id
- Recovery of all customers
- Retrieval of customers between a range of annual turnover
- Retrieval of customers between a range of input dates
- Retrieval of customers between a range of last contact dates
- Retrieval of customers by their business name or a substring thereof
- Sorting of customers by their annual turnover
- Sorting of customers alphabetically by business name
- Sorting of customers by date of entry
- Sorting of customers by date of last contact
- Sorting of customers alphabetically by province of registered office

### Addresses
- Editing an address
- Removing an address (if not associated with any customer)
- Retrieving an address via id
- Retrieving all addresses

### Invoice states
- Entering an invoice status
- Editing an invoice status
- Removing an invoice status (if not linked to any invoice)
- Retrieving an invoice status via the id
- Retrieving all invoice statuses

### Invoices
- Entering a new invoice
- Editing a new invoice
- Removing an invoice

##### Recovery operations
- Recovery of an invoice through id
- Recovery of all invoices
- Recovery of a customer's invoices
- Recovery of invoices through the status
- Recovery of invoices across the year
- Recovery of invoices across a range of amounts
- Recovery of invoices through a date

### Authentication
- Registering a new user (with roles)
- Login

## API Usage-Examples

### POST methods
#### Common

- **Inserimento di un nuovo comune**

```
{
	"name": "Fiumicino",
	"province": {
		"name": "Roma"
	}
}
```

- **Response**

```
{
    "id": {calculated},
    "name": "Fiumicino",
    "province": {
        "id": {calculated},
        "name": "Roma",
        "sign": "RM"
    }
}
```

#### Invoice
- **Entering a new invoice**

```
{
    "date": "2019-07-31 16:29:00"
    "number": 489576,
    }, "year": "2019",
    "amount": "900.16",
    "state": "UNPAID",
    "customer": 1
}
```

- **Response**

```
{
    "id": {calculated},
    "year": 2019,
    "date": "2019-07-31 16:29:00",
    "amount": 900.16,
    "number": 489576,
    "customer": {
        "id": 1
    },
    "state": {
        "id": {calculated},
        "name": "NON PAGATA"
    }
}
```

### PUT methods
#### Invoice status
- **Editing an invoice status**
  - /api/billingstates/{invoice-id}

```
{
    "name": "In Attesa"
}
```

- **Response**

```
{
    "id": {id-fattura},
    "name": "In Attesa"
}
```

### GET methods
#### Client

- **Fetching a customer through id**
  - /api/customers/{id-cliente}

- **Response**

```
{
    "id": 6,
    "companyName": "Flashspan",
    "vatNumber": "90-018-5605",
    "email": "bjanko5@yellowbook.com",
    "insertionDate": "2021-10-06 09:15:18",
    "lastContactDate": "2021-10-27 12:40:54",
    "annualTurnover": 205620.78,
    "pec": "bjanko5@a8.net",
    "phone": "+963 685 697 6956",
    "contactEmail": "bjanko5@sourceforge.net",
    "contactName": "Bee",
    "contactSurname": "Janko",
    "contactPhone": "+7 795 964 8280",
    "operatingSiteAddress": {
        "id": 20,
        "street": "Myrtle",
        "civicNumber": "31",
        "locality": "87331",
        "zip": "Syria",
        "common": {
            "id": 1918,
            "name": "Bellano",
            "province": {
                "id": 40,
                "name": "Lecco",
                "sign": "LC"
            }
        }
    },
    "legalSiteAddress": {
        "id": 19,
        "street": "Talisman",
        "civicNumber": "019",
        "locality": "Russia",
        "zip": "88393",
        "common": {
            "id": 4074,
            "name": "Mirabello Sannitico",
            "province": {
                "id": 21,
                "name": "Campobasso",
                "sign": "CB"
            }
        }
    },
    "customerType": "SPA"
}
```

#### Invoices
- **Receiving a customer's invoices**
  - /api/billings/customer/{customer-id}

- **Response**  

```
{
    "content": [
        {
            "id": 178,
            "year": 2021,
            "date": "2022-04-02 02:03:54",
            "amount": 17132.07,
            "number": 2502,
            "customer": {
                "id": 8
            },
            "state": {
                "id": 5,
                "name": "PAGATA"
            }
        },
        {
            "id": 194,
            "year": 2021,
            "date": "2022-09-11 06:31:32",
            "amount": 43840.67,
            "number": 1965,
            "customer": {
                "id": 8
            },
            "state": {
                "id": 7,
                "name": "IN ATTESA"
            }
        }
    ],
    "pageable": "INSTANCE",
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 2,
    "number": 0,
    "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```

### DELETE methods
#### Client
- **Removing a customer**
  - /api/customers/1
- **Response**
  - Http Status: 200

## Additional Resources
- The Postman export file is provided, already containing the correct configuration of the methods so that they can be tested
- Swagger Documentation URL: {domain-name}/swagger-ui.html
  - Please note, to use the swagger methods you must authenticate with the token, which can be obtained through the login method with the following prompt:

  ```
  {
  	"username": "admin",
  	"password": "admin"
  }
  ```
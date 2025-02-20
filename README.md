# Encryption Service

This repository provides a solution to the encryption challenge, ensuring that sensitive fields in entity objects are encrypted before being stored in the database and decrypted when retrieved.

## Challenge

The challenge description and requirements can be found [here](https://github.com/backend-br/desafios/blob/master/cryptography/PROBLEM.md).

## Solution

To solve this challenge, a transparent encryption service was implemented, handling encryption and decryption seamlessly within the application. The `EncryptionService` class provides methods for encrypting and decrypting sensitive data using AES encryption.

The encryption process takes place in runtime, ensuring that the `userDocument` and `creditCardToken` fields are securely stored in the database while remaining accessible for authorized operations.

## Endpoints

The following REST endpoints were implemented to support the CRUD operations:

### Create a new record
**Endpoint:** `POST /users`
- **Description:** Encrypts and stores a new record in the database.
- **Request Body:**
  ```json
  {
    "userDocument": "123456789",
    "creditCardToken": "abc123xyz",
    "value": 5999
  }
  ```
- **Response:**
  ```json
  {
  	"id": 2,
  	"userDocument": "+F+Um621XqDBRqFZ9XhAKg==",
  	"creditCardToken": "0ih7cRg6crKgAgnnMoqi0Q==",
  	"value": 5999
  }
  ```

### Get all records
**Endpoint:** `GET /users`
- **Description:** Retrieves all encrypted records from the database.
- **Response:**
  ```json
  [
  	{
  		"id": 1,
  		"userDocument": "4rtpn+RqwqU5kHKb5ddUiw==",
  		"creditCardToken": "cMr/wAKEsGSue+IZ9x9iWw==",
  		"value": 100
  	},
  	{
  		"id": 2,
  		"userDocument": "+F+Um621XqDBRqFZ9XhAKg==",
  		"creditCardToken": "0ih7cRg6crKgAgnnMoqi0Q==",
  		"value": 5999
  	}
  ]
  ```

### Get a record by ID
**Endpoint:** `GET /users/2`
- **Description:** Retrieves an encrypted record by its ID.
- **Response:**
  ```json
  {
  	"id": 2,
  	"userDocument": "123456789",
  	"creditCardToken": "abc123xyz",
  	"value": 5999
  }
  ```

## Technologies Used
- Java
- Spring Boot
- AES Encryption
- REST API

For more details, refer to the [challenge description](https://github.com/backend-br/desafios/blob/master/cryptography/PROBLEM.md) or explore the implementation in this repository.


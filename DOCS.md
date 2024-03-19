# Endpoints Documentation

All resources are located at `/api/v1`.

## Users 

### POST `/users`
Create a user, the body should be structured this way:
```json
{
	"name": "Ian Gabriel",
	"phoneNumber": "(99) 10100-0000",
	"address": {
		"cep": "59000-000",
		"street": "Rua tal",
		"neighborhood": "Centro",
		"city": "Natal",
		"state": "RN",
		"number": 10
	}
}
```

### PUT `/users`
Update a user, the body is the same as POST, but with a id field as well (it should be a number)

### GET `/users`
List all users

### GET `/users/{id}`
Get an user with a specific id

### DELETE `/users/{id}`
Delete a specific user

## Products

### POST `/products`
Create a product. The body should be structured this way:
```json
{
	"name": "Teste",
	"value": 2.0,
	"description": "Esse produto é só um teste"
}
```

### PUT `/products`
Update a product. Same as POST but with an id field on the body (it should be a number)

### GET `/products`
List all products.

### GET `/products/{id}`
Get a specific product.

### DELETE `/products/{id}`
Delete a specific product.

## Purchases
### POST `/purchases`
Create a purchase. The body should be structured this way:
```json
{
    "userId": 1,
    "productIds": [list of product ids],
    "paymentMethod": "Credit card"
}
```

### GET `/purchases/byUser/{userId}`
Get all purchases from certain user.

### GET `/purchases/{id}`
Get a specific purchase.

### DELETE `/purchases/{id}`
Delete a specific purchase.

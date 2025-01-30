# Restful API Specification

### Auth-Service
Path: ``/auth/register`` <br/>
Method: ``POST`` <br/>
Payload:
```json
{
  "fullname": "name of user",
  "email": "email of user",
  "pin": "pin of user",
  "address": "address of user",
  "phone_number": "phone_number of user",
  "nik": "nik of user",
  "mother_name": "mother_name of user",
  "saldo": "initial saldo of user"
}
```

### User-Service
Path: ``/users/create`` <br/>
Method: ``POST`` <br/>
Payload:
```json
{
  "fullname": "name of user",
  "email": "email of user",
  "pin": "pin of user",
  "address": "address of user",
  "phone_number": "phone_number of user",
  "nik": "nik of user",
  "mother_name": "mother_name of user",
  "saldo": "initial saldo of user"
}
```


### Account-Service
Path: ``/account/create-accoun`` <br/>
Method: ``POST`` <br/>
Payload:
```json
{
  "user_id": "user_id of user",
  "saldo": "initial saldo of user"
}
```
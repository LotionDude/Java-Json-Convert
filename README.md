# Java-Json-Convert (WORK IN PROGRESS)

Library made to convert a request type object into a response type object

Currently written terribly with no tests or validations.

## Usage

Create a schema for the `request`, a schema for the `response` and load it by creating a new `JsonNodeConverter`:

```java
JsonNode request = objectMapper.readTree(new File("./src/main/resources/request.json"));
JsonNode response = objectMapper.readTree(new File("./src/main/resources/response.json"));

JsonNodeConverter jsonNodeConverter = new JsonNodeConverter(request, response, objectMapper);
```

Convert a target object into the response object by using the `jsonNodeConverter.convert(target)` method:

```java
JsonNode target = objectMapper.readTree(new File("./src/main/resources/target.json"));

Lotion lotion = jsonNodeConverter.convert(target, Lotion.class);
```

## Defining schemas

### Request schema

```json
{
  "name": "name",
  "range": {
    "lt": "lt",
    "gt": "gt"
  }
}
```

### Response schema

```json
{
  "user": {
    "name": "name",
    "range": {
      "lt": "lt",
      "gt": "gt"
    }
  }
}
```

#### How it works

The target object that you insert into `jsonNodeConverter.convert(target)` should match the json defined in the request
schema.
The text value defined in the request schema is an alias which will be mapped to the alias value defined in the response
schema.

For example, using our previously defined schema, for the following target object:

```json
{
  "name": "lotan",
  "range": {
    "lt": 3,
    "gt": 1
  }
}
```

Will get converted into the following response object:

```json
{
  "user": {
    "name": "lotan",
    "range": {
      "lt": 3,
      "gt": 1
    }
  }
}
```

### Defining array schema's

Here is how you define an array property in both schemas

##### Request

```json
{
  "match --ref='match_arr'": [
    {
      "match": {
        "valid": "valid",
        "false": "false"
      }
    }
  ]
}
```

##### Response

```json
{
  "match --ref='match_arr'": [
    {
      "valid": "valid",
      "false": "false"
    }
  ]
}
```

In this case, the `--ref='match_arr'` flag gives the array the alias `match_arr`.
Giving the same alias in the request and response lets the library know to map between them.

### Flags

You can define custom flags for your schema properties. Currently work in progress
There are two ways to define flags

- `--FLAG='VALUE'` - The name of the flag followed by the value inside single quote marks. Can include spaces
  inside the value.
- `--FLAG=VALUE` - The name of the flag followed with its value. The value cannot contain spaces.

#### Type flags

Throws and error if the target object does not match the type of the request object.
(currently non-functional on response schema)

- `--type=int`: Integer
- `--type=dbl`: Double
- `--type=num`: Number
- `--type=str`: String
- `--type=val`: Value (Integer/Double/Number/String)
- `--type=obj`: Object
- `--type=arr`: Array

#### Ref flag

Used to override aliases on requests and response schemas. Mostly necessary on array types
where aliases can't be defined.

- `--ref=$ALIAS` - Replace `$ALIAS` with your desired alias.


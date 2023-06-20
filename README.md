# Simply Asset Finance Coding Test

Welcome to the coding test for Simply Asset Finance. Please read the description below to find out more about the format
of the test, and the project that you'll be working with.

When you are ready to begin, make a fork of this repository (https://github.com/SimplyFinance/asset-recommender), make
any changes to the code as instructed and open a pull request pointing to `master` branch once you have completed the
test. Please include your name in the title of the PR.

Feel free to change anything you see fit about the project.

This test is expected to take 1 hour.

# Asset Recommendation System

This project is a system that holds information about assets, and provides a couple of endpoints to retrieve data about
them.

An asset is generally a piece of machinery or a vehicle.

It is a Spring Boot (2.5) project, built using Maven (3.8.2), with an embedded H2 database that contains a few sample
assets.

## DB Structure

The database contains a single table, which has four fields:

```mermaid
erDiagram
    asset {
        INT id PK
        VARCHAR name
        DECIMAL cost
        VARCHAR type "Type of the asset - 'AGRICULTURE', 'CONSTRUCTION', 'TRANSPORT', 'WASTE'"
    }
```

## Maven

In order to build Spring Boot application, navigate to the base directory of the project (`asset-recommender`) and run

`mvn clean install`

This will create a file called `asset-recommender.jar` in the `target` directory. This file can be run as usual, simply
by running

`java -jar target/asset-recommender.jar`

## Task

The task is to implement two endpoints, which have definitions in the `AssetController`.

### 1.

`GET /api/asset?maxCost={maxCost}` should return a list of all assets that have a `cost` less than or equal to
the `maxCost` query param, ordered by `name` ascending. The endpoint should return a 404 if no assets are found.

For example, if there are 3 assets in the database:

| id  | name    | cost      | type        |
|-----|---------|-----------|-------------|
| 1   | Tractor | 100,000   | AGRICULTURE |
| 2   | Ferry   | 1,500,000 | TRANSPORT   |
| 3   | Taxi    | 30,000    | TRANSPORT   |

then the expected response for request `/api/asset?maxCost=100000` would be:

```json
[
  {
    "id": 3,
    "name": "Taxi",
    "cost": 30000,
    "type": "TRANSPORT"
  },
  {
    "id": 1,
    "name": "Tractor",
    "cost": 100000,
    "type": "AGRICULTURE"
  }
  ...
]
```

### 2.

`GET /api/asset/sum-for-types` should return a map, with `Asset.Type` as keys, and the sum of costs of all assets of
that type as value.  All types should be included in the response.

For example, with the same data as the previous example, the expected response for request `/api/asset/sum-for-types`
would be:

```json
{
  "AGRICULTURE": 100000,
  "CONSTRUCTION": 0,
  "TRANSPORT": 1530000,
  "WASTE": 0
}
```

## JUnit Tests

There currently exists one test class in the project, `AssetControllerIT`, which contains a few simple tests to verify
that endpoints are working as expected.  As the endpoints are not implemented yet, the tests will fail.

These are written using JUnit version 5.7.

Please feel free to make any changes to the tests that you wish, or add additional ones.
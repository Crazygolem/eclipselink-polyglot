# Polyglot Persistence with EclipseLink

Toy polyglot implementation of a one-to-many relation accross relational (MySQL) and NoSQL (MongoDB) persistence units by combining them in a composite persistence unit.

## Structure

The Gradle (thus Maven) conventions are followed for the directories hierarchy.

To simplify the generation of a separate jar for the composite member persistence units (separate jars seems to be a requirement for EclipseLink), the corresponding classes and files are located in an `entities` module (in the IntelliJ sense).

For now, the `master` branch contains a relational-only mapping. The goal is to replicate the structure, including the relations, in a polyglot environment. The `poly` branch contains the port of `master` to a polyglot environment.

## Running the example

1. Create a relational database and its schema according to the entities you have currently checked out (automatic `ddl-generation` seems to be broken in EclipseLink for composite persistence units)
2. Edit the `persistence.xml` file of the composite member PUs (`entities` module): the the databases url, user and password.
3. In the root project, issue the following gradle command: `gradle run`

## Dependencies

* The gradle scripts have been created using Gradle 2.2.1.
* You should have running instances of MySQL and MongoDB.
* Other dependencies are managed through gradle and automatically pulled when running the `run` task.

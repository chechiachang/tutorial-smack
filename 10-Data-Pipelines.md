Data Pipelines
===

# Strategy and principles

* Asynchronous Message Passing

# Spark and Cassandra

Spark-Cassandra connector:
* Expose Cassandra tables as Spark RDDs
* Write Spark RDDs to Cassandra
* Execute CQL queries in Spark applications

* Maps table rows to CassandraRow objects or tuples
* Maps rows to objects of user-defined classes
* saveToCassandra())
* joinWithCassandraTable()
* repartitionByCassandraReplica()
* Converts data types between Cassandra and Scala
* Supports all the Cassandra data types, including collections
* Filters rows on server side via the CQL WHERE clause
* Allows the for execution on arbitrary CQL statements
* Plays with Cassandra virtual nodes
* Works with PySpark DataFrames

# SMACK Example

### Demo
https://www.slideshare.net/akirillov/data-processing-platforms-architectures-with-spark-mesos-akka-cassandra-and-kafka

### Spark Cassandra Connector
https://github.com/datastax/spark-cassandra-connector/blob/master/doc/0_quick_start.md

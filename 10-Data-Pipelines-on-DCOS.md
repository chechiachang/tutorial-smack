Data Pipelines
===
# IOT pipline
https://dcos.io/docs/1.9/usage/tutorials/iot_pipeline/

##### DCOS Cassandra service
https://github.com/mesosphere/dcos-cassandra-service

# Runs on local machine

##### install packages
```
dcos auth login

dcos package install --options=cassandra-minimal.json cassandra

dcos package install --options=kafka-minimal.json kafka

dcos package install spark

#dcos package install marathon-lb
```

##### inspect task

```
dcos task

dcos task cassandra --json
dcos task kafka --json

dcos kafka connection
```

##### connect to node

Connect to DC/OS Cluster
```
dcos node ssh --master-proxy --leader --user=vagrant
```
Running `ssh -A -t vagrant@192.168.65.90 ssh -A -t vagrant@192.168.65.90 `
Last login: Wed Apr  5 22:37:59 2017 from 192.168.65.1

```
docker run -it cassandra cqlsh node-0.cassandra.mesos
```

##### Connect to cassandra node
```
dcos node ssh --master-proxy --mesos-id=$(dcos task cassandra --json | jq -r '.[] | .slave_id') --name=vagrant
```
##### Tweeter

```
dcos marathon app add tweeter.json
```


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

##### Another Demo
https://www.slideshare.net/akirillov/data-processing-platforms-architectures-with-spark-mesos-akka-cassandra-and-kafka

##### Spark Cassandra Connector
https://github.com/datastax/spark-cassandra-connector/blob/master/doc/0_quick_start.md

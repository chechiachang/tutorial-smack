Kafka
===

# Introduction

* Deliver real-time data to multiple types of receivers
* Integration between data producer and data consumer
* Message publishing: to connect heterogeneous applications through message broker
* Provides seamless integration
* Not block producer
* Not let the producers know the final consumer

### Capabilities:

0. Publish and describe to streams of records
* Store streams of records in a fault tolerant way
* Process streams of records as they occurs
* Build real-time streaming data pipeline get data between systems or applications
* Build real-time streaming applications that transform or react to the streams of data

### Concept

* Kafka is run as a cluster
* Cluster stores streams of records called topic
* Each record consists of a key, a value, and a timestamp

### Core APIs

* Producer API: allow an app to publish a stream of records to Kafka topic(s)
* Consumer API: allow an app to subscribe to topic(s) and process stream of records
* Streams API: allow an app to act as a stream processor, consuming an input stream and producing an output stream
* Connector API: allow an app to build and run reusable producers or consumers that connect applications or data systems

# Topic and Log

* Topic is a category to which records are published
* Topics have 0 to multiple subscribers
* Maintains a partitioned log for each topic
* Partition is ordered, immutable sequence of records, a structured commit log
* Records in the partitions are each assigned a offset number as unique id
* Retention period: a record available period after published, after which the record is discarded
* Constant performance with respect to data size
* Retain offset(position of that consumer) in the log. Offset is controlled by consumer
* Consumers are cheap, come and go without affect the cluster
* Partition:
 * Allow log scaling. Topic may has many partition for each partition fit in different servers.
 * Act as the unit of parallelism

### Distribution

* Partitions are distributed over the servers
* Server handles data and request for a share of partition
* Partition is replicated for fault tolerance
* Partition has a leader server and 0 to many followers
* Leader handle all read and write request
* Elect new leader if the current leader failed
* Each server acts as a leaders for some partition so load-balanced

### Producer

* Choose record to assign to which partition within the topic


### Consumer

* Consumers label themselves with a consumer group
* Each record published to a topic is delivered to a consumer for each consumer group
* Less group for load balancing, more group for more broadcast


### Guarantees

* Message to a partition with append in order
* Consumer sees records in order as they stored in log
* For a topic with replication factor N, will tolerate up to N -1 server failures

### Queuing and Publish-subscribe
* Read queue from server
 * Scaling processing
 * Not multi-subscribers, read and gone
* Server publish to all consumers
 * Allow broadcast to multiple subscribers
 * Bad scaling if broadcast to every consumer
* Kafka consumer group
 * As a queue, divide up processing to a group of processes. For scaling.
 * As publish, broadcast to group


 Order
 * Within a topic, Kafka assign partition to consumers in consumer group so each partition is consumed by exactly one consumer in the group.
 * Ensure the consumer is the only reader of the partition and consumes data in order

### Storage System

* Fault tolerance
* Allow producer to wait on acknowledgement until fully replicated to guarantee persistence
* Disk structure scale well. Perform the same from 50KB to 50 TB
* Allow client to control read position

### Streaming System
Real-time processing of streams
* Streaming API


# ZooKeeper

Simple
* Distributed coordination service for distributed applications
* Allow process to coordinate through shared hierarchal namespace
 * Znodes: Data registers, similar to file and directories
* Keep date in memory

Replicated
* Ensemble: Zookeeper intend to be replicated over a set of hosts called an ensemble
* Servers must all know each other

Ordered
* Keeps stamp for each update with a order number of all ZooKeeper transactions

Fast
* Good for Read-dominant workload, at ratio of about 10:1

Leader Elect algorithm

### Start ZooKeeper

```
bin/zkServer.sh start conf/zoo.cfg


bin/zkCli.sh -server 127.0.0.1:2181

# create a zNode
create /zk_test my_data

get /zk_test

set /zk_test junk

delete /zk_test
```

# Use Kafka

##### Start ZooKeeper
```
bin/zookeeper-server-start.sh config/zookeeper.properties
```
port=2181

##### Start a broker
```
bin/kafka-server-start.sh config/server.properties
```
port=9092

##### Create a topic
```
bin/kafka-topic.sh --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic amazing

bin/kafka-topic.sh --list --zookeeper localhost:2181
```
##### Start a producer
```
bin/kafka-console-producer.sh --broker-list localhost:9092 --topic amazing
```
enter some message [enter]
##### Start a consumer
```
bin/kafka-console-consumer.sh --zookeeper localhost:2181 --topic amazing --from-beginning
```
get message fromt producer

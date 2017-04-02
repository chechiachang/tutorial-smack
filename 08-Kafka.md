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

* Topic is a category to which records are publishded
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

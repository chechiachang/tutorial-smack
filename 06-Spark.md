Spark
===

* infrastructure software and data science laboratory

Modules:
* core
* SQL
* MLIB
* Streaming
* GraphX

# Tutorial

```
$ /bin/run-example SparkPi 10

$ /bin/run-example SparkPi 1000
#took 71s

$ /bin/spark-shell
```

# Spark Architecture

* Resilient Distributed Dataset(RDD): a parallelized computational abstraction of a collection
* SparkContext: represents connection between cluster and nodes
* Cluster manager

0. Spark driver program distributes classes into the cluster
* Cluster manager starts the executers, one on each node, and assign them a tasks set
* Driver programs access the Spark core through the SparkContext

### SparkContext Metadata

* appName: ex. Spark Shell
* getConf
* getExecutorMemorystatus
* isLocal
* isStopped
* master
* sparkUser
* startTime
* version

### SparkContext methods

# Working with RDDs

### Spark desing goals
* In-memory data storage
* Fault tolerant: cluster operations and linear operations on small data chucks
* Efficiency: operation parallelization between cluster parts
* Fast: minimizing data replication between cluster members

### RDD operations
* Transformations: returns a new RDD created after applied to a RDD. ex set operations(union, intersection, and join)
* Actions: apply action won't change the origin RDD. ex. count, collect, and first.

### Rules for RDD
* Immutability: a new RDD is created and the origin RDD is not modified
* Resilient: the chain of Transformations is always logged. The process can be reproduced again
* Lazy evaluation: not executed until required
* Process aware:
* Memory storage: RDD are created, used, destroyed within memory

### DataFrames API
* Scalability: kb data to terabytes data
* Optimization: SQL optimization SQL beautification
* Integration: integration with other Spark member(Core, SQL, Streaming, MLlib, GraphX)
* Multi-format: support multiple data formats and storage system

### RDD operations

Transformations return RDDs; actions don’t.

Transformations:
* Lazy evaluation
* Element-wise
* immutable
* Lineage graph

Actions:
* always return a value
* trigger evaluation of all previous transformations

### RDD Persistence(Caching)

# Spark in single machine

* Driver(SparkContext) and executors(tasks) run on different Java processes
* Spark-Shell -> a driver program -> create a SparkContext
* Driver splitting user program into tasks
* Driver generates Directed Acyclic Graph(DAG) by tasks' assigning to which nodes
* Each executor is a standalone process that runs tasks and store RDDs
* Executor runs assigned tasks and return results to the driver
* Executor runs a Block Manager, managing in-memory storage for RDDs

# Spark in Cluster Mode

* Cluster Manager:
* Master and Slaves

# Program Execution

* spark-submit
* Spark calls the user program's main()
* Driver connect to the cluster manager to request resources to launch the executors
* Cluster manager launches executors in each slave node
* Driver analyzes, divides, distributes tasks to executors
* Executors run tasks and store results
* User program ends with exit(), or SparkContext stop()
* Driver ends the executors and free cluster manager's resources

# Application Deployment

```
bin/spark-submit --master spark://skynet:7077 --executor-memory 10g Terminator.jar

spark-2.1.0-bin-hadoop2.7/bin/spark-submit --class "SparkDemo" --master local[*] target/scala-2.11/hello-akka_2.11-1.0.jar
```

# Spark Streaming

Receive a continuous flow and splitting it into small data chucks

### Architecture

* Discretized Stream(DStream): is internally a sequence of RDDs
 * DStream support RDDs operations like transformations
 * Additionally, DStream has stateful operations to aggregation data across time
 * DStream support output operations, similar to actions of RDDs but run periodically, producing output batch
* Microbatch: a continuous series of small batches of data
* Batched are generated at regular time intervals. Batch interval usually between 500 ms to seconds


### Spark Stream execution

* Receive a data stream from multiple sources
* For each input source, Spark launches streaming receivers as tasks on executors
* Receiver task gather data from sources and store it in RDDs
* Receivers also responsible for replicating data among other executors to support fault tolerance
* Streaming context in the driver periodically runs Spark jobs to process data and combine them with new RDDs


### Stateless transformation
* individual: Apply to each batch element(RDD) of DStream, not to whole DStream. ex. reduceByKey
* join: Can combine data from multiple DStream. ex. cogroup(), join(), leftOuterJoin()
* Merge: Can merge contents of diffferent DStreams by union()
* Reuse:
* Transform:

### Stateful transformations
are DStream taht track data across time; data from old batches to generate new batches. Require checkpointing to enable fault tolerance
0. Windowed transformations: operations on data over a windows duration
* Update state by key: tracks status between events of the same key. ex. a user session

### Windowed Operations
calculates results in a period of several StreamingContext batch interval time, able to combine the results of several batches

* Two parameters: windows duration and slide duration. Both be multiples of batch interval.
* Batches = window duration / batch interval
* slide duration: how often to calculate results. Default = batch interval

### batch interval

val inputsStream = ssc.socketStream(...)

This inputStream will generate RDDs every 2 seconds, containing last 2 second of data. Now say we define a few window operation on this. The window operation is defined as DStream.window(<window duration>, <slide duration>)
```
val windowStream1 = inputStream.window(Seconds(4))
val windowStream2 = inputStream.window(Seconds(4), Seconds(2))
val windowStream3 = inputStream.window(Seconds(10), Seconds(4))
val windowStream4 = inputStream.window(Seconds(10), Seconds(10)
val windowStream5 = inputStream.window(Seconds(2), Seconds(2))    // same as inputStream
val windowStream6 = inputStream.window(Seconds(11), Seconds(2))   // invalid
val windowStream7 = inputStream.window(Seconds(4), Seconds(1))    // invalid
```
Both, windowStream1 and windowStream2 will generate RDDs containing data from last 4 seconds. And the RDDs will be generated every 2 seconds (if the slide duration is not specified as in windowStream1, then the slide duration was assumed to be inputStream's batch duration = 2 sec). Note that each of these windows of data are overlapping. Window RDD at time 10 will contain data from times 6 to 10 (i.e. slightly after 6 to end of 10), and window RDD at time 12 will contain data from 8 to 12.

Similarly, windowStream3 will generate RDDs every 4 seconds, each containing data from last 10 seconds. And windowStream4 will generate non-overlapping windows, that is, RDDs every 10 seconds, containing data from last 10 seconds. windowStream5 is essentially same as the inputStream.

##### Update State by key


### Output Operations

* No output, no DStream evaluated
* foreachRDD() is a generic output operation

### Checkpointing

* Limits the state to be recomputed when a fault occurs. Checkpinting tells how far back need to go.
* Driver program fault tolerance. Start a driver from last checkpoint if it crashes

### Streaming performance

##### parallelism
* Increasing parallelism: specify parallelism for operations such as reduceByKey()
* Adding receptors: creating multiple input DStreams, which creates multiple receivers. Apply union() to merge
* Repartitioning data: redistribute data by repartition()

##### Window Size and Batch Size

Try batch size from 500 ms to 10 seconds

##### Garbage Collector

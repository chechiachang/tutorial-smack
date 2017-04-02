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

### Architecture

* Discretized Stream(DStream): is internally a sequence of RDDs
* Microbatch: a continuous series of small batches of data
* Transformations
* Output

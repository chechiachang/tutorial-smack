Mesos
===

# Distributed System

* Package Management
* Deploy
* Monitoring
* Discovering

# Apache Mesos

* General purpose cluster manager
* Make different machines "seen" as a single large machine, a data center system

Challenge:
packaging
deployment tasks
use of resources

### Mesos Framework

* PaaS: Deploy and manage
* MESOS: Build and run
* IaaS: Provision

ZooKeeper: discover services
Chronos: scheduling services
Marathon and Aurora: executing services

### Architecture

* Mesos Master: deamon that manages resources sharing across by frameworks by making resource offers
* Mesos Agent: manage software components, Mesos Frameworks, for task execution
* Mesos Framework
 * a scheduler registers with master to be offered the services
 * an executor process launched on agent nodes to run frameworks' tasks
* Master decides resource distribution, agents select which offered resource to be used
* Agent passes description to Mesos of tasks it wants to run

### Schedule to run a task

0. Agent 1 reports 4 CPUs and 4GB memory free
* master invoke allocation policy, tell Agent 1 that framework 1 should be offered all resources
* Master sends a resource offer to framework 1
* framework scheduler replies master with information about two tasks to run on agent 1
* master send tasks to agent 1, which allocates resources to the framework executor
* executor launch tasks
* unallocated(free) resources may offer to other framework

# Mesos 101

```
./spark-shell --master mesos://127.0.0.1:5050
```

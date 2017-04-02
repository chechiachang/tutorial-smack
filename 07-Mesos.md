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

* Master server: manage slave servers
* Slave server: manage software components, Mesos Frameworks, for task execution
* Mesos Framework
 * a scheduler records the services offered by the master
 * slave nodes or servers that process tasks

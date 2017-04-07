Swarm
===

https://docs.docker.com/engine/swarm/#feature-highlights

# Swarm mode overview

* Cluster integrated with docker engine
* Decentralized
* Declarative service model
* Scaling
* Desired state reconciliation

# Key concept


### Swarm
* is a cluster of docker engines
* Cluster management and orchestration feature by SwarmKit
* cluster running in swarm mode

### Node
* is an instance of Docker engine participating in the swarm
* manager node dispatch tasks and maintain desired state of workers
* manager node perform orchestration and cluster management functions
* manager nodes elect a single leader to conduct orchestration tasks
* worker nodes receive and execute tasks, report on tasks
* manager node can also work as worker
* worker node reports state to manager

### Service

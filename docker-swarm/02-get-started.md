Get Started
===

# Activities
* Initialize a cluster in swarm mode
* Add nodes
* Deploy application service
* Manage swarm

# Setup

* Three networked host machines
* Docker Engine 1.12 or later installed
* the IP address of the manager machine
* open ports between the hosts

use docker-machine

##### Docker Machine on AWS

https://docs.docker.com/machine/examples/aws/

```
docker-machine create --driver amazonec2 --amazonec2-regin us-east-01 aws-01

docker-machine ls

docker-machine ip aws-01

docker-machine inspect aws-01

docker-machine ssh aws-01
```

##### Docker Machine on Virtualbox

https://docs.docker.com/machine/drivers/virtualbox/

```
docker-machine create --driver=virtualbox vbox-01

```

# Create a swarm

```
$ docker-machine ls

NAME      ACTIVE   DRIVER       STATE     URL                         SWARM   DOCKER        ERRORS
aws-01    -        amazonec2    Running   tcp://107.21.89.1:2376              v17.04.0-ce   
vbox-01   -        virtualbox   Running   tcp://192.168.99.101:2376           v17.04.0-ce   
vbox-02   -        virtualbox   Running   tcp://192.168.99.102:2376           v17.04.0-ce

$ docker swarm init --advertise-addr 192.168.99.101

Swarm initialized: current node (o1xq7fcf4a0a3cy12lpbqgyad) is now a manager.

To add a worker to this swarm, run the following command:

    docker swarm join \
    --token SWMTKN-1-3a0teuafc86036798jyi4k5dfuy5dkftkq7mmpkx9den451s7i-bfxfilqx88mdgcwrjxmt1hnca \
    192.168.99.101:2377

To add a manager to this swarm, run 'docker swarm join-token manager' and follow the instructions.

$ docker info
$ docker node ls

```

##### Join Swarm

```
$ docker swarm join
```

# Deploy a service

```
$ docker service create --replicas 1 --name helloworld alpine ping docker.com

$ docker service inspect helloworld
$ docker service inspect --pretty helloworld
```

# Scale Service

```
$ docker service scale helloworld=5

$ docker service rm helloworld
```

# Drain node

```
$ docker node update --availibility drain vbox-02
```

#Routing mesh

https://docs.docker.com/engine/swarm/ingress/#publish-a-port-for-a-service

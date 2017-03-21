DC/OS
===

# Install

[Install DC/OS with Vagrant](https://dcos.io/docs/1.8/administration/installing/local/)

### Install

* Git
* Vagrant 1.9.2
* VirtualBox

$ virtualbox-dkms linux-headers-generic.

Reconfigure virtualbox if needed

$ sudo dpkg-reconfigure virtualbox-dkms
$ sudo dpkg-reconfigure virtualbox
$ sudo modprobe vboxdrv

## Vagrant up

$ git clone https://github.com/dcos/dcos-vagrant
$ cd dcos-vagrnt
$ cp VagrantConfig-1m-1a-1p.yaml VagrantConfig.yaml
$ vagrant up

[Vagrant Doc](https://www.vagrantup.com/docs/)

##### VagrantConfig-1m-1a-1p.yaml

m1:
  ip: 192.168.65.90
  cpus: 2
  memory: 1024
  type: master
a1:
  ip: 192.168.65.111
  cpus: 4
  memory: 6144
  memory-reserved: 512
  type: agent-private
p1:
  ip: 192.168.65.60
  cpus: 2
  memory: 1536
  memory-reserved: 512
  type: agent-public
  aliases:
  - spring.acme.org
  - oinker.acme.org
boot:
  ip: 192.168.65.50
  cpus: 2
  memory: 1024
  type: boot

This may take a while (half an hour in my case) to run all the VMs.

### DC/OS GUI

Access dc/os GUI on [http://m1.dcos/](http://m1.dcos/)

### Smack ON DC/OS

http://www.cakesolutions.net/teamblogs/smack-stack-on-dcos


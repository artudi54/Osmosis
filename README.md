# Osmosis - IoT Project
## About project
This is a project directed to create simple environment to show how certain IoT device configurations can be balanced bacing on task number and capacity of the device. Based on those two parameters the concentration is calculated. Each connected device pairs compare their concentration and exchange the tasks, to balance each other. In the end the result of the simulation is a balanced device configuration with evenly (based on concentration) distributed tasks.

Each device has these parameters:
- ``name (string)`` - name of the device
- ``tasks (integer)`` - initial number of tasks for the device
- ``capacity (integer)`` - task capacity of the device
- ``delay (real nubmer)`` - delay between each task exchange in seconds
- ``neighbours (string list)`` - list of neighbour device names

The list of devices is a configuration for the simulator.

## Technologies
- Java 11
- Gradle
- Graphviz Java - for generating graph images
- Jackson Databind - for configuration loading


## How to run
### With IntelliJ IDEA
1. Open project with IntelliJ IDEA
2. Wait for configuration to finish.
3. Navigate to ``Main.java`` file and run the program with play button.


## Execution
At the very beggining of the program you are asked to enter a letter specyfying the scenario. Available scenarios are:
- ``f`` - factory simulation - linearly connected nodes with one overloaded
- ``h`` - large factory simulation - larger version of 'f'
- ``c`` - cloud simulation - a scenario that resemles real cloud-edge-iotdevices nodes
- ``v`` - large cloud simulation - larger version of 'c'

Configuration files are jsons - they specify all the parameters for the execution. You can create your own configurations based on the examples and use them in the program.


The execution runs with 100x speed - it can be adjusted in Main class.

At the end of the execution you are presented with gif result ``output/output.gif``. The ``output`` directory also contains all images generated in a process.

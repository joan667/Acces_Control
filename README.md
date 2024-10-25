<p align="center">
  <h3 align="center">Access Control Project</h3>
  <p align="center">
    An access control system for UAB Software Design course, that allows to manage doors and areas access for a building.
  </p>
</p>



## Table of contents

- [Introduction](#introduction)
- [What's included](#whats-included)
    - [Scheme](#whats-included)
    - [UML](#uml)
- [Installation](#installation)
    - [Prerequisites](#prerequisites)
    - [Setup](#setup)
- [Usage](#usage)
- [Creators](#creators)
- [License](#license)



## Introduction
This project is an access control system for UAB Software Design course, that allows to manage doors and areas access for a building. It's a Java project that uses the State pattern to manage the doors states, and the Observer pattern to notify the users when a door is opened or closed.

Additionally, it has a web server that allows to simulate the building and the doors, and to interact with them. The web server is a Java server that allows to control the doors and areas access, and to see the building and the doors in a web page.


## What's included
Within the download you'll find the following directories and files:

```text
root/
├── src/
│   ├── baseNoStates/
│   │   ├── doorStates/
│   │   │   ├── LockedState.java
│   │   │   ├── ProppedState.java
│   │   │   ├── UnlockedShortlyState.java
│   │   │   └── UnlockedState.java
│   │   ├── requests/
│   │   │   ├── Request.java
│   │   │   ├── RequestArea.java
│   │   │   ├── RequestReader.java
│   │   │   └── RequestRefresh.java
│   │   ├── uml/
│   │   │   ├── base_no_states.png
│   │   │   ├── base_no_states.puml
│   │   │   ├── package_requests.png
│   │   │   └── package_requests.puml
│   │   ├── Actions.java
│   │   ├── Area.java
│   │   ├── Clock.java
│   │   ├── Day.java
│   │   ├── DaySchedule.java
│   │   ├── DirectoryAreas.java
│   │   ├── DirectoryUserGroups.java
│   │   ├── Door.java
│   │   ├── DoorState.java
│   │   ├── Main.java
│   │   ├── Observer.java
│   │   ├── Observerable.java
│   │   ├── Partition.java
│   │   ├── Schedule.java
│   │   ├── Space.java
│   │   ├── States.java
│   │   ├── TimedEvent.java
│   │   ├── User.java
│   │   ├── UserGroup.java
│   │   └── WebServer.java
│   └── simulator/
│       ├── images/
│       ├── building.html
│       └── building.js
├── LICENSE
└── README.md
```

### UML
The following UML diagram, shows how all the classes are connected and how it works.

![UML Diagram](src/baseNoStates/uml/base_no_states.png)

## Installation
To run this project, you need some [prerequisites](#prerequisites) that you can find below, and you need to follow the [setup](#setup) steps.

### Prerequisites
First of all, you need to have installed the following software:

- IntelliJ IDEA
- PUML plugin (for IntelliJ IDEA)
- JSON library (org.json:json:20240303)

### Setup
To run this project, you need to follow these steps:

#### 1. Install IntelliJ IDEA
Install IntelliJ IDEA from the [official website](https://www.jetbrains.com/idea/download/) with the UAB email to get the student license.

#### 2. Install the PUML plugin
Install the PUML plugin from the IntelliJ IDEA marketplace. You can find it in `:gear: -> Plugins... -> Marketplace -> Search for "PlantUML integration" -> Install`.

#### 3. Install the JSON library
Add the JSON library to the project. You can do it in `File -> Project Structure -> Libraries -> + -> From Maven... -> Search for "org.json:json:20240303" -> OK`.

#### 4. Prepare the project
Open the project in IntelliJ IDEA and add the `src` folder as a source folder. You can do it in `File -> Project Structure -> Modules -> Sources -> Add Content Root`.

## Usage
To execute the project, you need to:

1. Open the Main class file (`src/baseNoStates/Main.java`).
2. Execute it pressing the green play button (top right corner).
3. Open the link shown in the terminal.

## Creators
This project was developed by Software Design teachers at UAB, that have made the base code available for students to work on it.

The following students have worked on this project:

### Xavi Burgos
- Website: [xburgos.es](https://xburgos.es)
- LinkedIn: [@xavi-burgos](https://www.linkedin.com/in/xavi-burgos/)
- GitHub: [@xavi-burgos99](https://github.com/xavi-burgos99)
- X (Twitter): [@xavi_burgos99](https://x.com/xavi_burgos99)

### Gianina Sotelo
- LinkedIn: [@gianina-sotelo](https://www.linkedin.com/in/gianina-sotelo/)
- GitHub: [@gianisotelo](https://github.com/gianisotelo)

### Joan Masdefiol
- LinkedIn: [Joan Masdefiol](https://www.linkedin.com/in/joan-masdefiol-colome-3589a22b4/)
- GitHub: [@joan667](https://github.com/joan667)

## License
This project has been made by Software Design teachers at UAB. So only students from this course can use it, and it's not allowed to use it for commercial purposes.

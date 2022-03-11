<!-- PROJECT SHIELDS -->
[![Contributors][contributors-shield]][contributors-url]
[![Forks][forks-shield]][forks-url]
[![Stargazers][stars-shield]][stars-url]
[![Issues][issues-shield]][issues-url]
[![MIT License][license-shield]][license-url]
[![Quality][quality-shield]][quality-url]

<!-- PROJECT LOGO -->
<!--suppress ALL -->
<br />
<p align="center">
  <a href="https://github.com/TamrielNetwork/VitalWarp">
    <img src="images/logo.png" alt="Logo" width="80" height="80">
  </a>

<h3 align="center">VitalWarp</h3>

  <p align="center">
    Set warps on Spigot and Paper
    <br />
    <a href="https://github.com/TamrielNetwork/VitalWarp"><strong>Explore the docs »</strong></a>
    <br />
    <br />
    <a href="https://github.com/TamrielNetwork/VitalWarp">View Demo</a>
    ·
    <a href="https://github.com/TamrielNetwork/VitalWarp/issues">Report Bug</a>
    ·
    <a href="https://github.com/TamrielNetwork/VitalWarp/issues">Request Feature</a>
  </p>

<!-- TABLE OF CONTENTS -->
<details open="open">
  <summary><h2 style="display: inline-block">Table of Contents</h2></summary>
  <ol>
    <li>
      <a href="#about-the-project">About The Project</a>
      <ul>
        <li><a href="#description">Description</a></li>
        <li><a href="#features">Features</a></li>
        <li><a href="#built-with">Built With</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#commands-and-permissions">Commands and Permissions</a></li>
        <li><a href="#configuration - config.yml">Configuration</a></li>
		<li><a href="#configuration - messages.yml">Configuration</a></li>
      </ul>
    </li>
    <li><a href="#roadmap">Roadmap</a></li>
    <li><a href="#contributing">Contributing</a></li>
    <li><a href="#license">License</a></li>
    <li><a href="#contact">Contact</a></li>
    <li><a href="#acknowledgements">Acknowledgements</a></li>
  </ol>
</details>

<!-- ABOUT THE PROJECT -->

## About The Project

### Description

VitalWarp is a Spigot Plugin that gives players the ability to set warps and teleport to them.

This plugin is perfect for any server wanting their players to have specific points to teleport to.

### Features

* Set warps and teleport to them.

### Built With

* [Gradle 7](https://docs.gradle.org/7.4/release-notes.html)
* [OpenJDK 17](https://openjdk.java.net/projects/jdk/17/)

<!-- GETTING STARTED -->

## Getting Started

To get the plugin running on your server follow these simple steps.

### Commands and Permissions

1. Permission: `vitalwarp.warp`

* Command: `/warp <name>`
* Description: Teleport to a warp

2. Permission: `vitalwarp.setwarp`

* Command: `/setwarp <name>`
* Description: Set a warp

3. Permission: `vitalwarp.delwarp`

* Command: `/delwarp <name>`
* Description: Delete a warp

4. Permission: `vitalwarp.list`

* Command: `/warps`
* Description: List warps

5. Permission: `vitalwarp.delay.bypass`

* Description: Bypass delay

### Configuration - config.yml

```
# Command delay
delay:
  enabled: true
  # time in s
  time: 3

# Choose a storage system (mysql or yaml)
storage-system: yaml

mysql:
  host: "localhost"
  port: 3306
  database: vitalwarp
  username: "vitalwarp"
  password: ""
  prefix: "server_"
```

### Configuration - messages.yml

```
cmd: "&fUsage: &b/warp <name>"
warp-set: "&fWarp has been set"
warp-removed: "&fWarp has been removed"
no-perms: "&cYou don't have enough permissions!"
player-only: "&cThis command can only be executed by players!"
countdown: "&fTeleporting in &b%countdown% &fseconds"
invalid-name: "&cOnly a max of &b16 alphanumeric chars &cis allowed!"
active-delay: "&cYou are already trying to teleport!"
no-warps: "&cThere are no warps!"
```

<!-- ROADMAP -->

## Roadmap

See the [open issues](https://github.com/TamrielNetwork/VitalWarp/issues) for a list of proposed features (and known
issues).

<!-- CONTRIBUTING -->

## Contributing

Contributions are what make the open source community such an amazing place to be, learn, inspire, and create. Any
contributions you make are **greatly appreciated**.

1. Fork the Project
2. Create your Feature Branch (`git checkout -b feature/AmazingFeature`)
3. Commit your Changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the Branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

<!-- LICENSE -->

## License

Distributed under the GNU General Public License v3.0. See `LICENSE` for more information.

<!-- CONTACT -->

## Contact

Leopold Meinel - [@TamrielN](https://twitter.com/TamrielN) - Twitter

Leopold Meinel - [contact@tamriel.me](mailto:contact@tamriel.me) - eMail

Project Link - [VitalWarp](https://github.com/TamrielNetwork/VitalWarp) - GitHub

<!-- ACKNOWLEDGEMENTS -->

### Acknowledgements

* [README.md - othneildrew](https://github.com/othneildrew/Best-README-Template)

<!-- MARKDOWN LINKS & IMAGES -->

[contributors-shield]: https://img.shields.io/github/contributors-anon/TamrielNetwork/VitalWarp?style=for-the-badge

[contributors-url]: https://github.com/TamrielNetwork/VitalWarp/graphs/contributors

[forks-shield]: https://img.shields.io/github/forks/TamrielNetwork/VitalWarp?label=Forks&style=for-the-badge

[forks-url]: https://github.com/TamrielNetwork/VitalWarp/network/members

[stars-shield]: https://img.shields.io/github/stars/TamrielNetwork/VitalWarp?style=for-the-badge

[stars-url]: https://github.com/TamrielNetwork/VitalWarp/stargazers

[issues-shield]: https://img.shields.io/github/issues/TamrielNetwork/VitalWarp?style=for-the-badge

[issues-url]: https://github.com/TamrielNetwork/VitalWarp/issues

[license-shield]: https://img.shields.io/github/license/TamrielNetwork/VitalWarp?style=for-the-badge

[license-url]: https://github.com/TamrielNetwork/VitalWarp/blob/main/LICENSE

[quality-shield]: https://img.shields.io/codefactor/grade/github/TamrielNetwork/VitalWarp?style=for-the-badge

[quality-url]: https://www.codefactor.io/repository/github/TamrielNetwork/VitalWarp

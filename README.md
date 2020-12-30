# lingo-api VKBEP 2020/2021
 Lingo Api made by Redouan el Hidraoui
 
 The main branch will always be deployed on push if the build passes (Github Action). If the build does NOT pass, the main branch will not deploy. 

[![SonarCloud](https://sonarcloud.io/images/project_badges/sonarcloud-white.svg)](https://sonarcloud.io/dashboard?id=Redouanelh_lingo-api)

![example workflow name](https://github.com/Redouanelh/lingo-api/workflows/Maven-build-main-branch/badge.svg)

<!-- ![example workflow name](https://github.com/Redouanelh/lingo-api/workflows/Maven-build-development-branch/badge.svg) -->

 ### Request URL
 - *http://localhost:8080*
 - *https://lingo-api.herokuapp.com* (Needs to boot up after some inactivity)

* The game
  * **GET** /api/player/all [Get all players]
  * **GET** /api/player/{username} [Get one player]
  * **POST** /api/player/ [JSON body parameter(s): 'username': 'xxx'] [Register as player]
  * **POST** /api/game/start/ [JSON body parameter(s): 'username': 'xxx'] [Start a new game]
  * **PUT** /api/game/turn/ [JSON body parameter(s): 'username': 'xxx', 'guess': 'xxx'] [Perform a turn]

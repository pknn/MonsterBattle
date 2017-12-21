
# Monster Battle Project
Monster Battle is a battle-based game developed in Java with integration of JavaFX GUI Frameworks.   

**Why Monster Battle**.   
This project is inspired from battle scene of Pokémon’s Nintendo gameplay. The goal of this game is to defeat as much opponent as you can, collect reward as a gold and collect EXP to upgrade your monster’s level.

**Monster Battle Components**.     
Because I have integrated GUI in this project, I also integrated MVC ideas to my projects.

**1.Model** : Controll how the application connect to the data and what to do. In this project, all the models are loacated in ***Component*** directory.  
**2.Controller** : Controll the **View** for how GUI interacting with users. In this project, all the controller are located in ***Controller*** directory.  
**3.View** : Design what will GUI looks like and what user interacting with the application. In this project, all the view are located in ***FXML*** directory.

**Blueprints**
1. 7 FXML Scenes with individual controllers.
2. Monster class : Class for Monster objects.
3. Status class : Status class for Status objects. Used for easier organizing Monster status.
4. Gamehelper class : Helper class for contacting the database (Load save from file, Save game to file, Add slot to save and Delete slot from save) and Randomly pick opponent.
5. Type enumerator : Type enum for easier organizing type of monster and easier for debugging.

**Downloading**

You can download latest release from the release menu or simply clone the file to you computer.
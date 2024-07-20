# Just-CTGUI
### [**![](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.2.0/assets/cozy/requires/fabric-api_vector.svg)**](https://www.curseforge.com/minecraft/mc-mods/fabric-api) [**![](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.2.0/assets/cozy/available/modrinth_vector.svg)**](https://modrinth.com/mod/just-ctgui) [**![](https://cdn.jsdelivr.net/npm/@intergrav/devins-badges@3.2.0/assets/cozy/available/curseforge_vector.svg)**](https://www.curseforge.com/minecraft/mc-mods/just-ctgui)

### **Information for those who want to build this:**

MCreator plugins: File manager.

Minecraft version: 1.20.1 Forge

### **What is this mod and how to use it?**

**The Just Craft Tweaker GUI mod provides a visual recipe editor in minecraft using Craft Tweaker and a simple Zen Script programming language. To be more precise, it is not an additional mod for Craft Tweaker, but simply adds a visual editor to the game and automatic code generation in Zen Script.**

**It is very easy to use this mod, it is enough to install the mod on the server and client, if desired,** _**you can install Craft Tweaker for ease of development, but this is optional.**_ **Thanks to this, you can start creating your own recipes for assembly even before the official release of Craft Tweaker on this version. Thus, the recipes will not work in the game, but when the craft tweaker is installed, they will all work.**

After installing JCTGUI and CT on the server and client, you can start creating the first recipe. To do this, open the chat and register the beginning of the command "/ctgui" and press the space bar. Then you will have several options for crafting blocks. At the moment, only "crafting\_table" is supported and press the space bar. Next, you need to select an action. At the moment, you can only "open". As a result, our command should look something like this before executing: **/ctgui crafting\_table open**

Then we will open the GUI itself. Before opening, take the items from which you want to craft into your inventory. When the GUI is open, you can see an interface similar to crafting table, in which you need to lay out the recipe. Creating using items from other mods is possible, but errors may occur. When you have posted your recipe, you can click the Generate button to generate Zen Script code. The generated code will appear in your chat and you can check it for errors if you understand this language. Then you can click the Save button and the recipe will be automatically saved to the Scripts folder called generated1.zs. It is not possible to choose the name of the recipe and file manually yet, and this happens automatically.

Now that the recipe has been created, you need to update the list of Craft Tweaker recipes. This can be done by logging back into minecraft or by running the command below: **/reload**

The Todo list can be found here: https://github.com/300f20t/Just-CTGUI/issues/3

My discord server: https://discord.gg/y3xkvMrYt6

*-This mod is not maintained or supported by the the creators of  Craft Tweaker.*

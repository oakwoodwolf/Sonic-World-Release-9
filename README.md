# Sonic World Release 9

Blitz Sonic Engine code

THIS IS A CODE OF SONIC WORLD ENGINE RELEASE 9, a fork of BlitzSonic, containing 42 characters and a Chao Garden.

## Requirements

This version of the game is built on V1.106 of [Blitz3D](https://www.blitzcoder.org/forum/downloads.php) with various required userlibs.
**Newer versions don't work as compatibility with userlibs are broken**

- FastImage
- FastExt
- ParticleWorks
- Blitz3DA
- DirectX7A
- User32

As Blitz3D is no longer maintained, most of these userlibs' original links are down, so all the required .dll's and .decl's are provided.
Assets are not included and need to be provided. See [Assets]() heading to see how to acquire said assets.

 ------------------------------------------------------------------------

## Installation

1. Follow the steps of the Blitz3D download
2. Download this project and put it in this own folder.
3. Drag all the assets into this folder for the game.
4. Copy all of the `.dll` and `.decl` files into Blitz3D's install directory into a `userlibs` folder.
5. You can delete the `.decl` files in the game's folder as they only need to be read by Blitz3D.
6. Open `Sonic World.bb` in Blitz3D or your IDE (I recommend [IDEaL](http://www.fungamesfactory.com/download.php?get=IDEalSetup_0.8.87.exe))
7. Go to Program/Run Program. If everything's placed correctly the game should run.

 ------------------------------------------------------------------------

## Building

### Using IDEaL (Recommended!)

1. Open `Sonic World.bb` in IDEaL
2. Click `Compile/Create executable`.

### Using Blitz3D

1. Open `Sonic World.bb` in Blitz3D
2. Click `Program/Create executable`.

### Using any code editor

***you will have to add the Blitz3D/**bin** folder to your `path` Environment Variable.***

1. Open the folder for this project in the terminal.
2. Run ```blitzcc -o "Sonic World" "Sonic World.bb"``` in the game folder to build an executable using the compiler.

### Using Visual Studio Code (Experimental)

As this is a recent discovery, it has not been tested much. Hopefully this repo will be updated to support the bbdoc syntax exclusive to this extension.

***you will have to add the Blitz3D/**bin** folder to your `path` Environment Variable.***

1. Install [this extension](https://marketplace.visualstudio.com/items?itemName=dones.blitz3d) in the VSCode marketplace.
2. Go into the extension settings and set your Blitz3D path. This will be used for generating stubs and documentation for built-in methods.
3. Open this repository as a folder in VSCode.
4. Go to `Sonic World.bb`.
5. From the Command Pallate (Ctrl + Shift + P), you should see methods from the Blitz3D extension. These can be used for running open files and debugging.
6. To build an exe you will need to open a terminal in VSCode and run ```blitzcc -o "Sonic World" "Sonic World.bb"```.


***When distributing the game, you need the exe, all the dlls, any folder without an underscore and a GameArchive.swarc (this file can be empty if using extracted assets)***

## Assets

This repository ONLY includes the code for the game. Assets will need to be retrieved from a [compiled version of Sonic World Release 9](https://www.mediafire.com/file/jo038klpjdu3c6c/Sonic_World_Release_9.zip/file).
The game assets are encrypted using Molebox, so you need [De-Mole-ition](https://lifeinhex.com/updated-molebox-unpacker/), to extract the assets contained in GameArchive.swarc.
The extracted folders should all be placed in the game's main directory (i.e. Characters should be in the same folder as Mods, and _SourceCode)

## Contributions

Contributions are welcome! Feel free to fork this repository and make pull requests for new features or optimizations. If any new assets not from the base game are required for this, make sure to include those assets as well.

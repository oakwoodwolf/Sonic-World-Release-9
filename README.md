# Sonic World Release 9
Blitz Sonic Engine code

THIS IS A CODE OF SONIC WORLD ENGINE RELEASE 9
## Build instructions
This version of the game is built on V1.106 of [Blitz3D](https://www.blitzcoder.org/forum/downloads.php) with various required userlibs.
**Newer versions don't work as compatibility with userlibs are broken**
- FastImage
- [FastExt](https://www.blitzcoder.org/forum/topic.php?id=966) (Use the dll in this repo to activate it)
- ParticleWorks
- Blitz3DA
- DirectX7A

As Blitz3D is no longer maintained, most of these userlibs' original links are down, so mirrors of relevant ones are provided.
Put the downloaded userlibs into a `userlibs` folder in Blitz3D's install location.

## Assets
This repository ONLY includes the code for the game. Assets will need to be retrieved from a [compiled version of Sonic World Release 9](https://www.mediafire.com/file/jo038klpjdu3c6c/Sonic_World_Release_9.zip/file).
The game assets are encrypted using Molebox, so you need [De-Mole-ition](https://lifeinhex.com/updated-molebox-unpacker/), to extract the assets contained in GameArchive.swarc.
The extracted folders should all be placed in the game's main directory (i.e. Characters should be in the same folder as Mods, and _SourceCode)


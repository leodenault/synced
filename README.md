# Synced

A Discord audio player that streams audio from the desktop.

Synced runs as a bot that plays audio to your selected Discord server. You can control what audio is
streamed to the server from the app running on your desktop computer.

## Download

Download the latest version [here](https://github.com/leodenault/synced/releases/latest).

## Running the app

Extract the files from the `.zip` archive to your desired location on your file system. Open the
extract folder and run `Synced.sh` on Linux or `Synced.bat` on Windows.

## Getting a bot token

The first thing you'll need upon running the app is a bot token. To do so, you'll need to create a
bot application in Discord. The discord.py documentation
has [great instructions](https://discordpy.readthedocs.io/en/stable/discord.html) for doing so.

Once you've created the bot and copied its token you can paste it into the bot token field displayed
in Synced at startup. You may also choose to have Synced remember the bot token for you. If you
select this option, note that **the token will be stored as-is on your system under the
currently-running account**.

## Joining a server

Next, you'll need to select a server and channel to join. If the server list is empty, then it's
likely that your bot has not been authorized to join any servers. You can follow
discord.py's [instructions](https://discordpy.readthedocs.io/en/stable/discord.html#inviting-your-bot)
for doing so. Make sure to select the **connect** and **speak** permissions.

## Playing audio

Now that you've selected a server and channel, you should see your bot hanging out in there on
Discord. To play audio, you'll want to switch over to the player tab and select an audio file to
play. If there's no audio listed in the player tab, then you likely need to pick a different
top-level folder that contains the audio you're looking for.

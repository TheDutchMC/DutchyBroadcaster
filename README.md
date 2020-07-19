# DutchyBroadcaster
Minecraft 1.16 and up

## Commands
/reloadconfig: Reloads the config from disk

##Permissions
broadcaster.*: Grants the user all DutchyBroadcaster permissions
broadcaster.reloadconfig: Allows the user to use /reloadconfig

##Config
This is the default config.yml
```#Should the broadcaster be enabled. Default: true
enableBroadcast: "true"

#Should the broadcaster broadcast in a randomized order. Default: true
broadcastRandomizedOrder: "true"

#What should the broadcast interval be. In Seconds. Default: 300
broadcastInterval: "300"

#What color should the text broadcasts have. In hexadecial. Default: "#ffff00"
broadcastTextHexColor: "#ffff00"

#The broadcast messages
#Format for tellraw: "raw&<raw json>"
#Format for text only: "text&<text>"
#Only text will receive the hex color set above, for raw you can set it manually in the JSON
#NOTE: YOU NEED TO ESCAPE ANY QUOTES YOU USE WITH A BACKSLASH! e.g: {\"text\":\"I am escaped\"}
broadcastMessages:
- "text&Hi I am a message!"
- "raw&{\"text\":\"Hi i am also a message!\"}"```


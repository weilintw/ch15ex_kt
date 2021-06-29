import java.lang.IllegalStateException

/*c15-20*/
fun main() {
//呼叫 Player 類別的主建構函數，產生一個類別實體
//    var player = Player("wl")
//    player.castFireBall(5)
//
//    var currentRoom = TownSquare() //Room("Foyer")
//    println(currentRoom.description())
//    println(currentRoom.load())
//
//    player.auraColor()
//    printPlayerStatus(player)
    Game.play()
}

//private fun printPlayerStatus(player:Player) {
//    println(
//        "光環顏色：${player.auraColor()}" + "       走運嗎？" +
//                "${if (player.isBlessed) "是的" else "否"}"
//    )
//    println("${player.name}${player.formaHealthStatus()}")
//}
object  Game{
    private var player = Player("wl")
    private var currentRoom: Room = TownSquare()
    private  var worldMap = listOf(
        listOf(currentRoom,Room("Tavern"),Room("Back Room")),
        listOf(Room("Long Corridor"),Room("Generic Room")),

    )
    fun play() {
        while (true){
            println(currentRoom.description())
            println(currentRoom.load())
            player.auraColor()
            printPlayerStatus(player)
            print("> Enter you command: ")
            //println("Last command: ${readLine()}")
            println(GameInput(readLine()).processCommand())
        }
    }

    init {
        println("Welcome, adventurer")
        player.castFireBall(5)
    }
    private fun printPlayerStatus(player:Player) {
        println(
            "光環顏色：${player.auraColor()}" + "       走運嗎？" +
                    "${if (player.isBlessed) "是的" else "否"}"
        )
        println("${player.name}${player.formaHealthStatus()}")
    }
    private  class GameInput(arg: String?){
        private  val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1,{""})
        fun processCommand() = when (command.toLowerCase()){
            "move" -> move(argument)
            else -> commandNotFound()
        }
        private  fun commandNotFound() ="I'm not quite sure what you're trying to do!"
    }
    private  fun move(directionInput: String) =
        try {
            val direction =Direction.valueOf(directionInput.toUpperCase())
            val newPosition =direction.updateCoordinate(player.currentPosition)
            if (!newPosition.isInBounds){
                throw IllegalStateException("$direction is out of bounds.")
            }
            val newRoom  = worldMap[newPosition.y][newPosition.x]
            player.currentPosition = newPosition
            currentRoom = newRoom
            "OK, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
        } catch (e: Exception){
            "Invalid direction: $directionInput. "
        }
}
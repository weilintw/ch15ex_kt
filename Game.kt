/*c15-16*/
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
    private var currentRoom = TownSquare()

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
            else -> commandNotFound()
        }
        private  fun commandNotFound() ="I'm not quite sure what you're trying to do!"
    }
}
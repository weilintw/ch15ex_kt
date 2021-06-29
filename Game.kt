/*c13-12
*/
fun main() {
//呼叫 Player 類別的主建構函數，產生一個類別實體
    var player = Player("wl")
    player.castFireBall(5)
    player.auraColor()
    printPlayerStatus(player)
}

private fun printPlayerStatus(player:Player) {
    println(
        "光環顏色：${player.auraColor()}" + "       走運嗎？" +
                "${if (player.isBlessed) "是的" else "否"}"
    )
    println("${player.name}${player.formaHealthStatus()}")
}
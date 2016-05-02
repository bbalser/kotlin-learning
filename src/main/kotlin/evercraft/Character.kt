package evercraft

data class Character(val name: String,
                     val alignment: Alignment = Alignment.NEUTRAL,
                     val armorClass: Int = 10,
                     val hitPoints: Int = 5) {

    fun rename(newName: String): Character = copy(name = newName)

}
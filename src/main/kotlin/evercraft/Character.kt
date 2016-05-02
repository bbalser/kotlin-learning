package evercraft

data class Character(val name: String) {

    fun rename(newName: String): Character = copy(name = newName)

}
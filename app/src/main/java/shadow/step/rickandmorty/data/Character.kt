package shadow.step.rickandmorty.data

import java.util.ArrayList

data class Character(
    val image: Int,
    var name: String,
    var race: String,
    var status: String,
    val gender: String
)

object CharacterList{
    val characterListItems = ArrayList<Character>()
}
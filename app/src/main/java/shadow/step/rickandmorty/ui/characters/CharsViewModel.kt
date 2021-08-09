package shadow.step.rickandmorty.ui.characters


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import shadow.step.rickandmorty.data.Character
import shadow.step.rickandmorty.data.CharacterList.characterListItems

class CharsViewModel : ViewModel() {

    val charactersLiveData = MutableLiveData<ArrayList<Character>>()

    init {
        charactersLiveData.value = characterListItems
    }

}
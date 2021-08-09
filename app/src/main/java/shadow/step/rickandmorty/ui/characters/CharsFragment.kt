package shadow.step.rickandmorty.ui.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import shadow.step.rickandmorty.DetailsCharacterFragment
import shadow.step.rickandmorty.MainActivity.Global.detailsCharacterStatus
import shadow.step.rickandmorty.R
import shadow.step.rickandmorty.adapters.CharacterAdapter
import shadow.step.rickandmorty.data.Character
import shadow.step.rickandmorty.databinding.FragmentCharsBinding

class CharsFragment : Fragment(), CharacterAdapter.OnItemClickListener {

    companion object {
//        private const val CHARS_EXTRA_KEY = "CHARS_EXTRA_KEY"
        fun newInstance() = CharsFragment()
    }
    private lateinit var charsViewModel: CharsViewModel
    private var _binding: FragmentCharsBinding? = null
    private val binding get() = _binding!!
    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var recyclerviewCharacters: RecyclerView
    private var items = ArrayList<Character>()


    override fun onStart() {
        super.onStart()
        items.clear()
        charsViewModel.charactersLiveData.observe(this, Observer {
            items.addAll(it)
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        charsViewModel = ViewModelProvider(this).get(CharsViewModel::class.java)
        _binding = FragmentCharsBinding.inflate(inflater, container, false)

        val linearLayoutManager = LinearLayoutManager(activity)
        linearLayoutManager.orientation = LinearLayoutManager.HORIZONTAL
        recyclerviewCharacters = binding.rvCharacters
        recyclerviewCharacters.setHasFixedSize(true)
        recyclerviewCharacters.layoutManager = linearLayoutManager
        characterAdapter = CharacterAdapter(items,this)
        recyclerviewCharacters.adapter = characterAdapter

        val root: View = binding.root
        return root
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onResume() {
        super.onResume()
        characterAdapter.notifyDataSetChanged()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(position: Int) {
        if(!detailsCharacterStatus) {
            Toast.makeText(
                requireContext().applicationContext,
                "Character clicked!",
                Toast.LENGTH_SHORT
            ).show()
            val fragmentDetailsCharacter = DetailsCharacterFragment()
            parentFragmentManager.beginTransaction().apply {
                replace(R.id.containerDetailsCharacter, fragmentDetailsCharacter)
                addToBackStack(null)
                commit()
            }
            detailsCharacterStatus = true
        }else{
            activity?.onBackPressed()
            detailsCharacterStatus = false
        }
    }

}
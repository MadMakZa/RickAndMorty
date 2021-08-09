package shadow.step.rickandmorty.ui.characters

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import shadow.step.rickandmorty.adapters.CharacterAdapter
import shadow.step.rickandmorty.data.Character
import shadow.step.rickandmorty.databinding.FragmentCharsBinding

class CharsFragment : Fragment() {

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
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerviewCharacters = binding.rvCharacters
        recyclerviewCharacters.setHasFixedSize(true)
        recyclerviewCharacters.layoutManager = linearLayoutManager
        characterAdapter = CharacterAdapter(items)
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
}
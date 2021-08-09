package shadow.step.rickandmorty.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import shadow.step.rickandmorty.R
import shadow.step.rickandmorty.data.Character

class CharacterAdapter(private val characterListItems: MutableList<Character>,
                       private val listener: OnItemClickListener
                       )
    : RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.character_item, parent, false)
        return CharacterViewHolder(itemView)
    }
    override fun getItemCount(): Int {
        return characterListItems.size
    }
    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        val currentCharacter = characterListItems[position]
        holder._name.text = currentCharacter.name
        holder._race.text = currentCharacter.race
        holder._status.text = currentCharacter.status
        holder._gender.text = currentCharacter.gender
    }

    inner class CharacterViewHolder(itemView: View): RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var _image: ImageView = itemView.findViewById(R.id.ivCharacter) as ImageView
        val _name: TextView = itemView.findViewById(R.id.tvName) as TextView
        val _race: TextView = itemView.findViewById(R.id.tvRace) as TextView
        val _status: TextView = itemView.findViewById(R.id.tvStatus) as TextView
        val _gender: TextView = itemView.findViewById(R.id.tvGender) as TextView

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            listener.onItemClick(position = absoluteAdapterPosition)
        }
    }

    interface OnItemClickListener{
        fun onItemClick(position: Int)
    }

}
package com.vander.trabalho.trabfinal.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vander.trabalho.trabfinal.entities.People
import kotlinx.android.synthetic.main.item_people.view.*
import android.graphics.drawable.GradientDrawable
import android.graphics.Color

class PeopleAdapter(private var peoples: MutableList<People>, private var flag:Boolean, private var listener: PeopleAdapterListener) :
    RecyclerView.Adapter<PeopleAdapter.ArticleViewHolder>() {

    override fun getItemCount() = peoples.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ArticleViewHolder(
            LayoutInflater
                .from(parent.context)
                .inflate(com.vander.trabalho.trabfinal.R.layout.item_people, parent, false)
        )

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        holder.fillUI(peoples[position])
    }

    inner class ArticleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillUI(people: People) {

            if(people.name!=null){

                itemView.tvname_item.text  = people.name
                itemView.tvname_people.text   = people.tel

                itemView.tvIcon.setText(people.name.substring(0, 1));

                val color = Color.parseColor("#37A3FC")
                (itemView.tvIcon.getBackground() as GradientDrawable).setColor(color)
            }


            //ENTRA AQUI QUANDO FOR PRA CADASTRAR EMPRÉSTIMO
            if(!flag){
                itemView.setOnClickListener{ listener.confirmationLoan(people) }
            }
        }
    }
}
package com.vander.trabalho.trabfinal.ui.adapters

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.vander.trabalho.trabfinal.R
import com.vander.trabalho.trabfinal.entities.Loan
import com.vander.trabalho.trabfinal.entities.People
import kotlinx.android.synthetic.main.item_loan.view.*
import kotlinx.android.synthetic.main.item_people.view.*
import kotlinx.android.synthetic.main.item_people.view.tvname_item
import kotlinx.android.synthetic.main.item_people.view.tvname_people
import java.text.SimpleDateFormat
import java.util.*

class LoanAdapter(private var loans: MutableList<Loan>, private var flag:Boolean, private var listener: LoanAdapterListener) :
    RecyclerView.Adapter<LoanAdapter.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {

        return R.layout.item_loan
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
                ViewHolder(
                    LayoutInflater
                        .from(parent.context)
                        .inflate(viewType, parent, false)
                )

    override fun getItemCount() = loans.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.fillUI(loans[position])
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun fillUI(loan: Loan) {

            if(loan!=null){

                val dataHoraAtual = Date()
                val data = SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual)

                // VERIFICA STATUS
                if(loan.status.equals("Emprestado")){
                    if(data>loan.data_devolution){
                        val color = Color.parseColor("#DF0101")
                        (itemView.ico_status.getBackground() as GradientDrawable).setColor(color)

                        itemView.barrastatus.setBackgroundColor(Color.parseColor("#DF0101"))

                        loan.status = "Atrasado"
                        listener.updateStatus(loan)
                    }else{
                        val color = Color.parseColor("#FFBF00")
                        (itemView.ico_status.getBackground() as GradientDrawable).setColor(color)

                        itemView.barrastatus.setBackgroundColor(Color.parseColor("#FFBF00"))
                    }

                }else if(loan.status.equals("Recebido")){
                    val color = Color.parseColor("#04B486")
                    (itemView.ico_status.getBackground() as GradientDrawable).setColor(color)
                    Log.d("eu", "atrasado")
                    itemView.barrastatus.setBackgroundColor(Color.parseColor("#04B486"))

                }else{
                    val color = Color.parseColor("#DF0101")
                    (itemView.ico_status.getBackground() as GradientDrawable).setColor(color)
                    Log.d("eu", "atrasado")
                    itemView.barrastatus.setBackgroundColor(Color.parseColor("#DF0101"))
                }

                itemView.tvname_item.text      = loan.name_item
                itemView.tvname_people.text    = loan.id_people
                itemView.tvstatus.text         = loan.status
                itemView.tvdatedevolution.text = loan.data_devolution


                // ALTERA STATUS
                if(flag==true){
                    itemView.setOnLongClickListener{
                        itemView.tvstatus.text = "Recebido"
                        loan.status = "Recebido"

                        val color = Color.parseColor("#01DFA5")
                        (itemView.ico_status.getBackground() as GradientDrawable).setColor(color)

                        listener.updateStatus(loan)
                        true
                    }
                }

            }
        }
    }
}
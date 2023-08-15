package com.aya.taskgrand.features.fragment.home.ui.adapter

import com.aya.taskgrand.R
import com.aya.taskgrand.base.BaseAdapter
import com.aya.taskgrand.base.BaseViewHolder
import com.aya.taskgrand.core.extension.gone
import com.aya.taskgrand.core.extension.visible
import com.aya.taskgrand.databinding.ItemMatchBinding
import com.aya.taskgrand.features.fragment.home.data.MatchDB
import com.aya.taskgrand.utils.Constants
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


class ListMatchesDBAdapter()
    : BaseAdapter<MatchDB, ItemMatchBinding>( ){
    override fun layoutRes(): Int = R.layout.item_match

    var current_time = ""

    override fun onViewHolderCreated(viewHolder: BaseViewHolder<MatchDB>) {
        super.onViewHolderCreated(viewHolder)

    }


    override fun onBindItemBinding(
        binding: ItemMatchBinding,
        item: MatchDB,
        position: Int
    ) {
        if(position == 0 ) {
            current_time = item.startDate.toString()
            binding.name.visible() ; binding.date.visible()
        }else if (item.startDate.equals(current_time)){
            binding.name.gone() ; binding.date.gone()
        }else{
            current_time = item.startDate.toString()
            binding.name.visible() ; binding.date.visible()
        }
        binding.apply {
            name.text = checkDate( item.startDate!! , 2)
            date.text = checkDate( item.startDate , 1)

            //
            matchName.text = item.name
            matchDate.text =  checkDate( item.lastUpdated!! , 3)
            //
            states.text = item.status

            teamOne.text = item.HomeTeam
            teamTwo.text = item.AwayTeam

            if (item.scoreHomeTeam != null ) {
                resultMatch.text = "${item.scoreHomeTeam} - ${item.scoreAwayTeam}"
            }else
                resultMatch.text = "0 - 0"

             var time =   checkDate(item.utcDate!!,0)

            startMatch.text ="${adapterContext.resources.getString(R.string.started_at)} ${time}"

            if(item.status?.equals(Constants.MatchType.FINISHED)!!){
                binding.itemLayout.setBackgroundResource(R.drawable.draw_title_pink)
            }else if (item.status.equals(Constants.MatchType.CANCELED))
                binding.itemLayout.setBackgroundResource(R.drawable.draw_title_dark_pink)
            else if (item.status.equals(Constants.MatchType.LIVE_NOW))
                binding.itemLayout.setBackgroundResource(R.drawable.draw_title_red)
            else if (item.status.equals(Constants.MatchType.SCHEDULED)){
                states.text = Constants.MatchType.NOT_START.name

                binding.itemLayout.setBackgroundResource(R.drawable.draw_title_pink)
            }


        }
        super.onBindItemBinding(binding, item, position)

    }

    fun checkDate (date : String , type : Int) : String{

        if(type.equals(0)){
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)

            var data = inputFormat.parse(date)
            val timeFormat = SimpleDateFormat("HH:mm a")

            val resultTime = timeFormat.format(data)

            return resultTime
        }else if (type.equals(1)){
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

            var data = inputFormat.parse(date)
            val dateFormat = SimpleDateFormat("dd MMMM,yyyy")
            val resultDate =  dateFormat.format(data)

            return resultDate
        } else if (type.equals(2)){
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.US)

            var data = inputFormat.parse(date)
            val dayOfTheWeek =  android.text.format.DateFormat.format("EEEE", data)
            return dayOfTheWeek.toString()

        } else if (type.equals(3)){
            val inputFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.US)
            val currentDate = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(Date())

            var data = inputFormat.parse(date)
            val calendar1 = Calendar.getInstance(Locale.US);val calendar2 = Calendar.getInstance(Locale.US);
            calendar1.time = inputFormat.parse(currentDate)!!

            calendar2.time = data!!
            val week1: Int = calendar1.get(Calendar.WEEK_OF_YEAR)
            val week2: Int = calendar2.get(Calendar.WEEK_OF_YEAR)
            return "${adapterContext.resources.getString(R.string.week)} $week2"

        }else
            return  ""
    }

}
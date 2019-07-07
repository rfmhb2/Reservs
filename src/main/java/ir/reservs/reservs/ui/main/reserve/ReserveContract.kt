package ir.reservs.reservs.ui.main.reserve;

import android.content.Context
import ir.reservs.reservs.model.Day
import ir.reservs.reservs.model.Salon
import ir.reservs.reservs.model.Time
import ir.reservs.reservs.ui.base.BaseContract;

class ReserveContract {
    interface View : BaseContract.View {
        fun initializeViews(name: String, phone: String, time: String)
        fun getContext(): Context?
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun initialize(salon: Salon, time: Time, day: Day)
        fun payment()
    }
}

package ir.reservs.reservs.ui.base;

class BaseContract {
    interface View {
        fun onError(msg: String)
        fun onError(resId: Int)
        fun onTokenExpire()
    }

    interface Presenter<T> {
        fun onAttach(view: T)
        fun onDetach()
    }
}
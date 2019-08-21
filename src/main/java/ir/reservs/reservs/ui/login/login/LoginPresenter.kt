package ir.reservs.reservs.ui.login.login;

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import ir.reservs.reservs.R
import ir.reservs.reservs.data.DataManager
import ir.reservs.reservs.model.User
import ir.reservs.reservs.ui.base.BaseFragmentContract
import ir.reservs.reservs.utils.CommonUtils
import ir.reservs.reservs.utils.RetrofitError

class LoginPresenter(val dataManager: DataManager, val compositeDisposable: CompositeDisposable) : LoginContract.Presenter {

    private var view: LoginContract.View? = null

    private fun saveUser(user: User) {
        dataManager.setAccessToken(user.token)
        dataManager.setCurrentUserName(user.name)
        dataManager.setCurrentUserPhone(user.phone)
        dataManager.setCurrentUserImage(user.image)
    }


    fun login(phone: String, password: String, fcmToken: String?) {
        if (phone.isEmpty()) {
            view?.onError(R.string.empty_phone)
            return
        }
        if (!CommonUtils.isPhoneValid(phone)) {
            view?.onError(R.string.invalid_phone)
            return
        }
        if (password.isEmpty()) {
            view?.onError(R.string.empty_password)
            return
        }
        view?.showProgress()
        var token = ""
        if (fcmToken != null) token = fcmToken

        compositeDisposable.add(dataManager
                .login(phone, password, token)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ user ->
                    saveUser(user)
                    view?.hideProgress()
                    view?.openMainActivity()
                }, { error ->
                    RetrofitError.handle(view as BaseFragmentContract.View, error)
                    view?.hideProgress()
                }))
    }

    override fun onAttach(view: LoginContract.View) {
        this.view = view
    }


    override fun onDetach() {
        view = null
        if (!compositeDisposable.isDisposed) {
            compositeDisposable.dispose()
        }
    }
}

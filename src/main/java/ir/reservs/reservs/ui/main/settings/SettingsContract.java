package ir.reservs.reservs.ui.main.settings;

import ir.reservs.reservs.ui.base.BasePresenter;
import ir.reservs.reservs.ui.base.BaseView;

public class SettingsContract {
    interface View extends BaseView {
        void setUserInfo(String name, String phone, String image);
    }

    interface Presenter extends BasePresenter<View> {
        void logoutUser();
    }
}

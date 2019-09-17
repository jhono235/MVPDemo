package examples.aaronhoskins.com.mvpdemo.view.activities.mainactivity;

import android.util.Log;

import examples.aaronhoskins.com.mvpdemo.model.datasource.remote.retrofit.RetrofitHelper;
import examples.aaronhoskins.com.mvpdemo.model.randomuser.RandomUserResponse;
import examples.aaronhoskins.com.mvpdemo.view.adapters.RandomUserRvAdapter;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter {
    MainActivityContract contract;

    public MainActivityPresenter(MainActivityContract contract) {
        this.contract = contract;
    }

    public void getUserInfo(String numOfResults, String gender) {
        RetrofitHelper retrofitHelper = new RetrofitHelper();
        retrofitHelper.getService()
                .getRandomUserResponse(numOfResults, gender)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<RandomUserResponse>() {
                    RandomUserResponse randomUserResponse;
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(RandomUserResponse randomUserResponse) {
                        this.randomUserResponse = randomUserResponse;
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG_PRESENTER_MAIN", "ERROR IN API CALL -->" , e);
                    }

                    @Override
                    public void onComplete() {
                        setUpAdapter(randomUserResponse);
                    }
                });
    }

    public void setUpAdapter(RandomUserResponse randomUserResponse) {
        RandomUserRvAdapter randomUserRvAdapter = new RandomUserRvAdapter(randomUserResponse.getResults());
        contract.onAdapterReady(randomUserRvAdapter);
    }
}

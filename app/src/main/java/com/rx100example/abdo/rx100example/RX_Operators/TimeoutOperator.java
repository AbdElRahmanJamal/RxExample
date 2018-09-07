package com.rx100example.abdo.rx100example.RX_Operators;

import android.util.Log;
import com.rx100example.abdo.rx100example.model.Player;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

//allows you to abort an Observable with an
// onError termination if that Observable fails to emit any items during a specified span of time

//********* if we are dealing with requests to network resources
//we might want to configure some timeouts,
// so if the server takes so long to process a request, we can take
// specific actions, instead of hang forever
public class TimeoutOperator {
    //return TimeoutException If the next item is not emitted within the duration informed in the parameter
    public static void timeoutOperatorWithDuration(Observable<Player> playerObservable) {
        playerObservable.delay(2, TimeUnit.SECONDS)
                .timeout(1000, TimeUnit.MILLISECONDS)
                .onErrorReturn(error -> {
                    Log.d("output: ", error.toString());
                    return new Player("NON", "NON", "NON");
                })
                .subscribe(player -> {
                    Log.d("output: ", player.toString());
                });
    }

    //will trigger a second observable If the next item is not emitted within the duration informed in the parameter
    //if 1st Observable not emitted all its item before the duration informed in the parameter
    //it will return TimeoutException and then the 2nd Observable will triggered
    //basically  instead of terminate with an error  -> terminate with Other observable
    public static void timeoutOperatorWithSecondObservable(Observable<Player> playerObservable) {
        playerObservable.delay(2, TimeUnit.SECONDS)
                .timeout(1000, TimeUnit.MILLISECONDS, playerObservable.filter(player -> {
                    return player.getPosition().equals("Forward");
                }))
                .subscribe(player -> {
                    Log.d("output: ", player.toString());
                });
    }

  //create an Observable that emits a particular item after a given delay
  public static void timerOperator(Observable<Player> playerObservable) {
    Observable.timer(2, TimeUnit.SECONDS)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(new Observer<Long>() {
          @Override public void onSubscribe(Disposable d) {
            d = playerObservable.subscribe(player -> Log.d("output: ", player.toString()));
          }

          @Override public void onNext(Long aLong) {
            Log.d("output: ", aLong.toString());
          }

          @Override public void onError(Throwable e) {
            Log.d("output: ", e.toString());
          }

          @Override public void onComplete() {
            Log.d("output: ", "DONE");
          }
        });
  }


}

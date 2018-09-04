package com.rx100example.abdo.rx100example.RX_Operators;

import android.util.Log;

import com.rx100example.abdo.rx100example.model.Player;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

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
                    Log.d("onCreate: ", error.toString());
                    return new Player("NON", "NON", "NON");
                })
                .subscribe(player -> {
                    Log.d("onCreate: ", player.toString());
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
                    Log.d("onCreate: ", player.toString());
                });
    }

}

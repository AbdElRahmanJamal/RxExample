package com.rx100example.abdo.rx100example.RX_Operators;

import android.content.res.Resources;
import android.util.Log;

import com.rx100example.abdo.rx100example.model.Player;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class TransformingOperators {
    //it will divide source observable  into 10 and then return observable with this 10 items every time
    //EX: source observable [1,2,3,4,5,6,7,8,9] and buffer(4) so will get when running this example
    //[1,2,3,4] , [5,6,7,8] , [9]
    public static void bufferWithCountOperation(Observable<Player> playerObservable) {
        playerObservable.buffer(10).subscribe(aBoolean -> {
                    Log.d("output: ", aBoolean.toString());
                }
        );
    }

    //it will divide source observable  into 10 and then return observable with this 10 items every time
    //EX: source observable [1,2,3,4,5,6,7,8,9] and buffer(4) so will get when running this example
    //after 10 MILLISECONDS [1,2,3,4] , after 20 MILLISECONDS[5,6,7,8] , after 30 MILLISECONDS[9]
    public static void bufferWithTimeSpanCountOperation(Observable<Player> playerObservable) {
        playerObservable.buffer(10, TimeUnit.SECONDS, 4).subscribe(aBoolean -> {
                    Log.d("output: ", aBoolean.toString());
                }
        );
    }

    //retry() resubscribes when it receives onError().
    //repeat() resubscribes when it receives onCompleted().
    public static void retryWhenOperation(Observable<Player> playerObservable) {
        playerObservable.filter(player -> {
            return player.getPosition().equals("GK");
        }).retryWhen(errors -> errors.flatMap(error -> {
            // For IOExceptions, we  retry
            if (error instanceof Resources.NotFoundException) {
                Log.d("output: ", error.getMessage());
                return Observable.just(error);
            }
            // For anything else, don't retry
            return Observable.error(error);
        })).subscribe(player -> {
                    Log.d("output: ", player.toString());});
    }
}

package com.rx100example.abdo.rx100example.RX_Operators;

import android.util.Log;

import com.rx100example.abdo.rx100example.model.Player;

import io.reactivex.Observable;

public class FlatMapConcatMap {
    //the main different between flatMap and concatMap is order
    public static void flatMapOperation(Observable<Player> playerObservable) {
        playerObservable.flatMap(player -> {
            return Observable.just(player.getName());
        }).subscribe(player -> {
            Log.d("output: ", player.toString());
        });
    }

    public static void concatMapMapOperation(Observable<Player> playerObservable) {
        playerObservable.concatMap(player -> {
            return Observable.just(player.getName());
        }).subscribe(player -> {
            Log.d("output: ", player.toString());
        });
    }
}

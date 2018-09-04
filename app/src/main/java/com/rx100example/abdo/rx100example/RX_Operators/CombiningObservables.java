package com.rx100example.abdo.rx100example.RX_Operators;

import android.util.Log;

import com.rx100example.abdo.rx100example.model.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class CombiningObservables {
    //merge 2 Observable it will be like it 1st [1,2,3] 2nd Observable [5,6] merged Observable will be like [1,2,3,5,6]
    public static void mergeOperation(Observable<Player> playerObservable) {
        Observable<Player> playerGK = playerObservable.filter(player -> player.getPosition().equals("GK")).delay(2, TimeUnit.SECONDS);
        Observable<Player> playerForward = playerObservable.filter(player -> player.getPosition().equals("Forward")).delay(3, TimeUnit.SECONDS);
        List<Observable<Player>> observables = new ArrayList<>();
        observables.add(playerGK);
        observables.add(playerForward);
        Observable.merge(observables).subscribe(player -> {
            Log.d("onCreate: ", player.toString());
        });
    }

    //zip 2 Observable it will be like it 1st [1,2,3] 2nd Observable [5,6] zip Observable will be like [1,5][2,6]
    public static void zipOperation(Observable<Player> playerObservable) {
        Observable<Player> playerGK = playerObservable.filter(player -> player.getPosition().equals("GK"));
        Observable<Player> playerForward = playerObservable.filter(player -> player.getPosition().equals("Forward"));
        Observable.zip(playerForward, playerGK, (GK, forward) -> {
            return new Player(GK.toString(), "  ", forward.toString());
        }).subscribe(player -> {
            Log.d("onCreate: ", player.toString());
        });
    }

    //combines the most recently emitted items from each of the other source Observables
    //1st Observable[1,2,3] 2nd Observable [5,6] combine Observable will be like [3,6]
    public static void combineLatestOperation(Observable<Player> playerObservable) {
        Observable<Player> playerGK = playerObservable.filter(player -> player.getPosition().equals("GK")).delay(3, TimeUnit.SECONDS);
        Observable<Player> playerForward = playerObservable.filter(player -> player.getPosition().equals("Forward")).delay(4, TimeUnit.SECONDS);
        Observable.combineLatest(playerForward, playerGK, (GK, forward) -> {
            return new Player(GK.toString(), "  ", forward.toString());
        }).subscribe(player -> {
            Log.d("onCreate: ", player.toString());
        });
    }

    //When a new Observable is emitted, switchOnNext stops emitting items from
   // the earlier-emitted Observable and begins emitting items from the new one.
    public static void switchOnNextOperation(Observable<Player> playerObservable) {
        Observable<Player> playerGK = playerObservable.filter(player -> player.getPosition().equals("GK")).delay(2, TimeUnit.SECONDS);
        Observable<Player> playerForward = playerObservable.filter(player -> player.getPosition().equals("Forward")).delay(5, TimeUnit.SECONDS);
        Observable.combineLatest(playerForward, playerGK, (GK, forward) -> {
            return new Player(GK.toString(), "  ", forward.toString());
        }).subscribe(player -> {
            Log.d("onCreate: ", player.toString());
        });
    }

}

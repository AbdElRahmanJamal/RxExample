package com.rx100example.abdo.rx100example.RX_Operators;

import android.util.Log;

import com.rx100example.abdo.rx100example.model.Player;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class ConditionalOperators {
    //operator skips items emitted from the source while a certain condition is true
    public static void skipWhileOperation(Observable<Player> playerObservable) {
        playerObservable.skipWhile(player -> {
            boolean shouldSkip;
            if (player.getPosition().equals("GK")) {
                shouldSkip = true;
            } else {
                shouldSkip = false;
            }
            return shouldSkip;
        }).subscribe(player -> {
            Log.d("output: ", player.toString());
        });
    }

    //mb() operator can have multiple source observables,
    // but will emit all items from ONLY the first of these Observables
    // All the others will be discarded
    public static void ambOperation(Observable<Player> playerObservable) {
        Observable<Player> playerGK = playerObservable.filter(player -> player.getPosition().equals("GK")).delay(5, TimeUnit.SECONDS);
        Observable<Player> playerForward = playerObservable.filter(player -> player.getPosition().equals("Forward")).delay(4, TimeUnit.SECONDS);
        Observable.ambArray(playerForward, playerGK).subscribe(player -> {
            Log.d("output: ", player.toString());
        });
    }

    //All() operator is one of the operators that makes a boolean evaluation over the emitted items
    //EX: if observable like this [1,2,3,4,5] and we interested in even number All() operator will return false
    //EX: if observable like this [2,4,6,8] and we interested in even number All() operator will return true
    public static void allOperation(Observable<Player> playerObservable) {
        //will return true
        Observable<Player> playerGK = playerObservable.filter(player -> player.getPosition().equals("GK"));
        playerGK.all(player -> {
            return player.getPosition().equals("GK");
        }).subscribe(aBoolean -> {
                    Log.d("output: ", aBoolean.toString());
                }
        );
        //will return false
        Observable<Player> playerFW = playerObservable.filter(player -> player.getPosition().equals("Forward"));
        playerFW.all(player -> {
            return player.getPosition().equals("GK"); //base on this condition return true or false
        }).subscribe(aBoolean -> {
                    Log.d("output: ", aBoolean.toString());
                }
        );
    }

    public static void containsOperation(Observable<Player> playerObservable) {
        //return false
        Player player = new Player("Liverpool", "Abdo", "FW");
        playerObservable.contains(player.getName().equals("")).subscribe(aBoolean -> {
                    Log.d("output: ", aBoolean.toString());
                }
        );
        //return true
        Player player1 = new Player("Liverpool", "Georginio Wijnaldum", "Midfielder");
        playerObservable.contains(player1).subscribe(aBoolean -> {
                    Log.d("output: ", aBoolean.toString());
                }
        );
    }

}

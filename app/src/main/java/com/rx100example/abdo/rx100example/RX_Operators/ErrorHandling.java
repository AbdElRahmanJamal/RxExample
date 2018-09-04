package com.rx100example.abdo.rx100example.RX_Operators;

import android.util.Log;

import com.rx100example.abdo.rx100example.model.Player;

import java.util.NoSuchElementException;

import io.reactivex.Observable;

public class ErrorHandling {
    //return NoSuchElementException if karius  in GK players
    public static void errorHandling(Observable<Player> playerObservable) {
        playerObservable
                .filter(player -> player.getPosition().equals("GK"))
                .map(filteredPlayer -> {
                    if (!filteredPlayer.getName().equals("karius")) {
                        return new NoSuchElementException();
                    } else {
                        return filteredPlayer;
                    }
                })
                .onErrorReturn(throwable -> {
                    return throwable;
                }).subscribe(player -> {
            Log.d("outputs: ", player.toString());
        });
    }

    public static void errorHandlingReturnEmptyObjectIfExceptionThrown(Observable<Player> playerObservable) {
        playerObservable
                .filter(player -> player.getPosition().equals("GK"))
                .map(filteredPlayer -> {
                    if (!filteredPlayer.getName().equals("karius")) {
                        throw new NoSuchElementException();
                    } else {
                        return filteredPlayer;
                    }
                })
                .onErrorReturn(throwable -> {
                    return new Player();
                }).subscribe(player -> {
            Log.d("outputs: ", player.toString());
        });
    }
}

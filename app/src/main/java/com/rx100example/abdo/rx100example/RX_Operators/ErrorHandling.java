package com.rx100example.abdo.rx100example.RX_Operators;

import android.content.res.Resources;
import android.util.Log;
import com.rx100example.abdo.rx100example.model.Player;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import java.util.NoSuchElementException;

public class ErrorHandling {
  //return NoSuchElementException if karius  in GK players
  public static void errorHandling(Observable<Player> playerObservable) {
    playerObservable.filter(player -> player.getPosition().equals("GK"))
        .flatMap(ErrorHandling::emitError)
        .subscribe(player -> Log.d("outputs: ", player.toString()));
  }

  private static ObservableSource<Player> emitError(Player filteredPlayer) {
    if (!filteredPlayer.getName().equals("karius")) {
      return Observable.error(new NoSuchElementException());
    } else {
      return Observable.just(filteredPlayer);
    }
  }

  public static void errorHandlingReturnEmptyObjectIfExceptionThrown(
      Observable<Player> playerObservable) {
    playerObservable.filter(player -> player.getPosition().equals("GK"))
        .flatMap(ErrorHandling::emitError)
        .onErrorReturn(throwable -> Player.defaultInstance())
        .subscribe(player -> {
      Log.d("outputs: ", player.toString());
    });
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

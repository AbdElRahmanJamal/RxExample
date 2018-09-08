package com.rx100example.abdo.rx100example.RX_Operators;

import android.util.Log;
import com.rx100example.abdo.rx100example.model.Player;
import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CreatingObservables {
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

  //create an Observable from scratch by means of a function
  public static void createOperator(List<Player> playerList) {
    Observable.create((ObservableOnSubscribe<List<Player>>)
        emitter -> {
          emitter.onNext(playerList);
          emitter.onComplete();
        }).subscribe(player -> Log.d("output: ", player.toString()));
  }

  //Create an Observable that emits a range of sequential integers
  //start n and count m then it will create range from n to n+m+1
  public static void rangeOperator(List<Player> playerList, int start, int end) {
    Observable.range(start, end - start)
        .map(indexOfPlayer -> playerList.get(indexOfPlayer))
        .subscribe(player -> Log.d("output: ", player.toString()));
  }

  //convert an object or a set of objects into an Observable that emits that or those objects
  public static void justOperator(List<Player> playerList) {
    Observable.just(playerList)
        .flatMap(players -> Observable.fromIterable(playerList))
        .subscribe(player -> Log.d("output: ", player.toString()));
  }

  //create an Observable that emits a sequence of integers spaced by a given time interval
  //emits an infinite sequence of ascending integers, with a constant interval of time of your choosing between emissions.
  public static void IntervalOperator(List<Player> playerList) {
    Observable.interval(1, TimeUnit.MICROSECONDS)
        .doOnNext(number -> {
          if (number >= playerList.size() - 1) {
            new ArrayIndexOutOfBoundsException();
          }
        })
        .map(index -> {
          return playerList.get(index.intValue());
        })
        .onErrorReturn(throwable -> {
          return new Player();
        })
        .subscribe(player -> Log.d("output: ", player.toString()));
  }

  //create an Observable that emits a sequence of integers spaced by a given time interval
  //emits an range  sequence of ascending integers, with a constant interval of time of your choosing between emissions.
  //EX intervalRange it is combination between interval and range
  public static void IntervalRangeOperator(List<Player> playerList, int start, int end) {
    Observable.intervalRange(start, start + end, 0, 1, TimeUnit.SECONDS)
        .doOnNext(index -> {
          if (index >= playerList.size() - 1) {
            new ArrayIndexOutOfBoundsException();
          }
        })
        .map(index -> {
          return playerList.get(index.intValue());
        })
        .onErrorReturn(throwable -> new Player())
        .subscribe(player -> Log.d("output: ", player.toString()));
  }

  //do not create the Observable until the observer subscribes,
  // and create a fresh Observable for each observer
  //just and fromArray creation tools store the value of data when created, not when subscribed
  //defer   creation tools store the value of data when subscribed
  //defer() is that it creates a new Observable each time you get a subscriber.
  // create() can use the same function for each subscriber, so it's more efficient.
  public static void deferOperator(List<Player> playerList) {
    Observable.defer(() -> Observable.just(playerList))
        .subscribe(player -> Log.d("output: ", player.toString()));
  }
}

package com.rx100example.abdo.rx100example.RX_Operators;

import android.util.Log;
import com.rx100example.abdo.rx100example.model.Player;
import com.rx100example.abdo.rx100example.model.PlayerGroup;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TransformingOperators {
    //it will divide source observable  into 10 and then return observable with this 10 items every time
    //EX: source observable [1,2,3,4,5,6,7,8,9] and buffer(4) so will get when running this example
    //[1,2,3,4] , [5,6,7,8] , [9]
    public static void bufferWithCountOperation(Observable<Player> playerObservable) {
        playerObservable.buffer(10).subscribe(playerList -> {
            Log.d("output: ", playerList.toString());
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

    // divide an Observable into a set of Observables that each emit a different group
    // of items from the original Observable, organized by key
    public static Observable<List<PlayerGroup>> groupByOperation(Observable<Player> playerObservable) {
        return playerObservable
            .groupBy(Player::getPosition)
            .flatMap(playerPositionGroup -> {
                String playerPositionGroupKey = playerPositionGroup.getKey();
                return Observable.zip(
                    Observable.just(playerPositionGroupKey),
                    playerPositionGroup.toList().toObservable(),
                    PlayerGroup::new);
            })
            .toList().toObservable();
    }

    //transform the items emitted by an Observable by applying a function to each item
    //mao like flatMap but map return object and flatMap return Observable
    public static void mapOperation(Observable<Player> playerObservable) {
        playerObservable.map(player ->
            player.getName()
        ).subscribe(player -> {
            Log.d("output: ", player.toString());
        });
    }
    //the main different between flatMap and concatMap is order
    public static void flatMapOperation(Observable<Player> playerObservable) {
        playerObservable.flatMap(player ->
            Observable.just(player.getName())
        ).subscribe(player -> {
            Log.d("output: ", player.toString());
        });
    }
    //FlatMap may interleave emissions of Observables while ConcatMap will save order of emissions
    public static void concatMapMapOperation(Observable<Player> playerObservable) {
        playerObservable.concatMap(player ->
            Observable.just(player.getName())
        ).subscribe(player -> {
            Log.d("output: ", player.toString());
        });
    }

    //****scan operator is sometimes called an "ACCUMULATOR”
    //apply a function to each item emitted by an Observable, sequentially, and emit each successive value
    //this mean if i have observable like [1,2,3,4,5] if i use scan the output will be
    // accumulated like [1,3,6,10,15]
    public static void scanOperation() {
        ArrayList<Integer> playersPower = new ArrayList<>();
        playersPower.add(10);
        playersPower.add(20);
        playersPower.add(30);
        playersPower.add(40);
        playersPower.add(50);
        playersPower.add(60);
        playersPower.add(70);
        playersPower.add(80);
        playersPower.add(90);
        Observable.fromIterable(playersPower)
            .scan((accumulator, power) -> power + accumulator)
            .subscribe(accumulator -> {
                Log.d("output: ", accumulator.toString());
            });
    }

    //window Operation 
    //**periodically subdivide items from an Observable into Observable windows
    // and emit these windows rather than emitting the items one at a time

    // **Window is similar to Buffer, but rather than emitting packets of items from the source Observable, it emits Observables,
    // each one of which emits a subset of items from the source Observable and then terminates with an onCompleted notification.
    //buffer return items like this [1,2,3,4],[5,6,7,8]
    //window return observable of items

}

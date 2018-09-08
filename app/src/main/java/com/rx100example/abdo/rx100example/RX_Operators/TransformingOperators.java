package com.rx100example.abdo.rx100example.RX_Operators;

import android.content.res.Resources;
import android.util.Log;
import com.rx100example.abdo.rx100example.model.Player;
import com.rx100example.abdo.rx100example.model.PlayerGroup;
import io.reactivex.Observable;
import java.util.List;
import java.util.concurrent.TimeUnit;

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

    //the main different between flatMap and concatMap is order
    public static void flatMapOperation(Observable<Player> playerObservable) {
        playerObservable.flatMap(player -> {
            return Observable.just(player.getName());
        }).subscribe(player -> {
            Log.d("output: ", player.toString());
        });
    }
    //FlatMap may interleave emissions of Observables while ConcatMap will save order of emissions

    public static void concatMapMapOperation(Observable<Player> playerObservable) {
        playerObservable.concatMap(player -> {
            return Observable.just(player.getName());
        }).subscribe(player -> {
            Log.d("output: ", player.toString());
        });
    }
}

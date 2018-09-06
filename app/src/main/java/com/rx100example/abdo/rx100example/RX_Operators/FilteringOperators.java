package com.rx100example.abdo.rx100example.RX_Operators;

import android.util.Log;
import com.rx100example.abdo.rx100example.model.Player;
import com.rx100example.abdo.rx100example.model.PlayerGroup;
import io.reactivex.Observable;
import java.util.List;

public class FilteringOperators {
    //return first element
    public static void firstElementOperation(Observable<Player> playerObservable) {
        playerObservable.firstElement().
            subscribe(player -> Log.d("output: ", player.toString()));
    }

    //get first player with specific name
    public static void filterWithFirstOperationWithFunction(Observable<Player> playerObservable) {
        playerObservable.filter(player -> player.getName().equals("Alisson Becker")).firstElement().subscribe(
                player -> {
                    Log.d("output: ", player.toString());
                }
        );
    }

    //to return list
    public static List<Player> filterOperationWithFunction(Observable<Player> playerObservable) {
        return playerObservable.filter(player -> player.getPosition().equals("Defenders"))
                .onErrorReturn(throwable -> new Player())
                .toList().blockingGet();
    }

    //take first 3 elements and last 3 elements
    public static void takeOperationWithFunction(Observable<Player> playerObservable) {
        playerObservable.take(3).subscribe(player -> Log.d("output: ", player.toString()));
        playerObservable.takeLast(3).subscribe(player -> Log.d("output: ", player.toString()));
    }

    //check if Observable has one element or not if have one it will work correctly
    // if have more will throw exception
    public static void singleOperationWithFunction(Observable<Player> playerObservable) {
        playerObservable.singleElement()//TODO: to be discussed
                .onErrorReturn(throwable -> {
                    Log.d("output: ", "Sequence contains no elements ");
                    return new Player("__", "NON", "__");
                })
                .subscribe(
                        player -> {
                            Log.d("output: ", player.toString());
                        }
                );
    }

    //get first player with specific name
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
}

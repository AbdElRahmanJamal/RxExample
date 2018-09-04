package com.rx100example.abdo.rx100example;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.rx100example.abdo.rx100example.model.Player;

import java.util.ArrayList;

import io.reactivex.Observable;

import static com.rx100example.abdo.rx100example.RX_Operators.ErrorHandling.errorHandlingReturnEmptyObjectIfExceptionThrown;

public class MainActivity extends AppCompatActivity {
    ArrayList<Player> playerList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Observable<Player> playerObservable = createLiverpoolTeamPlayers();
//        FilteringOperators.firstElementOperation(playerObservable);
//        FilteringOperators.filterWithFirstOperationWithFunction(playerObservable); //filterOpeation
//         Log.d( "output: ",FilteringOperators.filterOperationWithFunction(playerObservable).size()+"");
//        FilteringOperators.takeOperationWithFunction(playerObservable);
//        FilteringOperators.singleOperationWithFunction(playerObservable);
        //CombiningObservables.mergeOperation(playerObservable);
        //zipOperation(playerObservable);
        //combineLatestOperation(playerObservable);
        //switchOnNextOperation(playerObservable);
        // skipWhileOperation(playerObservable);
        //allOperation(playerObservable);
//        containsOperation(playerObservable);
        //bufferWithCountOperation(playerObservable);
        //bufferWithTimeSpanOperation(playerObservable);
        // bufferWithTimeSpanCountOperation(playerObservable);
        //retryWhenOperation(playerObservable);
        //timeoutOperatorWithDuration(playerObservable);
        //timeoutOperatorWithSecondObservable(playerObservable);
//        groupByOperation(playerObservable)
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(playerGroups -> {
//                    Log.d("output: ", playerGroups.get(2).getPosition() + " " +
//                            playerGroups.get(2).getPlayers());
//                }, throwable -> {
//                    Log.e("output: ", throwable.toString());
//                });
        //errorHandling(playerObservable);
        errorHandlingReturnEmptyObjectIfExceptionThrown(playerObservable);
    }

    private Observable<Player> createLiverpoolTeamPlayers() {
        playerList.add(new Player("Liverpool", "Alisson Becker", "GK"));
        playerList.add(new Player("Liverpool", "Simon Mignolet", "GK"));
        playerList.add(new Player("Liverpool", "Caoimhin Kelleher", "GK"));
        playerList.add(new Player("Liverpool", "Nathaniel Clyne", "Defender"));
        playerList.add(new Player("Liverpool", "Virgil van Dijk", "Defender"));
        playerList.add(new Player("Liverpool", "Dejan Lovren", "Defender"));
        playerList.add(new Player("Liverpool", "Joe Gomez", "Defender"));
        playerList.add(new Player("Liverpool", "Alberto Moreno", "Defender"));
        playerList.add(new Player("Liverpool", "Andrew Robertson", "Defender"));
        playerList.add(new Player("Liverpool", "Joël Matip", "Defender"));
        playerList.add(new Player("Liverpool", "Trent Alexander-Arnold", "Defender"));
        playerList.add(new Player("Liverpool", "Fabinho", "Midfielder"));
        playerList.add(new Player("Liverpool", "Georginio Wijnaldum", "Midfielder"));
        playerList.add(new Player("Liverpool", "James Milner", "Midfielder"));
        playerList.add(new Player("Liverpool", "Naby Keïta", "Midfielder"));
        playerList.add(new Player("Liverpool", "Jordan Henderson", "Midfielder"));
        playerList.add(new Player("Liverpool", "Adam Lallana", "Midfielder"));
        playerList.add(new Player("Liverpool", "Alex Oxlade-Chamberlain", "Midfielder"));
        playerList.add(new Player("Liverpool", "Xherdan Shaqiri", "Midfielder"));
        playerList.add(new Player("Liverpool", "Sheyi Ojo", "Midfielder"));
        playerList.add(new Player("Liverpool", "Roberto Firmino", "Forward"));
        playerList.add(new Player("Liverpool", "Sadio Mané", "Forward"));
        playerList.add(new Player("Liverpool", "Mohamed Salah", "Forward"));
        playerList.add(new Player("Liverpool", "Daniel Sturridge", "Forward"));
        playerList.add(new Player("Liverpool", "Rhian Brewster", "Forward"));
        playerList.add(new Player("Liverpool", "Divock Origi", "Forward"));
        playerList.add(new Player("Liverpool", "Dominic Solanke", "Forward"));
        return Observable.fromIterable(playerList);
    }

}

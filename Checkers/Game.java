package com.company;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Game
{
    int round = 0;
    int roundPlayedByWhiteBot = 0;
    int roundPlayedByBlackBot = 0;
    int roundPlayedByKing = 0;
    long whitePlayerTime = 0;
    long blackPlayerTime = 0;
    int visitedNodeForWhitePlayer = 0;
    int visitedNodesForBlackPlayer = 0;

    ValidMoves validMoves;
    BotActions botActions;

    public Game()
    {
        this.validMoves = new ValidMoves();
        this.botActions = new BotActions();
    }

    public void run(Player whitePlayer, Player blackPlayer, Board board, int scoreCountHeuristic)
    {
        board.setBoard(whitePlayer, blackPlayer);
        board.printBoard();
        Player currentPlayer;
        Player oppositePlayer;

        if(round %2 == 0)
        {
            currentPlayer = whitePlayer;
            oppositePlayer = blackPlayer;
        }
        else
        {
            currentPlayer = blackPlayer;
            oppositePlayer = whitePlayer;
        }

        System.out.println(currentPlayer.getName() + "'s move");

        int startX;
        int startY;
        int finishX;
        int finishY;

        Move destinationField;
        Pawn pawn;

        if(currentPlayer.isHuman())
        {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Write move coordinates: ");
            String input = scanner.next();

            if(!UserInput.isInputCorrect(input)){
                runAgain(whitePlayer, blackPlayer, board, true, scoreCountHeuristic);
                return;
            }
            startX = Character.getNumericValue(input.charAt(0));
            startY = Character.getNumericValue(input.charAt(1));
            finishX = Character.getNumericValue(input.charAt(2));
            finishY = Character.getNumericValue(input.charAt(3));

            pawn = getPawn(startX, startY, currentPlayer);
            if(pawn == null){
                runAgain(whitePlayer, blackPlayer, board, true, scoreCountHeuristic);
                return;
            }

            ArrayList<Move> allValidMoves = getValidMoves(currentPlayer, board, oppositePlayer);
            filterValidMoves(allValidMoves);

            if(allValidMoves.size() == 0){
                runAgain(whitePlayer, blackPlayer, board, true, scoreCountHeuristic);
                return;
            }

            destinationField = getDestinationField(startX, startY, finishX, finishY, allValidMoves);
            if(destinationField == null)
            {
                runAgain(whitePlayer, blackPlayer, board, true, scoreCountHeuristic);
                return;
            }
        }
        else
        {
            int searchDepth = ((BotPlayer) currentPlayer).getSearchDepth();
            long currentTime = System.currentTimeMillis();

            if(((BotPlayer) currentPlayer).isUseMinMax())
            {
                botActions.minMax(currentPlayer, oppositePlayer, searchDepth, scoreCountHeuristic, searchDepth);
                if(currentPlayer.isWhite()) visitedNodeForWhitePlayer += botActions.getVisitedNodesForMinMax();
                else visitedNodesForBlackPlayer += botActions.getVisitedNodesForMinMax();

                botActions.setVisitedNodesInMinMax(0);
            }
            else
            {
                botActions.alphaBetaPruning(currentPlayer, oppositePlayer, searchDepth, scoreCountHeuristic, searchDepth, Integer.MIN_VALUE, Integer.MAX_VALUE);
                if(currentPlayer.isWhite()) visitedNodeForWhitePlayer += botActions.getVisitedNodesForAlphaBeta();
                else visitedNodesForBlackPlayer += botActions.getVisitedNodesForAlphaBeta();

                botActions.setVisitedNodesForInAlphaBeta(0);
            }


            if(currentPlayer.isWhite()) whitePlayerTime += System.currentTimeMillis() - currentTime;
            else blackPlayerTime += System.currentTimeMillis() - currentTime;

            ArrayList<Move> validMoves = new ArrayList<>(botActions.getMoves());

            if(validMoves.size() == 0)
            {
                System.out.println(currentPlayer.getName()+ " has no more valid moves. Game is over.");
                return;
            }
            filterValidMovesByScore(validMoves, currentPlayer.isWhite());

            Random random = new Random();
            int randIndex = random.nextInt(validMoves.size());

            destinationField = validMoves.get(randIndex);
            System.out.println("Current score: " + destinationField.getScore());
            botActions.getMoves().clear();

            startX = Character.getNumericValue(destinationField.getStartCoordinates().charAt(0));
            startY = Character.getNumericValue(destinationField.getStartCoordinates().charAt(1));
            finishX = Character.getNumericValue(destinationField.getCoordinates().charAt(0));
            finishY = Character.getNumericValue(destinationField.getCoordinates().charAt(1));
            pawn = getPawn(startX, startY, currentPlayer);

            if(currentPlayer.isWhite()) roundPlayedByWhiteBot++;
            else roundPlayedByBlackBot++;
        }

        removeCapturedPawns(oppositePlayer, destinationField);

        pawn.setX(finishX);
        pawn.setY(finishY);

        setPawnKingIfItIsKing(currentPlayer, finishY, pawn);

        if(checkIfEndGame(whitePlayer, blackPlayer)){
            System.out.println("The game is over");
            findWinner(whitePlayer, blackPlayer);
            return;
        }

        if(pawn.isKing()) roundPlayedByKing++;
        else roundPlayedByKing=0;

        if(checkIfDraw(roundPlayedByKing)){
            System.out.println("It's a draw!");
            return;
        }
        round++;
        runAgain(whitePlayer, blackPlayer, board, false, scoreCountHeuristic);
    }

    public double getAverageTimeForMoveOfWhitePlayer()
    {
        if(roundPlayedByWhiteBot > 0)
            return (double)whitePlayerTime/roundPlayedByWhiteBot;
        else
            return 0;
    }
    public double getAverageTimeForMoveOfBlackPlayer()
    {
        if(roundPlayedByBlackBot > 0)
            return (double)blackPlayerTime/roundPlayedByBlackBot;
        else
            return 0;
    }

    public int getAverageNodeVisitedForWhitePlayer()
    {
        if(roundPlayedByWhiteBot > 0)
            return visitedNodeForWhitePlayer/roundPlayedByWhiteBot;
        else
            return 0;
    }

    public int getAverageNodeVisitedForBlackPlayer()
    {
        if(roundPlayedByBlackBot > 0)
            return visitedNodesForBlackPlayer/roundPlayedByBlackBot;
        else
            return 0;
    }

    private void setPawnKingIfItIsKing(Player currentPlayer, int finishY, Pawn pawn) {
        if(finishY == 1 && !currentPlayer.isWhite())
        {
            pawn.setKing(true);
        }

        if(finishY == 8 && currentPlayer.isWhite())
        {
            pawn.setKing(true);
        }
    }

    private void removeCapturedPawns(Player oppositePlayer, Move destinationField) {
        for(int i = 0; i < destinationField.capturedPawns.size(); i++)
        {
            int finalI = i;
            Pawn pawnToDestroy = oppositePlayer.getPawns().stream()
                    .filter(p -> p.getX() == destinationField.getCapturedPawns().get(finalI).getX()
                            && p.getY() == destinationField.getCapturedPawns().get(finalI).getY())
                    .findAny()
                    .orElse(null);
            if(pawnToDestroy != null)
            {
                oppositePlayer.getPawns().remove(pawnToDestroy);
            }
        }
    }

    private Move getDestinationField(int startX, int startY, int finishX, int finishY, ArrayList<Move> allValidMoves) {
        Move destinationField;
        destinationField = allValidMoves.stream()
                .filter(h -> h.getCoordinates().equals(String.valueOf(finishX) + finishY)
                        && h.getStartCoordinates().equals(String.valueOf(startX) + startY))
                .findAny()
                .orElse(null);
        return destinationField;
    }

    private void filterValidMoves(ArrayList<Move> allValidMoves) {
        int maxCapturedPawns = 0;
        for (Move move : allValidMoves)
        {
            if (move.getCapturedPawns().size() > maxCapturedPawns)
            {
                maxCapturedPawns = move.getCapturedPawns().size();
            }
        }

        int finalMaxCapturedPawns = maxCapturedPawns;
        allValidMoves.removeIf(h -> h.getCapturedPawns().size() != finalMaxCapturedPawns);
    }

    private void filterValidMovesByScore(ArrayList<Move> allValidMoves, boolean isWhite) {
        int score;
        if(isWhite)
        {
            score = Integer.MIN_VALUE;
        }
        else
        {
            score = Integer.MAX_VALUE;
        }
        for (Move move : allValidMoves)
        {
            if(isWhite)
            {
                if (move.getScore() > score)
                {
                    score = move.getScore();
                }
            }
            else
            {
                if (move.getScore() < score)
                {
                    score = move.getScore();
                }
            }
        }

        int finalScore = score;
        allValidMoves.removeIf(h -> h.getScore() != finalScore);
    }

    private ArrayList<Move> getValidMoves(Player currentPlayer, Board board, Player oppositePlayer) {
        ArrayList<Move> allValidMoves = new ArrayList<>();
        for(int i = 0; i < currentPlayer.getPawns().size(); i++)
        {
            validMoves.createPossibleMovesList(currentPlayer, currentPlayer.getPawns().get(i), board, allValidMoves, oppositePlayer);
        }
        return allValidMoves;
    }

    private Pawn getPawn(int x, int y, Player player)
    {
        return player.getPawns().stream()
                .filter(pawn -> pawn.getX() == x && pawn.getY() == y)
                .findAny()
                .orElse(null);
    }

    private void runAgain(Player whitePlayer, Player blackPlayer, Board board, boolean fail, int scoreCountHeuristic) {
        if(fail){
            System.out.println("Your move coordinates are wrong");
        }
        else {
            System.out.println("Next player turn");
        }
        run(whitePlayer, blackPlayer, board, scoreCountHeuristic);
    }
    private boolean checkIfEndGame(Player whitePlayer, Player blackPlayer)
    {
        return whitePlayer.getPawns().size() == 0 || blackPlayer.getPawns().size() == 0;
    }
    private void findWinner(Player whitePlayer, Player blackPlayer){
        if(whitePlayer.getPawns().size() == 0){
            System.out.println(blackPlayer.getName() + " won!");
        }
        else{
            System.out.println(whitePlayer.getName() + " won!");
        }

    }
    private boolean checkIfDraw(int playedByKing)
    {
        return playedByKing == 15;
    }
}

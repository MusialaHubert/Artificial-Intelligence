package com.company;

import java.util.ArrayList;

public class BotActions
{
    private final Score score = new Score();
    private final ValidMoves validMovesObject = new ValidMoves();
    private ArrayList<Move> moves = new ArrayList<>();
    private int visitedNodesInMinMax = 0;
    private int visitedNodesForInAlphaBeta = 0;

    public int minMax(Player player, Player oppositePlayer, int searchDepth, int heuristic, int depth)
    {
        if(searchDepth == 0 || player.getPawns().size() == 0 || oppositePlayer.getPawns().size() == 0)
        {
            int currentScore = switch (heuristic) {
                case 2 -> score.scoreWithKingPawns(player, oppositePlayer);
                case 3 -> score.scoreWithDistrictsAndEqualPawns(player, oppositePlayer);
                case 4 -> score.scoreWithDistrictsAndKingPawns(player, oppositePlayer);
                default -> score.scorePawnsAreEqual(player, oppositePlayer);
            };

            return currentScore;
        }

        Player newPlayer = prepareNewPlayer(player);
        Player newOppositePlayer = prepareNewPlayer(oppositePlayer);

        ArrayList<Move> validMoves = new ArrayList<>();
        Board board = new Board();
        board.setBoard(newPlayer, newOppositePlayer);

        for(int i = 0; i < newPlayer.getPawns().size(); i++)
        {
            validMovesObject.createPossibleMovesList(newPlayer, newPlayer.getPawns().get(i), board, validMoves, newOppositePlayer);
        }

        filterNewValidMoves(validMoves);

        if(newPlayer.isWhite())
        {
            int maxScore = Integer.MIN_VALUE;

            for (Move validMove : validMoves)
            {
                newPlayer = prepareNewPlayer(player);
                newOppositePlayer = prepareNewPlayer(oppositePlayer);

                makeMove(validMove, newPlayer, newOppositePlayer);
                int currentScore = minMax(newOppositePlayer, newPlayer, searchDepth - 1, heuristic, depth);
                if (currentScore > maxScore)
                {
                    maxScore = currentScore;
                }
                if(depth == searchDepth)
                {
                    validMove.setScore(currentScore);
                    moves.add(validMove);
                }
                visitedNodesInMinMax++;
            }
            return maxScore;
        }
        else
        {
            int minScore = Integer.MAX_VALUE;
            for (Move validMove : validMoves)
            {
                newPlayer = prepareNewPlayer(player);
                newOppositePlayer = prepareNewPlayer(oppositePlayer);

                makeMove(validMove, newPlayer, newOppositePlayer);
                int currentScore = minMax(newOppositePlayer, newPlayer, searchDepth - 1, heuristic, depth);
                if (currentScore < minScore)
                {
                    minScore = currentScore;
                }
                if(depth == searchDepth)
                {
                    validMove.setScore(currentScore);
                    moves.add(validMove);
                }
                visitedNodesInMinMax++;
            }
            return minScore;
        }
    }

    public int alphaBetaPruning(Player player, Player oppositePlayer, int searchDepth, int heuristic, int depth, int alpha, int beta)
    {
        if(searchDepth == 0 || player.getPawns().size() == 0 || oppositePlayer.getPawns().size() == 0)
        {
            int currentScore = switch (heuristic) {
                case 2 -> score.scoreWithKingPawns(player, oppositePlayer);
                case 3 -> score.scoreWithDistrictsAndEqualPawns(player, oppositePlayer);
                case 4 -> score.scoreWithDistrictsAndKingPawns(player, oppositePlayer);
                default -> score.scorePawnsAreEqual(player, oppositePlayer);
            };

            return currentScore;
        }

        Player newPlayer = prepareNewPlayer(player);
        Player newOppositePlayer = prepareNewPlayer(oppositePlayer);

        ArrayList<Move> validMoves = new ArrayList<>();
        Board board = new Board();
        board.setBoard(newPlayer, newOppositePlayer);

        for(int i = 0; i < newPlayer.getPawns().size(); i++)
        {
            validMovesObject.createPossibleMovesList(newPlayer, newPlayer.getPawns().get(i), board, validMoves, newOppositePlayer);
        }

        filterNewValidMoves(validMoves);

        if(newPlayer.isWhite())
        {
            int maxScore = Integer.MIN_VALUE;

            for (Move validMove : validMoves)
            {
                newPlayer = prepareNewPlayer(player);
                newOppositePlayer = prepareNewPlayer(oppositePlayer);

                makeMove(validMove, newPlayer, newOppositePlayer);
                int currentScore = alphaBetaPruning(newOppositePlayer, newPlayer, searchDepth - 1, heuristic, depth, alpha, beta);
                if (currentScore > maxScore)
                {
                    maxScore = currentScore;
                }
                if(depth == searchDepth)
                {
                    validMove.setScore(currentScore);
                    moves.add(validMove);
                }
                visitedNodesForInAlphaBeta++;

                if (alpha <= currentScore)
                {
                    alpha = currentScore;
                }

                if (beta <= alpha) {
                    break;
                }
            }
            return maxScore;
        }
        else
        {
            int minScore = Integer.MAX_VALUE;
            for (Move validMove : validMoves)
            {
                newPlayer = prepareNewPlayer(player);
                newOppositePlayer = prepareNewPlayer(oppositePlayer);

                makeMove(validMove, newPlayer, newOppositePlayer);
                int currentScore = alphaBetaPruning(newOppositePlayer, newPlayer, searchDepth - 1, heuristic, depth, alpha, beta);
                if (currentScore < minScore)
                {
                    minScore = currentScore;
                }
                if(depth == searchDepth)
                {
                    validMove.setScore(currentScore);
                    moves.add(validMove);
                }
                visitedNodesForInAlphaBeta++;

                if (beta >= currentScore)
                {
                    beta = currentScore;
                }

                if (beta <= alpha) {
                    break;
                }
            }
            return minScore;
        }
    }

    private void filterNewValidMoves(ArrayList<Move> newValidMoves)
    {
        int maxCapturedPawns = 0;
        for (Move move : newValidMoves)
        {
            if (move.getCapturedPawns().size() > maxCapturedPawns)
            {
                maxCapturedPawns = move.getCapturedPawns().size();
            }
        }

        int finalMaxCapturedPawns = maxCapturedPawns;
        newValidMoves.removeIf(h -> h.getCapturedPawns().size() != finalMaxCapturedPawns);
    }

    public int getVisitedNodesForAlphaBeta() {
        return visitedNodesForInAlphaBeta;
    }
    public int getVisitedNodesForMinMax() {
        return visitedNodesInMinMax;
    }

    public void setVisitedNodesInMinMax(int visitedNodesInMinMax) {
        this.visitedNodesInMinMax = visitedNodesInMinMax;
    }

    public void setVisitedNodesForInAlphaBeta(int visitedNodesForInAlphaBeta) {
        this.visitedNodesForInAlphaBeta = visitedNodesForInAlphaBeta;
    }

    private Player prepareNewPlayer(Player player) {
        Player newPlayer;
        if(player.isHuman()) newPlayer = new HumanPlayer(player.getName(), player.isWhite());
        else newPlayer = new BotPlayer(player.getName(), player.isWhite(), ((BotPlayer) player).isUseMinMax());

        newPlayer.getPawns().clear();
        for(int i = 0; i < player.getPawns().size(); i++)
        {
            Pawn oldPawn = player.getPawns().get(i);
            Pawn newPawn = new Pawn(oldPawn.getX(), oldPawn.getY(), oldPawn.isWhite(), oldPawn.isKing());
            newPlayer.getPawns().add(newPawn);
        }
        return newPlayer;
    }

    private Pawn makeMove(Move validMove, Player newPlayer, Player newOppositePlayer)
    {
        int startX = Character.getNumericValue(validMove.getStartCoordinates().charAt(0));
        int startY = Character.getNumericValue(validMove.getStartCoordinates().charAt(1));
        int finishX = Character.getNumericValue(validMove.getCoordinates().charAt(0));
        int finishY = Character.getNumericValue(validMove.getCoordinates().charAt(1));

        Pawn pawn = newPlayer.getPawns().stream()
                .filter(p -> p.getX() == startX && p.getY() == startY)
                .findAny()
                .orElse(null);

        pawn.setX(finishX);
        pawn.setY(finishY);

        if(finishY == 1 && !pawn.isWhite())
        {
            pawn.setKing(true);
        }

        if(finishY == 8 && pawn.isWhite())
        {
            pawn.setKing(true);
        }

        for(int j = 0; j < validMove.capturedPawns.size(); j++)
        {
            int finalJ = j;
            newOppositePlayer.getPawns().stream()
                    .filter(p -> p.getX() == validMove.getCapturedPawns().get(finalJ).getX()
                            && p.getY() == validMove.getCapturedPawns().get(finalJ).getY())
                    .findAny().ifPresent(pawnToDestroy -> newOppositePlayer.getPawns().remove(pawnToDestroy));
        }

        return pawn;
    }

    public ArrayList<Move> getMoves() {
        return moves;
    }

    public void setMoves(ArrayList<Move> moves) {
        this.moves = moves;
    }
}

package com.company;

import java.util.ArrayList;

public class ValidMoves
{
    public void createPossibleMovesList(Player player, Pawn pawn, Board board, ArrayList<Move> moves, Player oppositePlayer)
    {
        if(!pawn.isKing())
        {
            int yTop = pawn.getY()-1;
            int yBottom = pawn.getY()+1;
            int leftX = pawn.getX()-1;
            int rightX = pawn.getX()+1;

            findMovesWithBeating(player, board, moves, yTop, yBottom, leftX, rightX, pawn, oppositePlayer);

            if(player.isWhite())
            {
                findMovesWithoutBeating(pawn, board, moves, yBottom);
            }
            else
            {
                findMovesWithoutBeating(pawn, board, moves, yTop);
            }
        }
        else
        {
            findMovesWithBeatingForKing(pawn, board, moves, player, oppositePlayer);
            findMoveWithoutBeatingForKing(pawn, board, moves);
        }
    }
    private void findMovesWithBeatingForKing(Pawn pawn, Board board, ArrayList<Move> moves, Player currentPlayer, Player oppositePlayer)
    {
        if(pawn.getX() - 1 >= 1 && pawn.getY() - 1 >= 1)
        {
            int y = pawn.getY() - 1;
            int oppositePawnsInMyPath = 0;
            Pawn oppositePawn = null;

            for(int i = pawn.getX() - 1; i >= 1; i--)
            {
                Pawn nextPawn = getPawn(board, y, i);

                if(nextPawn != null && nextPawn.isWhite() == pawn.isWhite()) break;
                if(nextPawn != null && nextPawn.isWhite() != pawn.isWhite())
                {
                    oppositePawnsInMyPath++;
                    oppositePawn = nextPawn;
                }

                if(nextPawn == null && oppositePawnsInMyPath == 1){
                    makeBeatingWithKing(moves, y, i, pawn, oppositePawn, currentPlayer, oppositePlayer);
                }

                y--;
                if(y < 1) break;
            }
        }
        if(pawn.getX() + 1 <= 8 && pawn.getY() - 1 >= 1)
        {
            int y = pawn.getY() - 1;
            int oppositePawnsInMyPath = 0;
            Pawn oppositePawn = null;

            for(int i = pawn.getX() + 1; i <= 8; i++)
            {
                Pawn nextPawn = getPawn(board, y, i);

                if(nextPawn != null && nextPawn.isWhite() == pawn.isWhite()) break;
                if(nextPawn != null && nextPawn.isWhite() != pawn.isWhite())
                {
                    oppositePawnsInMyPath++;
                    oppositePawn = nextPawn;
                }

                if(nextPawn == null && oppositePawnsInMyPath == 1){
                    makeBeatingWithKing(moves, y, i, pawn, oppositePawn, currentPlayer, oppositePlayer);
                }

                y--;
                if(y < 1) break;
            }
        }
        if(pawn.getX() - 1 >= 1 && pawn.getY() + 1 <= 8)
        {
            int y = pawn.getY() + 1;
            int oppositePawnsInMyPath = 0;
            Pawn oppositePawn = null;

            for(int i = pawn.getX() - 1; i >= 1; i--)
            {
                Pawn nextPawn = getPawn(board, y, i);

                if(nextPawn != null && nextPawn.isWhite() == pawn.isWhite()) break;
                if(nextPawn != null && nextPawn.isWhite() != pawn.isWhite())
                {
                    oppositePawnsInMyPath++;
                    oppositePawn = nextPawn;
                }

                if(nextPawn == null && oppositePawnsInMyPath == 1){
                    makeBeatingWithKing(moves, y, i, pawn, oppositePawn, currentPlayer, oppositePlayer);
                }

                y++;
                if(y > 8) break;
            }
        }
        if(pawn.getX() + 1 <= 8 && pawn.getY() + 1 <= 8)
        {
            int y = pawn.getY() + 1;
            int oppositePawnsInMyPath = 0;
            Pawn oppositePawn = null;

            for(int i = pawn.getX() + 1; i <= 8; i++)
            {
                Pawn nextPawn = getPawn(board, y, i);

                if(nextPawn != null && nextPawn.isWhite() == pawn.isWhite()) break;
                if(nextPawn != null && nextPawn.isWhite() != pawn.isWhite())
                {
                    oppositePawnsInMyPath++;
                    oppositePawn = nextPawn;
                }

                if(nextPawn == null && oppositePawnsInMyPath == 1){
                    makeBeatingWithKing(moves, y, i, pawn, oppositePawn, currentPlayer, oppositePlayer);
                }


                y++;
                if(y > 8) break;
            }
        }
    }
    private void findMoveWithoutBeatingForKing(Pawn pawn, Board board, ArrayList<Move> moves)
    {
        if(pawn.getX() - 1 >= 1 && pawn.getY() - 1 >= 1)
        {
            int y = pawn.getY() - 1;
            for(int i = pawn.getX() - 1; i >= 1; i--)
            {
                Pawn nextPawn = getPawn(board, y, i);
                if(nextPawn != null) break;

                addKingMoveWithoutBeating(pawn, moves, i, y);

                y--;
                if(y < 1) break;
            }
        }
        if(pawn.getX() + 1 <= 8 && pawn.getY() - 1 >= 1)
        {
            int y = pawn.getY() - 1;
            for(int i = pawn.getX() + 1; i <= 8; i++)
            {
                Pawn nextPawn = getPawn(board, y, i);
                if(nextPawn != null) break;

                addKingMoveWithoutBeating(pawn, moves, i, y);

                y--;
                if(y < 1) break;
            }
        }
        if(pawn.getX() - 1 >= 1 && pawn.getY() + 1 <= 8)
        {
            int y = pawn.getY() + 1;
            for(int i = pawn.getX() - 1; i >= 1; i--)
            {
                Pawn nextPawn = getPawn(board, y, i);
                if(nextPawn != null) break;

                addKingMoveWithoutBeating(pawn, moves, i, y);

                y++;
                if(y > 8) break;
            }
        }
        if(pawn.getX() + 1 <= 8 && pawn.getY() + 1 <= 8)
        {
            int y = pawn.getY() + 1;
            for(int i = pawn.getX() + 1; i <= 8; i++)
            {
                Pawn nextPawn = getPawn(board, y, i);
                if(nextPawn != null) break;

                addKingMoveWithoutBeating(pawn, moves, i, y);

                y++;
                if(y > 8) break;
            }
        }
    }

    private void addKingMoveWithoutBeating(Pawn pawn, ArrayList<Move> moves, int i, int j)
    {
        Move move = new Move();
        move.setStartCoordinates(String.valueOf(pawn.getX()) + pawn.getY());
        move.setCoordinates(String.valueOf(i)+ j);
        moves.add(move);
    }

    private void findMovesWithBeating(Player player, Board board, ArrayList<Move> moves, int yTop, int yBottom,
                                      int leftX, int rightX, Pawn currentPawn, Player oppositePlayer)
    {
        if(leftX >= 1 && yBottom >= 1 && yBottom <= 8)
        {
            Pawn oppositePawn = getPawn(board, yBottom, leftX);

            if(oppositePawn != null && oppositePawn.isWhite() != player.isWhite())
            {
                if(leftX - 1 >= 1 && yBottom + 1 <= 8){
                    Pawn freePawn = getPawn(board, yBottom +1, leftX -1);

                    if(freePawn == null)
                    {
                        makeBeatingWithNoKing(moves, yBottom +1, leftX -1, currentPawn, oppositePawn, player, oppositePlayer);
                    }
                }
            }
        }
        if(leftX >= 1 && yTop >= 1 && yTop <= 8)
        {
            Pawn oppositePawn = getPawn(board, yTop, leftX);

            if(oppositePawn != null && oppositePawn.isWhite() != player.isWhite())
            {
                if(leftX - 1 >= 1 && yTop - 1 >= 1)
                {
                    Pawn freePawn = getPawn(board, yTop -1, leftX -1);

                    if(freePawn == null){
                        makeBeatingWithNoKing(moves, yTop -1, leftX -1, currentPawn, oppositePawn, player, oppositePlayer);
                    }
                }
            }
        }
        if(rightX <= 8 && yBottom >= 1 && yBottom <= 8)
        {
            Pawn oppositePawn = getPawn(board, yBottom, rightX);

            if(oppositePawn != null && oppositePawn.isWhite() != player.isWhite())
            {
                if(rightX + 1 <= 8 && yBottom + 1 <= 8)
                {
                    Pawn freePawn = getPawn(board, yBottom +1, rightX +1);
                    if(freePawn == null){
                        makeBeatingWithNoKing(moves, yBottom +1, rightX +1, currentPawn, oppositePawn, player, oppositePlayer);
                    }
                }
            }
        }
        if(rightX <= 8 && yTop >= 1 && yTop <= 8)
        {
            Pawn oppositePawn = getPawn(board, yTop, rightX);

            if(oppositePawn != null && oppositePawn.isWhite() != player.isWhite())
            {
                if(rightX + 1 <= 8 && yTop - 1 >= 1)
                {
                    Pawn freePawn = getPawn(board, yTop -1, rightX +1);
                    if(freePawn == null){
                        makeBeatingWithNoKing(moves, yTop -1, rightX +1, currentPawn, oppositePawn, player, oppositePlayer);
                    }
                }
            }
        }
    }

    private void findMovesWithoutBeating(Pawn pawn, Board board, ArrayList<Move> moves, int y)
    {
        int leftX = pawn.getX()-1;
        int rightX = pawn.getX()+1;

        if(leftX >= 1 && y >= 1 && y <= 8) findFreeField(board, moves, leftX, y, pawn);

        if(rightX <= 8 && y >= 1 && y <= 8) findFreeField(board, moves, rightX, y, pawn);
    }

    private Pawn getPawn(Board board, int y, int x)
    {
        return board.getBoard().stream()
                .filter(p1 -> p1.getX() == x && p1.getY() == y)
                .findAny()
                .orElse(null);
    }

    private void makeBeatingWithNoKing(ArrayList<Move> moves, int y, int x, Pawn playerPawn,
                                       Pawn oppositePawn, Player currentPlayer, Player oppositePlayer)
    {
        makeMove(moves, y, x, playerPawn, oppositePawn);

        Player newPlayer = copyCurrentPlayer(y, x, playerPawn, currentPlayer);

        Player newOppositePlayer = copyOppositePlayer(oppositePawn, oppositePlayer);

        Board newBoard = copyBoard(newPlayer, newOppositePlayer);

        Pawn newPawn = newPlayer.getPawns().stream()
                .filter(p1 -> p1.getX() == x && p1.getY() == y)
                .findAny()
                .orElse(null);

        if(newPawn == null) return;

        findMovesWithBeating(newPlayer, newBoard, moves, y - 1, y + 1, x - 1, x + 1, newPawn, newOppositePlayer);
    }

    private void makeBeatingWithKing(ArrayList<Move> moves, int y, int x, Pawn playerPawn,
                                     Pawn oppositePawn, Player currentPlayer, Player oppositePlayer)
    {
        makeMove(moves, y, x, playerPawn, oppositePawn);

        Player newPlayer = copyCurrentPlayer(y, x, playerPawn, currentPlayer);

        Player newOppositePlayer = copyOppositePlayer(oppositePawn, oppositePlayer);

        Board newBoard = copyBoard(newPlayer, newOppositePlayer);

        Pawn newPawn = newPlayer.getPawns().stream()
                .filter(p1 -> p1.getX() == x && p1.getY() == y)
                .findAny()
                .orElse(null);

        if(newPawn == null) return;
        findMovesWithBeatingForKing(newPawn, newBoard,moves, newPlayer, newOppositePlayer);

    }

    private Board copyBoard(Player newPlayer, Player newOppositePlayer) {
        Board newBoard = new Board();
        if(newPlayer.isWhite())
        {
            newBoard.setBoard(newPlayer, newOppositePlayer);
        }
        else
        {
            newBoard.setBoard(newOppositePlayer, newPlayer);
        }
        return newBoard;
    }

    private Player copyOppositePlayer(Pawn oppositePawn, Player oppositePlayer) {
        Player newOppositePlayer;

        if(oppositePlayer.isHuman())
        {
            newOppositePlayer = new HumanPlayer(oppositePlayer.getName(), oppositePlayer.isWhite());
        }
        else
        {
            newOppositePlayer = new BotPlayer(oppositePlayer.getName(), oppositePlayer.isWhite(), ((BotPlayer) oppositePlayer).isUseMinMax());
        }

        newOppositePlayer.getPawns().clear();
        for(int i = 0; i < oppositePlayer.getPawns().size(); i++)
        {
            Pawn oldPawn = oppositePlayer.getPawns().get(i);
            int newX = oldPawn.getX();
            int newY = oldPawn.getY();

            if(oldPawn == oppositePawn)
            {
                continue;
            }

            Pawn newPawn = new Pawn(newX, newY, oldPawn.isWhite(), oldPawn.isKing());
            newOppositePlayer.getPawns().add(newPawn);
        }
        return newOppositePlayer;
    }

    private Player copyCurrentPlayer(int y, int x, Pawn playerPawn, Player currentPlayer) {
        Player newPlayer;
        if(currentPlayer.isHuman())
        {
            newPlayer = new HumanPlayer(currentPlayer.getName(), currentPlayer.isWhite());
        }
        else
        {
            newPlayer = new BotPlayer(currentPlayer.getName(), currentPlayer.isWhite(), ((BotPlayer) currentPlayer).isUseMinMax());
        }

        newPlayer.getPawns().clear();
        for(int i = 0; i < currentPlayer.getPawns().size(); i++)
        {
            Pawn oldPawn = currentPlayer.getPawns().get(i);
            int newX = oldPawn.getX();
            int newY = oldPawn.getY();

            if(oldPawn == playerPawn)
            {
                newX = x;
                newY = y;
            }

            Pawn newPawn = new Pawn(newX, newY, oldPawn.isWhite(), oldPawn.isKing());
            newPlayer.getPawns().add(newPawn);
        }
        return newPlayer;
    }

    private void makeMove(ArrayList<Move> moves, int y, int x, Pawn playerPawn, Pawn oppositePawn) {
        Move moveWithBeating = moves.stream().filter(m -> m.endCoordinates.equals(String.valueOf(playerPawn.getX()) + playerPawn.getY()))
                .findAny()
                .orElse(null);

        if(moveWithBeating == null)
        {
            Move move = new Move();
            move.setStartCoordinates(String.valueOf(playerPawn.getX()) + playerPawn.getY());
            move.setCoordinates(String.valueOf(x)+ y);

            move.getCapturedPawns().add(oppositePawn);
            moves.add(move);
        }
        else
        {
            Move move = new Move();
            ArrayList<Pawn> capturedPawns = new ArrayList<>(moveWithBeating.getCapturedPawns());
            move.setCapturedPawns(capturedPawns);
            move.getCapturedPawns().add(oppositePawn);
            move.setStartCoordinates(moveWithBeating.getStartCoordinates());
            move.setCoordinates(String.valueOf(x)+ y);
            moves.add(move);
        }
    }

    private void findFreeField(Board board, ArrayList<Move> moves, int x, int y, Pawn pawn)
    {
        Pawn pawnNext = board.getBoard().stream()
                .filter(p -> p.getX() == x && p.getY() == y)
                .findAny()
                .orElse(null);

        if(pawnNext == null){
            Move move = new Move();
            move.setStartCoordinates(String.valueOf(pawn.getX()) + pawn.getY());
            move.setCoordinates(String.valueOf(x)+ y);
            moves.add(move);
        }
    }
}

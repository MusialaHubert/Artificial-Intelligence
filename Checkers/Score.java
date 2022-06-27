package com.company;

public class Score
{
    public int scorePawnsAreEqual(Player currentPlayer, Player oppositePlayer)
    {
        int score = 0;
        for(int i = 0; i < currentPlayer.getPawns().size(); i++)
        {
            if(currentPlayer.isWhite()) score += 1;
            else score -= 1;
        }
        for(int i = 0; i < oppositePlayer.getPawns().size(); i++)
        {
            if(oppositePlayer.isWhite()) score += 1;
            else score -= 1;
        }
        return score;
    }

    public int scoreWithKingPawns(Player currentPlayer, Player oppositePlayer)
    {
        int score = 0;
        for(int i = 0; i < currentPlayer.getPawns().size(); i++)
        {
            int currentScore = 0;
            if(currentPlayer.isWhite()) currentScore++;
            else currentScore--;

            if(currentPlayer.getPawns().get(i).isKing()) currentScore *= 3;

            score += currentScore;
        }
        for(int i = 0; i < oppositePlayer.getPawns().size(); i++)
        {
            int currentScore = 0;
            if(oppositePlayer.isWhite()) currentScore++;
            else currentScore--;

            if(oppositePlayer.getPawns().get(i).isKing()) currentScore *= 3;

            score += currentScore;
        }
        return score;
    }

    public int scoreWithDistrictsAndEqualPawns(Player currentPlayer, Player oppositePlayer)
    {
        int score = 0;

        for(int i = 0; i < currentPlayer.getPawns().size(); i++)
        {
            int currentScore = 0;
            if(currentPlayer.isWhite())
            {
                currentScore++;
                if(currentPlayer.getPawns().get(i).getY() > 4)
                {
                    currentScore *= 2;
                }
            }
            else
            {
                currentScore--;
                if(currentPlayer.getPawns().get(i).getY() < 4)
                {
                    currentScore *= 2;
                }
            }

            score += currentScore;
        }
        for(int i = 0; i < oppositePlayer.getPawns().size(); i++)
        {
            int currentScore = 0;
            if(oppositePlayer.isWhite())
            {
                currentScore++;
                if(oppositePlayer.getPawns().get(i).getY() > 4)
                {
                    currentScore *= 2;
                }
            }
            else{
                currentScore--;
                if(oppositePlayer.getPawns().get(i).getY() < 4)
                {
                    currentScore *= 2;
                }
            }
            score += currentScore;
        }
        return score;
    }
    public int scoreWithDistrictsAndKingPawns(Player currentPlayer, Player oppositePlayer)
    {
        int score = 0;

        for(int i = 0; i < currentPlayer.getPawns().size(); i++)
        {
            int currentScore = 0;
            if(currentPlayer.isWhite())
            {
                currentScore++;
                if(currentPlayer.getPawns().get(i).getY() > 4)
                {
                    currentScore *= 2;
                }
            }
            else{
                currentScore--;
                if(currentPlayer.getPawns().get(i).getY() < 4)
                {
                    currentScore *= 2;
                }
            }
            if(currentPlayer.getPawns().get(i).isKing()) currentScore++;

            score += currentScore;
        }
        for(int i = 0; i < oppositePlayer.getPawns().size(); i++)
        {
            int currentScore = 0;
            if(oppositePlayer.isWhite())
            {
                currentScore++;
                if(oppositePlayer.getPawns().get(i).getY() > 4)
                {
                    currentScore *= 2;
                }
            }
            else{
                currentScore--;
                if(oppositePlayer.getPawns().get(i).getY() < 4)
                {
                    currentScore *= 2;
                }
            }
            if(oppositePlayer.getPawns().get(i).isKing()) currentScore++;

            score += currentScore;
        }
        return score;
    }
}

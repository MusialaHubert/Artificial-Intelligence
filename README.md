# **Artificial Intelligence**
This repository contains three projects covering different areas of Artificial Intelligence. 

## **Genetic Algorithm**
The aim of implementing the project was to get acquainted with the optimization problem based on an example of Genetic Algorithm. The presented problem applies to
arranging machines on the board in a way that minimizes the cost of work taking into consideration distance between them.

In order to optimize the result, the genetic algorithm used the selection operators (tournament and roulette), mutation and crossover.

Language: **C#**

## **Constraint Satisfaction Problem**
The aim of implementing the project was to get acquainted with the algorithms used for solving Constraint Satisfaction Problems on the example of two games: Binary and Futoshiki.

To solve the problems I have used following algorithms:
- Backtracking
- Forward checking

**Binary** is a logic puzzle to be solved on the n by n board. It consists in entering 1 and 0 so that each row and column are unique, in no row or column there are three numbers in a row and each row and column contain the same number of zeros what ones.

**Futoshiki** is a board-based puzzle game. It is playable on a square board having a given fixed size. The purpose of the game is to discover the digits hidden inside the board's cells; each cell is filled with a digit between 1 and the board's size. On each row and column each digit appears exactly once. The board might also contain some inequalities between the board cells and these inequalities must be respected.

Language: **Java**


## **Checkers**
Console application for playing checkers allowing you to play in human - human mode, human - AI mode and AI - AI mode. 

To perform AI movements **MinMax Algorithm** and **Alpha Beta Pruning** have been implemented. 

Several Evaluation Functions have been created including: 
- All pieces scored equally
- King Pawns scored higher than normal Pieces
- Pawns located in the half on opponent's board scored higher

Language: **Java**

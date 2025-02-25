package com.systems.automaton.pinball;

public class JNIEntryPoint {
    static void setState(int state) {
        StateHelper.INSTANCE.setState(state);
    }

    static void setBallInPlunger(boolean isInPlunger) {
        StateHelper.INSTANCE.setBallInPlunger(isInPlunger);
    }

    static void addHighScore(int score) {
        StateHelper.INSTANCE.addHighScore(score);
    }

    static int getHighScore() {
        return StateHelper.INSTANCE.getHighScore();
    }

    static void setRemainingBalls(int balls) {
        StateHelper.INSTANCE.setRemainingBalls(balls);
    }

    static void printString(String string, int type) {
        StateHelper.INSTANCE.printString(string, type);
    }

    static void clearText(int type) {
        StateHelper.INSTANCE.clearText(type);
    }

    static void postScore(int score) {
        StateHelper.INSTANCE.postScore(score);
    }

    static void postBallCount(int count) {
        StateHelper.INSTANCE.postBallCount(count);
    }

    static void cheatsUsed() { StateHelper.INSTANCE.cheatsUsed(); }

    static void gameIsReady() {StateHelper.INSTANCE.gameIsReady();}
}

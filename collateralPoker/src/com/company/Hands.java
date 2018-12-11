package com.company;

public enum Hands {
    HC_9(0), HC_T(1), HC_J(2), HC_Q(3), HC_K(4), HC_A(5),
    PA_9(6), PA_T(7), PA_J(8), PA_Q(9), PA_K(9), PA_A(10),
    TP_T9(11), TP_J9(12), TP_JT(13), TP_Q9(14), TP_QT(15),
    TP_QJ(16), TP_K9(17), TP_KT(18), TP_KJ(19), TP_KQ(19),
    TP_A9(20), TP_AT(21), TP_AJ(22), TP_AQ(23), TP_AK(24),
    TK_9(25), TK_T(26), TK_J(27), TK_Q(28), TK_K(29), TK_A(30),
    ST_SMALL(31), ST_BIG(32),
    FL_DIAMONDS(31), FL_HEARTS(31), FL_SPADE(31), FL_CLUBS(31),
    FH_9T(32), FH_9J(33), FH_9Q(34), FH_9K(35), FH_9A(36),
    FH_T9(37), FH_TJ(38), FH_TQ(39), FH_TK(40), FH_TA(41),
    FH_J9(42), FH_JT(43), FH_JQ(44), FH_JK(45), FH_JA(46),
    FH_Q9(47), FH_QT(48), FH_QJ(49), FH_QK(50), FH_QA(51),
    FH_K9(52), FH_KT(53), FH_KJ(54), FH_KQ(55), FH_KA(56),
    FH_A9(57), FH_AT(58), FH_AJ(59), FH_AQ(60), FH_AK(61),
    FK_9(62), FK_T(63), FK_J(64), FK_Q(65), FK_K(66), FK_A(67),
    SF_DIAMONDS(68), SF_HEARTS(68), SF_SPADES(68),SF_CLUBS(68),
    RF_DIAMONDS(69), RF_HEARTS(69), RF_SPADES(69), RF_CLUBS(69);

    private int value;

    Hands(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
//set of name of all posible hands in game
//6 highcard + 6 pairs + 15 2pairs + 6 3kind + 2 straight + 4 flush + 30 fulls + 6 4kind + 4 straightflush + 4 royals = 83
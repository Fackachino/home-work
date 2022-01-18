package com.sbrf.reboot.atm;

import java.util.ArrayList;
import java.util.List;

public class Cassette<Banknote> {
    private List<Banknote> banknoteList;

    public Cassette(ArrayList<Banknote> banknoteList) {
        this.banknoteList = banknoteList;
    }

    public int getCountBanknotes(){
        return banknoteList.size();
    }
}

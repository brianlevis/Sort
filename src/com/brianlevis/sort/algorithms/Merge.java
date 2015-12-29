package com.brianlevis.sort.algorithms;

public class Merge extends Algorithm {
    
   private String state = "scan";
   private int currentIndex = 1;
   private int lowerBound = 0;
   private int midPoint = 1;
   private int upperBound = 1;
    
    public Merge() {
        super();
        select(1);
        highlight(0);
    }
    
    private void scan() {
        
    }
    
    private void sort() {
        
    }
    
    private void combine() {
        
    }

    public void next() {
        switch (state) {
        case "scan":
            scan();
            break;
        }
    }
}

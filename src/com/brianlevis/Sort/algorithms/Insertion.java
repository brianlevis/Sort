package com.brianlevis.sort.algorithms;

public class Insertion extends Algorithm {

	private int lastIndex    = 0;
	private int currentIndex = 0;
	private int localMinimum = 0;
	private int nextIndex    = 0;
	
	public Insertion() {
		super("Insertion Sort");
	}
	
	public void next() {
		if (nextIndex == elements.size()) {
			sorted = true;
			deselect(nextIndex - 1);
			return;
		}
		
		deselect(lastIndex);
		if (currentIndex == elements.size()) {
			deselect(localMinimum);
			elements.add(nextIndex, elements.remove(localMinimum));
			deselect(Math.max(nextIndex - 1, 0));
			highlight(nextIndex);
			nextIndex++;
			localMinimum = nextIndex;
			currentIndex = nextIndex;
			lastIndex = nextIndex;
		} else {
			select(localMinimum);
			if (select(currentIndex) <= get(localMinimum)) {
				deselect(localMinimum);
				localMinimum = currentIndex;
			}
			lastIndex = currentIndex;
			currentIndex++;
		}
	}

}

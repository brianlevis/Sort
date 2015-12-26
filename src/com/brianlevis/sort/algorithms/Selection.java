package com.brianlevis.sort.algorithms;

public class Selection extends Algorithm {

	private int lastIndex    = 0;
	private int currentIndex = 1;
	private int localMinimum = 0;
	private int nextIndex    = 0;
	
	public Selection() {
		super("Selection Sort");
		select(0);
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
			deselect(Math.max(0, nextIndex - 1));
			move(localMinimum, nextIndex);
			highlight(nextIndex);
			nextIndex++;
			localMinimum = nextIndex;
			currentIndex = nextIndex + 1;
			lastIndex = nextIndex;
		} else {
			if (select(currentIndex) <= select(localMinimum)) {
				deselect(localMinimum);
				localMinimum = currentIndex;
			}
			lastIndex = currentIndex;
			currentIndex++;
		}
	}

}

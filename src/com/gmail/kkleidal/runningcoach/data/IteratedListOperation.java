package com.gmail.kkleidal.runningcoach.data;

import java.util.ArrayList;

/**
 * Performs an operation iteratively over a list
 * @author kkleidal
 *
 * @param <T1>
 * @param <T2>
 */
public interface IteratedListOperation<T1,T2> {
	public abstract T2 performOp( ArrayList<T1> list );
}

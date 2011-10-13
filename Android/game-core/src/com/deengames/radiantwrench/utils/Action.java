package com.deengames.radiantwrench.utils;

import com.deengames.radiantwrench.util.RadiantWrenchException;

/**
 * Just like our C# equivalent, a generic method delegate that takes
 * no parameters and has no return type.
 * @author ashes999
 *
 */
public interface Action {
	void invoke() throws RadiantWrenchException;
}

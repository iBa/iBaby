package com.iBaby.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Custom class made to sort params
 * @author steffengy
 *
 * @param <K> The index
 * @param <V> The param
 */
@SuppressWarnings("hiding")
public class ParamMap<K, V> extends HashMap {
	/*
	 * @return Integer, the amount of the params which are needed
	 */
	public int countNeeded() {
		int needed = 0;
		for(Map.Entry<Integer, Param> e : (Set<Map.Entry<Integer, Param>>) this.entrySet()) {
			if(e.getValue().isNeeded()) {
				++needed;
			}
		}
		return needed;
	}
	/**
	 * @return Integer, the amount of the params which aren't needed
	 */
	public int countOptional() {
		int optional = 0;
		for(Map.Entry<Integer, Param> e : (Set<Map.Entry<Integer, Param>>) this.entrySet()) {
			if(!e.getValue().isNeeded()) {
				++optional;
			}
		}
		return optional;
	}
	/**
	 * ..
	 */
	public Param get(int i) {
		return (Param) super.get(i);
	}
	/**
	 * ..
	 */
	public Set<Map.Entry<Integer, Param>> entrySet() {
		return (Set<Map.Entry<Integer, Param>>) super.entrySet();
	}
}

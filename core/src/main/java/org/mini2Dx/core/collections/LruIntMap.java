/*******************************************************************************
 * Copyright 2020 See AUTHORS file
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package org.mini2Dx.core.collections;

import org.mini2Dx.gdx.utils.IntIntMap;
import org.mini2Dx.gdx.utils.IntMap;

/**
 * Extends {@link IntMap} to implement least-recently used capabilities.
 * Once the specified maximum capacity is reached,
 * the key that has been used the least is removed from the map.
 *
 * @param <V> The value type
 */
public class LruIntMap<V> extends IntMap<V> {
	public static final int DEFAULT_MAX_CAPACITY = 128;

	private final int maxCapacity;
	private final IntIntMap accessCounter = new IntIntMap();

	public LruIntMap() {
		super();
		maxCapacity = DEFAULT_MAX_CAPACITY;
	}

	public LruIntMap(int initialCapacity) {
		this(initialCapacity, DEFAULT_MAX_CAPACITY);
	}

	public LruIntMap(int initialCapacity, float loadFactor) {
		this(initialCapacity, DEFAULT_MAX_CAPACITY, loadFactor);
	}

	public LruIntMap(IntMap<? extends V> map) {
		this(map, DEFAULT_MAX_CAPACITY);
	}

	public LruIntMap(int initialCapacity, int maxCapacity) {
		super(initialCapacity);
		this.maxCapacity = maxCapacity;
	}

	public LruIntMap(int initialCapacity, int maxCapacity, float loadFactor) {
		super(initialCapacity, loadFactor);
		this.maxCapacity = maxCapacity;
	}

	public LruIntMap(IntMap<? extends V> map, int maxCapacity) {
		super(map);
		this.maxCapacity = maxCapacity;
	}

	@Override
	public V put(int key, V value) {
		while(super.size >= maxCapacity) {
			purge();
		}

		final V result = super.put(key, value);
		accessCounter.getAndIncrement(key, -1, 1);
		return result;
	}

	@Override
	public void putAll(IntMap<? extends V> map) {
		while(super.size >= maxCapacity) {
			purge();
		}

		super.putAll(map);
	}

	@Override
	public V get(int key) {
		final V result = super.get(key);
		accessCounter.getAndIncrement(key, 0, 1);
		return result;
	}

	@Override
	public V get(int key, V defaultValue) {
		final V result = super.get(key, defaultValue);
		accessCounter.getAndIncrement(key, 0, 1);
		return result;
	}

	@Override
	public V remove(int key) {
		final V result = super.remove(key);
		accessCounter.remove(key, -1);
		return result;
	}

	@Override
	public void clear() {
		super.clear();
		accessCounter.clear();
	}

	private void purge() {
		int smallestKey = 0;
		int smallestCount = Integer.MAX_VALUE;

		IntIntMap.Keys keys = accessCounter.keys();
		while(keys.hasNext) {
			final int key = keys.next();
			final int count = accessCounter.get(key, 0);
			if(count < smallestCount) {
				smallestKey = key;
				smallestCount = count;
			}
		}
		remove(smallestKey);
	}

	/**
	 * Returns the maximum number of keys that can be stored in the {@link LruIntMap}
	 * @return Defaults to 128
	 */
	public int getMaxCapacity() {
		return maxCapacity;
	}
}
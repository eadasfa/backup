package com.example.demo;

import static org.junit.Assert.*;

import org.junit.Test;

import com.example.demo.Caculate;

public class CaculateTest {

	@Test
	public void addTest() {
		assertEquals(6, new Caculate().add(3, 3));
	}

}
